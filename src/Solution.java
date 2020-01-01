import java.util.Collections;
import java.util.List;

public class Solution {

    private List<Job> plannedJobs;
    private double timeToSolve;
    private int precModel;
    private String nameOfSolvedInstance;

    public Solution(List<Job> plannedJobs, String nameOfSolvedInstance, int precModel) {
        this.plannedJobs = plannedJobs;
        Collections.sort(this.plannedJobs);
        this.nameOfSolvedInstance = nameOfSolvedInstance;
        this.precModel = precModel;
    }

    public String getNameOfSolvedInstance() {
        return this.nameOfSolvedInstance;
    }

    public int getPrecModel() {
        return this.precModel;
    }

    public void setTimeToSolve(double timeToSolve) {
        this.timeToSolve = timeToSolve;
    }

    public String getTimeToSolve() {
        return String.format("%.02f", this.timeToSolve).replace(",", ".");
    }

    public int getSumOfCompletionTimes() {
        int sum = 0;
        for (Job job : this.plannedJobs) {
            sum += job.getEndTime();
        }
        return sum;
    }

    public boolean isFeasible() {
        // TODO: implement feasibility check
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        for (Job job : this.plannedJobs) {
            str += job + " - ";
        }
        str += "\n";
        return str;
    }
}
