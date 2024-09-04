package GUI;

import Entities.Event;
import Entities.User;
import Enums.Role;
import services.interfaces.EventServiceInterface;
import services.interfaces.UserServiceInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ParticipantGUI {

    private final UserServiceInterface userService;
    private final EventServiceInterface eventService;
    private final Scanner scanner;
    private static  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private User currentUser;



    public ParticipantGUI(UserServiceInterface userService, EventServiceInterface eventService, Scanner scanner) {
        this.userService = userService;
        this.eventService = eventService;
        this.scanner = scanner;
    }

    //Participant Menu
    public User displayMenuParticipant(User currentUser) {
        int choice;
        do {
            System.out.println("\n***** Participant Menu *****");
            System.out.println("1. Display All Events");
            System.out.println("2. Search Events");
            System.out.println("3. Switch account");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayEventsWithOptions(currentUser);
                    break;
                case 2:
                    searchEvents();
                    break;
                case 3:
                    return createUser();
                case 0:
                    System.out.println("Exiting Participant Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        return currentUser;
    }

    private User createUser() {
        System.out.println(" ****** Please Enter your information ************* ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Select your role: 1. Admin 2. Participant");
        int roleChoice = Integer.parseInt(scanner.nextLine());
        Role role = (roleChoice == 1) ? Role.ADMIN : Role.PARTICIPANT;

        User newUser = new User(name, email, role);
        userService.addUser(newUser);

        System.out.println("User added successfully as " + (role == Role.ADMIN ? "Admin." : "Participant."));
        return newUser;
    }


    // add user as participant
    public   void  loginUser() {

        System.out.println(" ****** Log in as Participant  ************* ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Role role = Role.PARTICIPANT;

        User newUser = new User(name, email, role);
        userService.addUser(newUser);

        System.out.println("Entities.User Logged in successfully as Participant.");

    }


    //display events with option to register and unregister
    private void displayEventsWithOptions(User participant) {
        System.out.println("\nEvent List:");
        System.out.println("Welcome to the sub Participant GUI "+participant.getName());

        eventService.displayEvents();

        System.out.println("\nWould you like to:");
        System.out.println("1. Register for an Entities.Event");
        System.out.println("2. Unregister from an Entities.Event");
        System.out.println("0. Return to Menu");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                registerForEvent(participant);
                break;
            case 2:
                unregisterFromEvent(participant);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. Returning to menu.");
        }
    }


private void registerForEvent(User user) {
    if (user == null) {
        System.out.println("Error: User is not logged in.");
        return;
    }

    System.out.print("Enter the event number to register: ");
    int eventIndex = Integer.parseInt(scanner.nextLine()) - 1;

    Event event = eventService.getEvent(eventIndex);
    if (event.isParticipantRegistered(user)) {
        System.out.println("You are already registered for this event.");
    } else {
        eventService.registerUserForEvent(eventIndex, user);
        System.out.println("You have registered for the event successfully.");
    }
}


    private  void unregisterFromEvent(User user) {
        System.out.print("Enter the event number to unregister: ");
        int eventIndex = Integer.parseInt(scanner.nextLine()) - 1;
        eventService.unregisterUserFromEvent(eventIndex, user);
        System.out.println("You have unregistered from the event successfully.");
    }

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

    private  Date parseDate(String dateStr) {
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
