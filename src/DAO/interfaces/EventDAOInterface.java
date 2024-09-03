package DAO.interfaces;

import Entities.Event;
import Entities.User;

import java.util.Date;
import java.util.List;

public interface EventDAOInterface {
    // Retrieves an event by index
    Event getEvent(int index);

    // Retrieves all events
    List<Event> getEvents();

    // Adds a new event
    void addEvent(Event event);

    // Modifies an existing event
    void modifyEvent(int index, Event updatedEvent);

    // Deletes an event by index
    void deleteEvent(int index);

    // Displays all events
    void displayEvents();

    // Searches for events based on date, location, or type
    void searchEvents(Date date, String location, String type);

    // Registers a user for an event
    void registerUserForEvent(int index, User user);

    // Unregisters a user from an event
    void unregisterUserFromEvent(int index, User user);

    // Displays all participants registered for an event
    void afficherInscriptions(Event event);

    // Displays all events a participant is registered for
    void afficherEvenementsPourParticipant(User participant);



}
