package services.interfaces;

import Entities.User;

import java.util.List;


public interface UserServiceInterface {


    List<User> getUsers();

    List<User> getParticipants();

    void addUser(User user);

    void modifyUser(int index, User updatedUser);

    void removeUser(int userId);

    void displayUsers();

    void displayParticipants();

    boolean isAdmin(User user);
}
