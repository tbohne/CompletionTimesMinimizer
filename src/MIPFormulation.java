import ilog.concert.*;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.List;

public class MIPFormulation {

    private Instance instance;
    private final double timeLimit;
    private final boolean hideCPLEXOutput;
    private final int mipEmphasis;
    private final double tolerance;

    /**
     * Constructor
     *
     * @param instance        - instance to be solved
     * @param timeLimit       - time limit for the solving procedure
     * @param hideCPLEXOutput - determines whether the CPLEX output gets hidden
     * @param mipEmphasis     - controls trade-offs between speed, feasibility and optimality
     * @param tolerance       - termination tolerance
     */
    public MIPFormulation(Instance instance, double timeLimit, boolean hideCPLEXOutput, int mipEmphasis, double tolerance) {
        this.instance = instance;
        this.timeLimit = timeLimit;
        this.hideCPLEXOutput = hideCPLEXOutput;
        this.mipEmphasis = mipEmphasis;
        this.tolerance = tolerance;
    }

    public int computeTimeHorizon() {
        int sum = 0;
        for (int processingTime : this.instance.getProcessingTimes()) {
            sum += processingTime;
        }
        return sum;
    }

    public Solution solve(int precModel, String instanceName) {

        Solution sol = new Solution(new ArrayList<>(), instanceName, precModel);

        try {
            // define new model
            IloCplex cplex = new IloCplex();

            int T = this.computeTimeHorizon();

            IloIntVar[][] x = new IloIntVar[this.instance.getNumberOfJobs()][];
            this.initVariables(cplex, x, T);

            IloLinearNumExpr objective = cplex.linearNumExpr();

            for (int j = 0; j < this.instance.getNumberOfJobs(); j++) {
                for (int t = 0; t < T; t++) {
                    objective.addTerm(t, x[j][t]);
                }
            }
            cplex.addMinimize(objective);

            this.addConstraints(cplex, x, T, precModel);

            this.setCPLEXConfig(cplex);
            double startTime = cplex.getCplexTime();

            cplex.exportModel("res/lp_files/" + instanceName + "_" + precModel + ".lp");

            if (cplex.solve()) {
                List<Job> plannedJobs = this.generateSolutionFromVariableAssignments(x, cplex);
                sol = new Solution(plannedJobs, instanceName, precModel);
                sol.setTimeToSolve(cplex.getCplexTime() - startTime);
            }
            cplex.end();

        } catch (IloException e) {
            e.printStackTrace();
        }
        return sol;
    }

    private List<Job> generateSolutionFromVariableAssignments(IloIntVar[][] x, IloCplex cplex) throws ilog.concert.IloException {
        List<Job> plannedJobs = new ArrayList<>();
        for (int j = 0; j < x.length; j++) {
            for (int t = 0; t < x[0].length; t++) {
                if (Math.round(cplex.getValue(x[j][t])) == 1) {
                    plannedJobs.add(new Job(j + 1, t + 1));
//                    System.out.println("job " + (j + 1) + " ends at time " + (t + 1));
                }
            }
        }
        return plannedJobs;
    }

    private void initVariables(IloCplex cplex, IloIntVar[][] x, int T) throws ilog.concert.IloException {
        for (int j = 0; j < this.instance.getNumberOfJobs(); j++) {
            x[j] = cplex.intVarArray(T, 0, 1);
        }
    }

    private void addConstraints(IloCplex cplex, IloIntVar[][] x, int T, int precModel) throws ilog.concert.IloException {

        // j starting at job 0, t starting at t = 1

        // --- Constraint (3.11) ---
        for (int j = 0; j < this.instance.getNumberOfJobs(); j++) {
            IloLinearIntExpr expr = cplex.linearIntExpr();
            for (int t = this.instance.getProcessingTimes()[j] - 1; t < T; t++) {
                expr.addTerm(1, x[j][t]);
            }
            cplex.addEq(expr, 1);
        }

        // --- Constraint (3.12) ---
        for (int t = 0; t < T; t++) {
            IloLinearIntExpr expr = cplex.linearIntExpr();
            for (int j = 0; j < this.instance.getNumberOfJobs(); j++) {
                for (int tau = t; tau <= Math.min(t + this.instance.getProcessingTimes()[j] - 1, T - 1); tau++) {
                    expr.addTerm(1, x[j][tau]);
                }
            }
            cplex.addLe(expr, 1);
        }

        switch (precModel) {
            case 1:
                this.applyPrecVariantOne(cplex, x, T);
                break;
            case 2:
                this.applyPrecVariantTwo(cplex, x, T);
                break;
            case 3:
                this.applyPrecVariantThree(cplex, x, T);
                break;
            case 4:
                this.applyPrecVariantFour(cplex, x, T);
                break;
            default:
                System.out.println("unknown precedence model");
                System.exit(0);
        }
    }

    public void applyPrecVariantFour(IloCplex cplex, IloIntVar[][] x, int T) throws ilog.concert.IloException {
        for (Precedence prec : this.instance.getPrecedences()) {
            for (int t = 0; t < T; t++) {
                IloLinearIntExpr exprOne = cplex.linearIntExpr();
                for (int tau = t; tau < T; tau++) {
                    exprOne.addTerm(1, x[prec.getPred() - 1][tau]);
                }
                IloLinearIntExpr exprTwo = cplex.linearIntExpr();
                for (int tau = 0; tau < Math.min(t + this.instance.getProcessingTimes()[prec.getSucc() - 1] - 1, T); tau++) {
                    exprTwo.addTerm(1, x[prec.getSucc() - 1][tau]);
                }
                cplex.addLe(cplex.sum(exprOne, exprTwo), 1);
            }
        }
    }

    public void applyPrecVariantThree(IloCplex cplex, IloIntVar[][] x, int T) throws ilog.concert.IloException {
        for (Precedence prec : this.instance.getPrecedences()) {
            for (int t = 0; t < T; t++) {
                IloLinearIntExpr expr = cplex.linearIntExpr();
                for (int tau = t + this.instance.getProcessingTimes()[prec.getSucc() - 1]; tau < T; tau++) {
                    expr.addTerm(1, x[prec.getSucc() - 1][tau]);
                }
                cplex.addLe(cplex.diff(x[prec.getPred() - 1][t], expr), 0);
            }
        }
    }

    public void applyPrecVariantTwo(IloCplex cplex, IloIntVar[][] x, int T) throws ilog.concert.IloException {
        for (Precedence prec : this.instance.getPrecedences()) {
            for (int t = 0; t < T; t++) {
                IloLinearIntExpr expr = cplex.linearIntExpr();
                for (int tau = 0; tau < Math.min(t + this.instance.getProcessingTimes()[prec.getSucc() - 1] - 1, T); tau++) {
                    expr.addTerm(1, x[prec.getSucc() - 1][tau]);
                }
                cplex.addLe(cplex.sum(x[prec.getPred() - 1][t], expr), 1);
            }
        }
    }

    public void applyPrecVariantOne(IloCplex cplex, IloIntVar[][] x, int T) throws ilog.concert.IloException {
        // --- Constraint (3.13) ---
        for (Precedence prec : this.instance.getPrecedences()) {

            IloLinearIntExpr exprOne = cplex.linearIntExpr();
            IloLinearIntExpr exprTwo = cplex.linearIntExpr();

            for (int t = 0; t < T; t++) {
                exprOne.addTerm(t, x[prec.getSucc() - 1][t]);
            }
            for (int t = 0; t < T; t++) {
                exprTwo.addTerm(t, x[prec.getPred() - 1][t]);
            }
            cplex.addGe(cplex.diff(exprOne, exprTwo), this.instance.getProcessingTimes()[prec.getSucc() - 1]);
        }
    }

    private void setCPLEXConfig(IloCplex cplex) throws ilog.concert.IloException {

        if (this.hideCPLEXOutput) {
            cplex.setOut(null);
        }

        // set time limit
        cplex.setParam(IloCplex.Param.TimeLimit, this.timeLimit);

        // control trade-offs between speed, feasibility and optimality
        cplex.setParam(IloCplex.IntParam.MIPEmphasis, this.mipEmphasis);

        // set termination tolerance
        cplex.setParam(IloCplex.DoubleParam.EpAGap, this.tolerance);
        cplex.setParam(IloCplex.DoubleParam.EpGap, this.tolerance);
    }
}
