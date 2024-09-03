package DAO.interfaces;

import Entities.User;

import java.util.ArrayList;

public interface UserDAOInterface {
    // Retrieves the list of all users
    ArrayList<User> getUsers();

    // Retrieves the list of participants
    ArrayList<User> getParticipants();

    // Adds a new user
    void addUser(User user);

    // Modifies an existing user
    void modifyUser(int index, User updatedUser);

    // Deletes an existing user
    void removeUser(int index);

    // Displays a list of all users
    void displayUsers();

    // Displays a list of all participants
    void displayParticipants();

    // Checks if the given user has an admin role
    boolean isAdmin(User user);
}
