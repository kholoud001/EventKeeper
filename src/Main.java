import DAO.implementation.EventDAOImplementation;
import DAO.implementation.UserDAOImplementation;
import DAO.interfaces.EventDAOInterface;
import DAO.interfaces.UserDAOInterface;
import GUI.AdminGUI;
import GUI.MainGUI;
import GUI.ParticipantGUI;
import services.implementation.EventServiceImpl;
import services.implementation.ReportServiceImpl;
import services.implementation.UserServiceImpl;
import services.interfaces.EventServiceInterface;
import services.interfaces.ReportServiceInterface;
import services.interfaces.UserServiceInterface;


import java.util.Scanner;


/**
 * Main application class for managing events.
 */
public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize DAO and Services
        UserDAOInterface userDAO = new UserDAOImplementation();
        EventDAOInterface eventDAO = new EventDAOImplementation();
        UserServiceInterface userService = new UserServiceImpl(userDAO);
        EventServiceInterface eventService = new EventServiceImpl(eventDAO);
        ReportServiceInterface reportService = new ReportServiceImpl();


        ParticipantGUI participantGUI = new ParticipantGUI(userService, eventService, scanner);
        AdminGUI adminGUI = new AdminGUI(userService, eventService, reportService, scanner);

        MainGUI mainGUI = new MainGUI(userService, scanner, participantGUI, adminGUI);

        mainGUI.start();

    }

}
