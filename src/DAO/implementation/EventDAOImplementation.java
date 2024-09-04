package DAO.implementation;

import DAO.interfaces.EventDAOInterface;
import Entities.Event;
import Entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAOImplementation implements EventDAOInterface{
    private ArrayList<Event> events;

    public EventDAOImplementation() {
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

    /**
     * Registers a user for a specified event.
     *
     * @param index The index of the event in the events list. It should be within the valid range
     *              (0 to size of events list - 1).
     * @param user The user to be registered for the event. The user's details and event list
     *             are used to determine if they are already registered.
     */
    public void registerUserForEvent(int index, User user) {
        if (index >= 0 && index < events.size()) {
            Event event = events.get(index);
            if (!user.getEvents().contains(event)) {
                user.addEvent(event);
                event.addParticipant(user);
                System.out.println(user.getName() + " has been registered for the event: " + event.getTitle());
            } else {
                System.out.println(user.getName() + " is already registered for the event: " + event.getTitle());
            }
        } else {
            System.out.println("Invalid event number.");
        }
    }


    /**
     * Unregisters a user for a specified event.
     *
     * @param index The index of the event in the events list. It should be within the valid range
     *              (0 to size of events list - 1).
     * @param user The user to be unregistered for the event.
     */
    public void unregisterUserFromEvent(int index, User user) {
        if (index >= 0 && index < events.size()) {
            Event event = events.get(index);
            if (event.removeParticipant(user)) {
                // System.out.println(user.getName() + " has been unregistered from the event: " + event.getTitle());
            } else {
                System.out.println(user.getName() + " is not registered for this event.");
            }
        } else {
            System.out.println("Invalid event number.");
        }
    }

    /**
     * Display participants of an event.
     *
     * @param event the event's participants list to display.
     *
     */
    public void afficherInscriptions(Event event) {
        List<User> participants = event.getParticipants();

        if (participants.isEmpty()) {
            System.out.println("There are no registrations for this event.");
        } else {
            System.out.println("Participants registered for the event" + event.getTitle() + ":");
            for (int i = 0; i < participants.size(); i++) {
                System.out.println((i + 1) + ". " + participants.get(i).getName());
            }
        }
    }
    /**
     * Display a participant's registered events.
     *
     * @param participant the participant id's registered events to display.
     *
     */

    public void afficherEvenementsPourParticipant(User participant) {
        List<Event> registeredEvents = participant.getEvents();

        if (registeredEvents.isEmpty()) {
            System.out.println(participant.getName() + " Not registered for any event.");
        } else {
            System.out.println("Events that \" + participant.getName() + \" is registered for:  ");
            for (int i = 0; i < registeredEvents.size(); i++) {
                System.out.println((i + 1) + ". " + registeredEvents.get(i).getTitle());
            }
        }
    }




}
