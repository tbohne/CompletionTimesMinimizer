public class Job implements Comparable<Job> {

    private int idx;
    private int endTime;

    public Job(int idx, int endTime) {
        this.idx = idx;
        this.endTime = endTime;
    }

    public int getIdx() {
        return this.idx;
    }

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
