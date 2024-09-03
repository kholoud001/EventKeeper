package services.interfaces;

import Entities.User;

import java.util.List;

public interface ReportServiceInterface {

    void generateParticipantsReport(List<User> participants, String filePath);

}
