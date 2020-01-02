import java.io.File;
import java.util.Arrays;

public class Test {

    private static final String INSTANCE_PREFIX = "res/instances/";
    private static final int TIME_LIMIT = 600;

    /********************** CPLEX CONFIG **********************/
    private static final boolean HIDE_CPLEX_OUTPUT = true;
    // 1 --> feasibility over optimality
    private static final int MIP_EMPHASIS = 1;
    // 0 --> only terminating with optimal solutions before time limit
    private static final double MIP_TOLERANCE = 0.0;
    /**********************************************************/

    public static void main(String[] args) {

        File dir = new File(INSTANCE_PREFIX);
        File[] directoryListing = dir.listFiles();
        assert directoryListing != null;
        Arrays.sort(directoryListing);

        for (File file : directoryListing) {

            String instanceName = file.toString().replace(INSTANCE_PREFIX, "").replace(".txt", "");
            System.out.println("working on: " + instanceName);
            Instance instance = InstanceReader.readInstance(INSTANCE_PREFIX + instanceName + ".txt");
            String solutionName = instanceName.replace("instance", "sol");

            MIPFormulation mip = new MIPFormulation(instance, TIME_LIMIT, HIDE_CPLEX_OUTPUT, MIP_EMPHASIS, MIP_TOLERANCE);

            Solution sol = mip.solve(1, instanceName);
            System.out.println("obj: " + sol.getSumOfCompletionTimes());
            System.out.println(sol);
            System.out.println("time: " + sol.getTimeToSolve());
            SolutionWriter.writeSolutionAsCSV("solutions.csv", sol, TIME_LIMIT);

            sol = mip.solve(2, instanceName);
            System.out.println("obj: " + sol.getSumOfCompletionTimes());
            System.out.println(sol);
            System.out.println("time: " + sol.getTimeToSolve());
            SolutionWriter.writeSolutionAsCSV("solutions.csv", sol, TIME_LIMIT);

            sol = mip.solve(3, instanceName);
            System.out.println("obj: " + sol.getSumOfCompletionTimes());
            System.out.println(sol);
            System.out.println("time: " + sol.getTimeToSolve());
            SolutionWriter.writeSolutionAsCSV("solutions.csv", sol, TIME_LIMIT);

            sol = mip.solve(4, instanceName);
            System.out.println("obj: " + sol.getSumOfCompletionTimes());
            System.out.println(sol);
            System.out.println("time: " + sol.getTimeToSolve());
            SolutionWriter.writeSolutionAsCSV("solutions.csv", sol, TIME_LIMIT);
        }
    }
}
