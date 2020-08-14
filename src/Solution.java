import java.util.Collections;
import java.util.List;

/**
 * Represents a solution to 1 | prec | sum C_j.
 */
public class Solution {

    private List<Job> plannedJobs;
    private double timeToSolve;
    private int precModel;
    private String nameOfSolvedInstance;

    /**
     * Constructor
     *
     * @param plannedJobs          - list of planned jobs
     * @param nameOfSolvedInstance - name of the solved instance
     * @param precModel            - precedence model that was used
     */
    public Solution(List<Job> plannedJobs, String nameOfSolvedInstance, int precModel) {
        this.plannedJobs = plannedJobs;
        Collections.sort(this.plannedJobs);
        this.nameOfSolvedInstance = nameOfSolvedInstance;
        this.precModel = precModel;
    }

    /**
     * Returns the name of the solved instance.
     *
     * @return name of instance
     */
    public String getNameOfSolvedInstance() {
        return this.nameOfSolvedInstance;
    }

    /**
     * Returns the precedence model that was used to generate the solution.
     *
     * @return precedence model
     */
    public int getPrecModel() {
        return this.precModel;
    }

    /**
     * Sets the time to solve.
     *
     * @param timeToSolve - time it took to generate the solution
     */
    public void setTimeToSolve(double timeToSolve) {
        this.timeToSolve = timeToSolve;
    }

    /**
     * Returns a string representation of the solution's runtime.
     *
     * @return runtime
     */
    public String getTimeToSolve() {
        return String.format("%.02f", this.timeToSolve).replace(",", ".");
    }

    /**
     * Returns the sum of the completion times (objective value).
     *
     * @return sum of completion times
     */
    public int getSumOfCompletionTimes() {
        int sum = 0;
        for (Job job : this.plannedJobs) {
            sum += job.getEndTime();
        }
        return sum;
    }

    /**
     * Returns whether the solution is feasible.
     *
     * @return whether solution is feasible
     */
    public boolean isFeasible() {
        // TODO: implement feasibility check
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        for (Job job : this.plannedJobs) {
            str += job + " - ";
        }
        str += "\n";
        return str;
    }
}
