public class Test {

    public static void main(String[] args) {

        String instanceName = "res/instances/instance10.txt";
        System.out.println("working on: " + instanceName);
        Instance instance = InstanceReader.readInstance(instanceName);
        System.out.println(instance);

        MIPFormulation mip = new MIPFormulation(instance, 180.0, true, 1, 0.0);

        Solution sol = mip.solve(1);
        System.out.println("obj: " + sol.getSumOfCompletionTimes());
        System.out.println(sol);
        System.out.println("time: " + sol.getTimeToSolve());

        mip.solve(2);
        System.out.println("obj: " + sol.getSumOfCompletionTimes());
        System.out.println(sol);
        System.out.println("time: " + sol.getTimeToSolve());

        mip.solve(3);
        System.out.println("obj: " + sol.getSumOfCompletionTimes());
        System.out.println(sol);
        System.out.println("time: " + sol.getTimeToSolve());

        mip.solve(4);
        System.out.println("obj: " + sol.getSumOfCompletionTimes());
        System.out.println(sol);
        System.out.println("time: " + sol.getTimeToSolve());
    }
}
