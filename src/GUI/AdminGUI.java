package GUI;

import Entities.Event;
import Entities.User;
import Enums.Role;
import services.interfaces.EventServiceInterface;
import services.interfaces.ReportServiceInterface;
import services.interfaces.UserServiceInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminGUI {
    private final UserServiceInterface userService;
    private final EventServiceInterface eventService;
    private final ReportServiceInterface reportService;
    private final Scanner scanner;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private User currentUser;

    public AdminGUI(UserServiceInterface userService, EventServiceInterface eventService, ReportServiceInterface reportService, Scanner scanner) {
        this.userService = userService;
        this.eventService = eventService;
        this.reportService = reportService;
        this.scanner = scanner;
    }

    public User displayMenuAdmin() {

        int choix;

        do {
            // Display the menu
            System.out.println("\n*** Entities.Event Management Section: ***");
            System.out.println("1. Add Event");
            System.out.println("2. Modify Event");
            System.out.println("3. Delete Event");
            System.out.println("4. Display All Events");
            System.out.println("5. Search Events");
            System.out.println("\n*** Participant Management Section: ***");
            System.out.println("6. Modify Participant Details");
            System.out.println("7. Display All Participants");
            System.out.println("8. Display Participants for an Event");
            System.out.println("9. Display Events of a Participant");
            System.out.println("10. Delete a Participant");
            System.out.println("11. Switch Account");
            System.out.println("12. Generate Report");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    modifyEvent();
                    break;
                case 3:
                    deleteEvent();
                    break;
                case 4:
                    displayEvents();
                    break;
                case 5:
                    searchEvents();
                    break;
                case 6:
                    modifyUser();
                    break;
                case 7:
                    userService.displayParticipants();
                    break;
                case 8:
                    Event selectedEvent = selectEvent();
                    if (selectedEvent != null) {
                        displayEventSubs(selectedEvent);
                    } else {
                        System.out.println("No event selected.");
                    }
                    break;
                case 9:
                    displayEventsForSelectedParticipant(userService, scanner, eventService);
                    break;
                case 10:
                    deleteUser();
                    break;
                case 11:
                    return createUser();
                case 12:
                    generateReport();
                    break;
                case 0:
                    System.out.println("Exiting Admin Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choix != 0);
        return currentUser;
    }

    //se connnecter
    private User createUser() {
        System.out.println(" ****** Please Enter your informations ************* ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Select your role: 1. Admin 2. Participant");
        int roleChoice = Integer.parseInt(scanner.nextLine());
        Role role = (roleChoice == 1) ? Role.ADMIN : Role.PARTICIPANT;

        User newUser = new User(name, email, role);
        userService.addUser(newUser);

        System.out.println("User added successfully as " + (role == Role.ADMIN ? "Admin." : "Particpant."));
        return newUser;
    }


    // add event
    private  void addEvent() {
        System.out.print("Enter event title: ");
        String title = scanner.nextLine();

        System.out.print("Enter event date (dd-MM-yyyy): ");
        Date date = parseDate(scanner.nextLine());

        System.out.print("Enter event location: ");
        String location = scanner.nextLine();

        System.out.print("Enter event type: ");
        String type = scanner.nextLine();

        Event event = new Event(title, date, location, type);
        eventService.addEvent(event);
        System.out.println("Entities.Event added successfully.");
    }

    //modify event
    private  void modifyEvent() {
        displayEvents();
        System.out.print("Enter the event number to modify: \n ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Enter new event title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new event date (dd-MM-yyyy): ");
        Date date = parseDate(scanner.nextLine());

        System.out.print("Enter new event location: ");
        String location = scanner.nextLine();

        System.out.print("Enter new event type: ");
        String type = scanner.nextLine();

        Event updatedEvent = new Event(title, date, location, type);
        eventService.modifyEvent(index, updatedEvent);
        System.out.println("Entities.Event modified successfully.");
    }

    //delete event
    private  void deleteEvent() {
        displayEvents();
        System.out.print("Enter the event number to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        eventService.deleteEvent(index);
        System.out.println("Entities.Event deleted successfully.");
    }

    //display events
    private void displayEvents() {
        System.out.println("\nEntities.Event List:");
        eventService.displayEvents();
    }

    //Search event
    private  void searchEvents() {
        System.out.print("Enter event date (dd-MM-yyyy) to search (leave blank to skip): ");
        Date date = parseDate(scanner.nextLine());

        System.out.print("Enter event location to search (leave blank to skip): ");
        String location = scanner.nextLine();

        System.out.print("Enter event type to search (leave blank to skip): ");
        String type = scanner.nextLine();

        System.out.println("\nSearch Results:");
        eventService.searchEvents(date, location, type);
    }

    //Modify person detail
    private  void modifyUser(){
        List<User> users = userService.getParticipants();

        if (users.isEmpty()) {
            System.out.println("No participants available to modify.");
            return;
        }

        userService.displayParticipants();
        System.out.print("Enter the participant number to modify: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= users.size()) {
            System.out.println("Invalid participant number.");
            return;
        }

        System.out.print("Enter new participant name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new participant email: ");
        String email = scanner.nextLine();

        System.out.println("Select your role: 1. Admin 2. Participant");
        int roleChoice = Integer.parseInt(scanner.nextLine());
        Role role = (roleChoice == 1) ? Role.ADMIN : Role.PARTICIPANT;

        User updatedUser = new User(name, email, role);
        userService.modifyUser(index, updatedUser);
        System.out.println("Entities.User modified successfully.");
    }

    //display events sub
    private  void displayEventSubs(Event event) {
        eventService.afficherInscriptions(event);
    }
    
    // Display events for a participant
    private void displayEventsForSelectedParticipant(UserServiceInterface userService, Scanner scanner, EventServiceInterface eventService) {
        List<User> users = userService.getUsers();
        ArrayList<User> participants = (ArrayList<User>) users.stream()
                .filter(user -> user.getRole() == Role.PARTICIPANT)
                .collect(Collectors.toList());

        if (participants.isEmpty()) {
            System.out.println("No participants available.");
            return;
        }

        System.out.println("Select a participant:");
        for (int i = 0; i < participants.size(); i++) {
            System.out.println((i + 1) + ". " + participants.get(i).getName());
        }

        int participantIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (participantIndex >= 0 && participantIndex < participants.size()) {
            User selectedParticipant = participants.get(participantIndex);
            eventService.afficherEvenementsPourParticipant(selectedParticipant); // Assuming this method exists in EventServiceInterface
        } else {
            System.out.println("Invalid participant selected.");
        }
    }

    private  void deleteUser() {
        List<User> users = userService.getParticipants();

        if (users.isEmpty()) {
            System.out.println("No participants available to delete.");
            return;
        }
        userService.displayParticipants();
        System.out.print("Enter the participant number to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        userService.removeUser(index);
        System.out.println("Entities.User removed successfully.");

    }

    // add user as participant
    public User loginUser() {
        System.out.println(" ****** Log in as Participant  ************* ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Role role = Role.PARTICIPANT;

        User newUser = new User(name, email, role);
        userService.addUser(newUser);
        System.out.println("User Logged in successfully as Participant.");
        return newUser;  // Return the new user object
    }
 
    //generate report
    private  void generateReport(){
        List<User> participants = userService.getParticipants();
        reportService.generateParticipantsReport(participants, "participants_report.txt");

    }

    //Select Event
    private  Event selectEvent() {
        displayEvents();
        System.out.println("Select an event:");
        int eventIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (eventIndex >= 0 && eventIndex < eventService.getEvents().size()) {
            return eventService.getEvents().get(eventIndex);
        } else {
            System.out.println("Invalid selection.");
            return null;
        }
    }
    private static Date parseDate(String dateStr) {
        if (dateStr.isEmpty()) {
            return new Date();
        }
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy.");
            return new Date();
        }
    }


}
