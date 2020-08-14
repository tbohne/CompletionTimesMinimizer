import java.util.List;

/**
 * Represents an instance of the scheduling problem.
 */
public class Instance {

    private int numberOfJobs;
    private int[] processingTimes;
    private List<Precedence> precedences;

    /**
     * Constructor
     *
     * @param numberOfJobs    - number of jobs to be scheduled
     * @param processingTimes - processing times of the jobs
     * @param precedences     - precedence relationships to be fulfilled
     */
    public Instance(int numberOfJobs, int[] processingTimes, List<Precedence> precedences) {
        this.numberOfJobs = numberOfJobs;
        this.processingTimes = processingTimes;
        this.precedences = precedences;
    }

    /**
     * Returns the instance's number of jobs.
     *
     * @return number of jobs
     */
    public int getNumberOfJobs() {
        return this.numberOfJobs;
    }

    /**
     * Returns the instance's processing times.
     *
     * @return processing times
     */
    public int[] getProcessingTimes() {
        return this.processingTimes;
    }

    /**
     * Returns the instance's precedence relationships.
     *
     * @return precedences
     */
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
