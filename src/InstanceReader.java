import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstanceReader {

    public static Instance readInstance(String filename) {

        int numberOfJobs = 0;
        int[] processingTimes = new int[numberOfJobs];
        List<Precedence> precedences = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine();
            numberOfJobs = Integer.parseInt(reader.readLine().trim());
            reader.readLine();
            reader.readLine();
            processingTimes = new int[numberOfJobs];
            for (int idx = 0; idx < numberOfJobs; idx++) {
                String line = reader.readLine().trim();
                processingTimes[idx] = Integer.parseInt(line.split(" ")[1]);
            }
            String line = reader.readLine();
            line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                precedences.add(new Precedence(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1])));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Instance(numberOfJobs, processingTimes, precedences);
    }
}
