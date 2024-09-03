package services.implementation;

import Entities.User;
import services.interfaces.ReportServiceInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportServiceImpl implements ReportServiceInterface {

    /**
     * Generates a report of participants and writes it to a specified file.
     *
     * @param participants The list of User objects representing the participants to include in the report.
     * @param filePath The file path where the report should be saved. The file will be created or
     *                 overwritten at this location.
     */
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
