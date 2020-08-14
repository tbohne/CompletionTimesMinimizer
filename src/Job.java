/**
 * Represents a job to be scheduled.
 */
public class Job implements Comparable<Job> {

    private int idx;
    private int endTime;

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
        return Integer.toString(this.idx) + " (" + this.endTime + ")";
    }

    @Override
    public int compareTo(Job other) {
        if (this.endTime < other.endTime) {
            return -1;
        } else if (this.endTime > other.endTime) {
            return 1;
        } else {
            return 0;
        }
    }
}
