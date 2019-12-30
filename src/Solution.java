import java.util.Collections;
import java.util.List;

public class Solution {

    private List<Job> plannedJobs;

    public Solution(List<Job> plannedJobs) {
        this.plannedJobs = plannedJobs;
        Collections.sort(this.plannedJobs);
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
