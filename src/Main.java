import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Main application class for managing events.
 */
public class Main {
    private static EventManager eventManager = new EventManager();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
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
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nEvent Management Menu:");
        System.out.println("1. Add Event");
        System.out.println("2. Modify Event");
        System.out.println("3. Delete Event");
        System.out.println("4. Display All Events");
        System.out.println("5. Search Events");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
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
        System.out.print("Enter the event number to modify: ");
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
}
