package DAO.implementation;

import DAO.interfaces.UserDAOInterface;
import Entities.User;
import Enums.Role;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserDAOImplementation implements UserDAOInterface {
    private ArrayList<User> users;

    public UserDAOImplementation() {
        users = new ArrayList<>();
    }
    /**
     * Retrieves the list of all users.
     *
     * @return An ArrayList containing all User objects.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Retrieves the list of participants.
     *
     * @return An ArrayList containing User objects with the role of PARTICIPANT.
     */
    public ArrayList<User> getParticipants() {
        return (ArrayList<User>) users.stream()
                .filter(user -> user.getRole() == Role.PARTICIPANT)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new user to the list.
     *
     * @param user The user to be added.
     */
    public void addUser(User user) {
        users.add(user);
    }
    /**
     * Modifies an existing user.
     * @param index The index of the user to modify.
     * @param updatedUser The updated user details.
     */
    public void modifyUser(int index, User updatedUser) {
        users.set(index, updatedUser);
    }
    /**
     * Deletes an existing user.
     * @param index The index of the user to delete.
     */
    public void removeUser(int index) {
        users.remove(index);
    }

    /**
     * Displays a list of all the users.
     *
     */
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("There are no users");
        }else {
            for (int i = 0; i < users.size(); i++) {
                System.out.println((i + 1) + ". " + users.get(i));
            }
        }
    }

    /**
     * Displays a list of all participants.
     *
     */
    public void displayParticipants() {
        int count = 0;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole() == Role.PARTICIPANT) {
                count++;
                System.out.println(count + ". " + users.get(i).getName());
            }
        }
        if (count == 0) {
            System.out.println("There are no participants.");
        }
    }

    /**
     * Checks if the given user has an admin role.
     *
     * @param user The User object whose role is to be checked.
     * @return true if the user has the role of ADMIN, false otherwise.
     */
    public boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }
}
