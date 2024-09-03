package services.implementation;

import DAO.interfaces.EventDAOInterface;
import Entities.Event;
import Entities.User;
import services.interfaces.EventServiceInterface;

import java.util.Date;
import java.util.List;

public class EventServiceImpl implements EventServiceInterface {

    private final EventDAOInterface eventDAO;

    // Constructor Injection of EventDAOInterface
    public EventServiceImpl(EventDAOInterface eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public Event getEvent(int index) {
        return eventDAO.getEvent(index);
    }

    @Override
    public List<Event> getEvents() {
        return eventDAO.getEvents();
    }


    @Override
    public void addEvent(Event event) {
        eventDAO.addEvent(event);
    }

    @Override
    public void modifyEvent(int index, Event updatedEvent) {
        eventDAO.modifyEvent(index, updatedEvent);
    }

    @Override
    public void deleteEvent(int index) {
        eventDAO.deleteEvent(index);
    }

    @Override
    public void displayEvents() {
        eventDAO.displayEvents();
    }

    @Override
    public void searchEvents(Date date, String location, String type) {
        eventDAO.searchEvents(date, location, type);
    }

    @Override
    public void registerUserForEvent(int index, User user) {
        eventDAO.registerUserForEvent(index, user);
    }

    @Override
    public void unregisterUserFromEvent(int index, User user) {
        eventDAO.unregisterUserFromEvent(index, user);
    }

    @Override
    public void afficherInscriptions(Event event) {
        eventDAO.afficherInscriptions(event);
    }

    @Override
    public void afficherEvenementsPourParticipant(User participant) {
        eventDAO.afficherEvenementsPourParticipant(participant);
    }



}
