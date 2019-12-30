import java.util.List;

public class Instance {

    private int numberOfJobs;
    private int[] processingTimes;
    private List<Precedence> precedences;

    public Instance(int numberOfJobs, int[] processingTimes, List<Precedence> precedences) {
        this.numberOfJobs = numberOfJobs;
        this.processingTimes = processingTimes;
        this.precedences = precedences;
    }

    public int getNumberOfJobs() {
        return this.numberOfJobs;
    }

    public int[] getProcessingTimes() {
        return this.processingTimes;
    }

    public List<Precedence> getPrecedences() {
        return this.precedences;
    }

    @Override
    public String toString() {
        String str = "";
        str += "number of jobs: " + this.numberOfJobs + "\n";
        str += "processing times:\n";
        for (int processingTime : this.processingTimes) {
            str += processingTime + " ";
        }
        str += "\n";
        str += "precedences:\n";
        for (Precedence prec : this.precedences) {
            str += prec + " ";
        }
        str += "\n";
        return str;
    }
}
