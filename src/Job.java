/**
 * Represents a job to be scheduled.
 */
public class Job implements Comparable<Job> {

    private final int idx;
    private final int endTime;

    /**
     * Constructor
     *
     * @param idx     - index of the job
     * @param endTime - end time of the job
     */
    public Job(int idx, int endTime) {
        this.idx = idx;
        this.endTime = endTime;
    }

    /**
     * Returns the job's index.
     *
     * @return index
     */
    public int getIdx() {
        return this.idx;
    }

    /**
     * Returns the job's end time.
     *
     * @return ent time
     */
    public int getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        return this.idx + " (" + this.endTime + ")";
    }

    @Override
    public int compareTo(Job other) {
        return Integer.compare(this.endTime, other.endTime);
    }
}
