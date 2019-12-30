public class Test {

    public static void main(String[] args) {

        String instanceName = "res/instances/instance10.txt";
        System.out.println("working on: " + instanceName);
        Instance instance = InstanceReader.readInstance(instanceName);
        System.out.println(instance);

    }
}
