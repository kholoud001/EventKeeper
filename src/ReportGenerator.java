import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    public void generateParticipantsReport(List<User> participants, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Participant Report\n");
            writer.write("============================\n");
            writer.write("Name\t\tEmail\t\tRole\n");
            writer.write("-----------------------------\n");

            for (User participant : participants) {
                writer.write(participant.getName() + "\t" +
                        participant.getEmail() + "\t" +
                        participant.getRole() + "\n");
            }

            System.out.println("Report generated successfully at " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while generating the report.");
            e.printStackTrace();
        }
    }
}
