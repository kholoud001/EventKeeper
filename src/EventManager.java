import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Class for managing events.
 */
public class EventManager {

    private ArrayList<Event> events;

    public EventManager() {
        events = new ArrayList<>();
    }

    public Event getEvent(int index) {
        if (index >= 0 && index < events.size()) {
            return events.get(index);
        } else {
            System.out.println("Invalid event index.");
            return null;
        }
    }

    public List<Event> getEvents() {
        return events;
    }

    /**
     * Adds a new event to the list.
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Modifies an existing event.
     *
     * @param index The index of the event to modify.
     * @param updatedEvent The updated event details.
     */
    public void modifyEvent(int index, Event updatedEvent) {
        events.set(index, updatedEvent);
    }

    /**
     * Deletes an event from the list.
     *
     * @param index The index of the event to delete.
     */
    public void deleteEvent(int index) {
        if (index >= 0 && index < events.size()) {
            events.remove(index);
        } else {
            System.out.println("Invalid event index.");
        }
    }

    /**
     * Displays all events.
     */
    public void displayEvents() {
        if (events.isEmpty()) {
            System.out.println("No events found.");
        } else {
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i));
            }
        }
    }

    /**
     * Searches for events based on the date, location, or type.
     *
     * @param date The date to search for.
     * @param location The location to search for.
     * @param type The type to search for.
     */
    public void searchEvents1(Date date, String location, String type) {
        ArrayList<Event> filteredEvents = events.stream()
                .filter(e -> (date == null || e.getDate().equals(date)) &&
                        (location == null || e.getLocation().equalsIgnoreCase(location)) &&
                        (type == null || e.getType().equalsIgnoreCase(type)))
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredEvents.isEmpty()) {
            System.out.println("No matching events found.");
        } else {
            filteredEvents.forEach(System.out::println);
        }
    }

    public void searchEvents(Date date, String location, String type) {
        ArrayList<Event> filteredEvents = new ArrayList<>();

        for (int i=0; i<events.size(); i++) {
            Event event = events.get(i);
            boolean matches = false;

            if (event.getDate().equals(date)) {
                matches = true;
            }
            if (event.getLocation().equalsIgnoreCase(location)) {
                matches = true;
            }
            if (event.getType().equalsIgnoreCase(type)) {
                matches = true;
            }
            if (matches) {
                filteredEvents.add(event);
            }
        }
        if (filteredEvents.isEmpty()) {
            System.out.println("No matching events found.");
        } else {
            filteredEvents.forEach(System.out::println);
        }

    }

    public void registerUserForEvent(int index, User user) {
        if (index >= 0 && index < events.size()) {
            Event event = events.get(index);
            event.addParticipant(user);
            System.out.println(user.getName() + " has been registered for the event: " + event.getTitle());
        } else {
            System.out.println("Invalid event number.");
        }
    }

    public void unregisterUserFromEvent(int index, User user) {
        if (index >= 0 && index < events.size()) {
            Event event = events.get(index);
            if (event.removeParticipant(user)) {
                System.out.println(user.getName() + " has been unregistered from the event: " + event.getTitle());
            } else {
                System.out.println(user.getName() + " is not registered for this event.");
            }
        } else {
            System.out.println("Invalid event number.");
        }
    }

    public void afficherInscriptions(Event event) {
        List<User> participants = event.getParticipants();

        if (participants.isEmpty()) {
            System.out.println("Il n'y a pas d'inscriptions pour cet événement.");
        } else {
            System.out.println("Participants inscrits pour l'événement " + event.getTitle() + ":");
            for (int i = 0; i < participants.size(); i++) {
                System.out.println((i + 1) + ". " + participants.get(i).getName());
            }
        }
    }

}
