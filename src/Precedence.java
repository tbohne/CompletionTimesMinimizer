public class Precedence {

    private int pred;
    private int succ;

    public Precedence(int pred, int succ) {
        this.pred = pred;
        this.succ = succ;
    }

    public int getPred() {
        return this.pred;
    }

    public int getSucc() {
        return this.succ;
    }

    @Override
    public String toString() {
        return "(" + this.pred + ", " + this.succ + ")";
    }
}
