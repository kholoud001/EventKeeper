import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Main application class for managing events.
 */
public class Main {
    private static EventManager eventManager = new EventManager();
    private static  UserManager userManager = new UserManager();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    public static void main(String[] args) {

        User currentUser = createUser1();
      //  User currentUser = new User("Admin", "admin@example.com", Role.ADMIN);
       // userManager.addUser(currentUser);

        ArrayList<User> users = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        boolean running = true;

        while (running) {
            if(currentUser.getRole()==Role.ADMIN) {
                printAdminMenu();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
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
                        displayUsers();
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
                        displayEventsForSelectedParticipant(userManager, scanner, eventManager);

                        break;
                    case 10:
                        deleteUser();
                        break;
                    case 11:
                        currentUser = loginUser();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            if(currentUser.getRole()==Role.PARTICIPANT) {
                printParticipantMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {

                    case 1:
                        displayEventsWithOptions(currentUser);
                        break;
                    case 2:
                        searchEvents();
                        break;
//                    case 3:
//                        modifyUser();
//                        break;
                    case 3:
                        currentUser = createUser1();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }



    private static void printAdminMenu() {
        System.out.println("\n ***Event Management Section: *** ");
        System.out.println("1. Add Event");
        System.out.println("2. Modify Event");
        System.out.println("3. Delete Event");
        System.out.println("4. Display All Events");
        System.out.println("5. Search Events");
        System.out.println("\n ***  Participant Management Section: *** ");
        System.out.println("6. Modifier les détails d'un participant\"");
        System.out.println("7. Display All Participants");
        System.out.println(("8. Display the Participants for an event"));
        System.out.println("9. Display Events of a Participant");
        System.out.println("10. Delete a participant");
        System.out.println("11 switch account");
        System.out.println("0. Exit");

        System.out.print("Choose an option: ");
    }

    private static void printParticipantMenu() {
        System.out.println("\n ****  Participant Menu: ****");
        System.out.println("1. Display All Events");
        System.out.println("2. Search Events");
//        System.out.println("3. Modifier les détails d'un participant");
        System.out.println("3. Switch account");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static User createUser1() {
        System.out.println(" ****** Please Enter your informations ************* ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Select your role: 1. Admin 2. Participant");
        int roleChoice = Integer.parseInt(scanner.nextLine());
        Role role = (roleChoice == 1) ? Role.ADMIN : Role.PARTICIPANT;

        User newUser = new User(name, email, role);
        userManager.addUser(newUser);

        System.out.println("User added successfully as " + (role == Role.ADMIN ? "Admin." : "Particpant."));
        return newUser;
    }

    private static User loginUser() {

            System.out.println(" ****** Log in as Participant  ************* ");
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            Role role = Role.PARTICIPANT;

            User newUser = new User(name, email, role);
            userManager.addUser(newUser);

            System.out.println("User Logged in successfully as Participant.");
            return newUser;

    }



    private static void addEvent() {
        System.out.print("Enter event title: ");
        String title = scanner.nextLine();

        System.out.print("Enter event date (dd-MM-yyyy): ");
        Date date = parseDate(scanner.nextLine());

        System.out.print("Enter event location: ");
        String location = scanner.nextLine();

        System.out.print("Enter event type: ");
        String type = scanner.nextLine();

        Event event = new Event(title, date, location, type);
        eventManager.addEvent(event);
        System.out.println("Event added successfully.");
    }

    private static void modifyEvent() {
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
        eventManager.modifyEvent(index, updatedEvent);
        System.out.println("Event modified successfully.");
    }

    private static void deleteEvent() {
        System.out.print("Enter the event number to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        eventManager.deleteEvent(index);
        System.out.println("Event deleted successfully.");
    }

    private static void displayEvents() {
        System.out.println("\nEvent List:");
        eventManager.displayEvents();
    }

    private static void displayEventSubs(Event event) {
        eventManager.afficherInscriptions(event);
    }

    public static void displayEventsForSelectedParticipant(UserManager userManager, Scanner scanner, EventManager eventManager) {
        ArrayList<User> users = userManager.getUsers();
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
            eventManager.afficherEvenementsPourParticipant(selectedParticipant);
        } else {
            System.out.println("Invalid participant selected.");
        }
    }

    private static Event selectEvent() {
        displayEvents();

        System.out.println("Select an event:");

        int eventIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (eventIndex >= 0 && eventIndex < eventManager.getEvents().size()) {
            return eventManager.getEvents().get(eventIndex);
        } else {
            System.out.println("Invalid selection.");
            return null;
        }
    }


    private static void displayEventsWithOptions(User currentUser) {
        System.out.println("\nEvent List:");
        eventManager.displayEvents();

        System.out.println("\nWould you like to:");
        System.out.println("1. Register for an Event");
        System.out.println("2. Unregister from an Event");
        System.out.println("0. Return to Menu");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                registerForEvent(currentUser);
                break;
            case 2:
                unregisterFromEvent(currentUser);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. Returning to menu.");
        }
    }

    private static void registerForEvent(User user) {
        System.out.print("Enter the event number to register: ");
        int eventIndex = Integer.parseInt(scanner.nextLine()) - 1;

        Event event = eventManager.getEvent(eventIndex);

        if (event.isParticipantRegistered(user)) {
            System.out.println("You are already registered for this event.");
        } else {
            eventManager.registerUserForEvent(eventIndex, user);
            System.out.println("You have registered for the event successfully.");
        }
    }


    private static void unregisterFromEvent(User user) {
        System.out.print("Enter the event number to unregister: ");
        int eventIndex = Integer.parseInt(scanner.nextLine()) - 1;
        eventManager.unregisterUserFromEvent(eventIndex, user);
        System.out.println("You have unregistered from the event successfully.");
    }

    private static void searchEvents() {
        System.out.print("Enter event date (dd-MM-yyyy) to search (leave blank to skip): ");
        Date date = parseDate(scanner.nextLine());

        System.out.print("Enter event location to search (leave blank to skip): ");
        String location = scanner.nextLine();

        System.out.print("Enter event type to search (leave blank to skip): ");
        String type = scanner.nextLine();

        System.out.println("\nSearch Results:");
        eventManager.searchEvents(date, location, type);
    }

    private static Date parseDate(String dateStr) {
        if (dateStr.isEmpty()) {
            return null;
        }
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy.");
            return null;
        }
    }

    private static void     displayUsers() {
        System.out.println("\nParticipant List:");
       userManager.displayParticipants();
       // userManager.displayUsers();
    }

    //Modify person detail
    private static void modifyUser(){
        displayUsers();
        System.out.print("Enter the participant number to modify: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Enter new participant name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new participant email): ");
        String email = scanner.nextLine();

        System.out.println("Select your role: 1. Admin 2. Participant");
        int roleChoice = Integer.parseInt(scanner.nextLine());
        Role role = (roleChoice == 1) ? Role.ADMIN : Role.PARTICIPANT;

        User updatedUser = new User(name,email,role);
        userManager.modifyUser(index, updatedUser);
        System.out.println("User modified successfully.");

    }

    private static void deleteUser() {
        displayUsers();
        System.out.print("Enter the participant number to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        userManager.removeUser(index);
        System.out.println("User removed successfully.");

    }


}
