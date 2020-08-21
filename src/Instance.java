import java.util.List;

/**
 * Represents an instance of the scheduling problem.
 */
public class Instance {

    private final int numberOfJobs;
    private final int[] processingTimes;
    private final List<Precedence> precedences;

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
        StringBuilder str = new StringBuilder();
        str.append("number of jobs: ").append(this.numberOfJobs).append("\n");
        str.append("processing times:\n");
        for (int processingTime : this.processingTimes) {
            str.append(processingTime).append(" ");
        }
        str.append("\n");
        str.append("precedences:\n");
        for (Precedence prec : this.precedences) {
            str.append(prec).append(" ");
        }
        str.append("\n");
        return str.toString();
    }
}
