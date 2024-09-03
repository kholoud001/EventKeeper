package services.interfaces;

import Entities.Event;
import Entities.User;

import java.util.Date;
import java.util.List;

public interface EventServiceInterface {
    Event getEvent(int index);

    List<Event> getEvents();

    void addEvent(Event event);

    void modifyEvent(int index, Event updatedEvent);

    void deleteEvent(int index);

    void displayEvents();

    void searchEvents(Date date, String location, String type);


    void registerUserForEvent(int index, User user);

    void unregisterUserFromEvent(int index, User user);

    void afficherInscriptions(Event event);

    void afficherEvenementsPourParticipant(User participant);
}
