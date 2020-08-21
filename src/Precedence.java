/**
 * Represents a precedence relation between two jobs.
 */
public class Precedence {

    private final int pred;
    private final int succ;

    /**
     * Constructor
     *
     * @param pred - predecessor
     * @param succ - successor
     */
    public Precedence(int pred, int succ) {
        this.pred = pred;
        this.succ = succ;
    }

    /**
     * Returns the predecessor.
     *
     * @return predecessor
     */
    public int getPred() {
        return this.pred;
    }

    /**
     * Returns the successor.
     *
     * @return successor
     */
    public int getSucc() {
        return this.succ;
    }

    @Override
    public String toString() {
        return "(" + this.pred + ", " + this.succ + ")";
    }
}
