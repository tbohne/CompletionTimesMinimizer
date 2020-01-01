import java.io.File;
import java.util.Arrays;

public class Test {

    private static final String INSTANCE_PREFIX = "res/instances/";
    private static final int TIME_LIMIT = 600;

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

            MIPFormulation mip = new MIPFormulation(instance, TIME_LIMIT, true, 1, 0.0);

            Solution sol = mip.solve(1, instanceName);
            System.out.println("obj: " + sol.getSumOfCompletionTimes());
            System.out.println(sol);
            System.out.println("time: " + sol.getTimeToSolve());
            SolutionWriter.writeSolutionAsCSV("solutions.csv", sol, TIME_LIMIT);

            mip.solve(2, instanceName);
            System.out.println("obj: " + sol.getSumOfCompletionTimes());
            System.out.println(sol);
            System.out.println("time: " + sol.getTimeToSolve());
            SolutionWriter.writeSolutionAsCSV("solutions.csv", sol, TIME_LIMIT);

            mip.solve(3, instanceName);
            System.out.println("obj: " + sol.getSumOfCompletionTimes());
            System.out.println(sol);
            System.out.println("time: " + sol.getTimeToSolve());
            SolutionWriter.writeSolutionAsCSV("solutions.csv", sol, TIME_LIMIT);

            mip.solve(4, instanceName);
            System.out.println("obj: " + sol.getSumOfCompletionTimes());
            System.out.println(sol);
            System.out.println("time: " + sol.getTimeToSolve());
            SolutionWriter.writeSolutionAsCSV("solutions.csv", sol, TIME_LIMIT);
        }
    }
}
