package services.implementation;

import DAO.interfaces.UserDAOInterface;
import Entities.User;
import Enums.Role;
import services.interfaces.UserServiceInterface;

import java.util.List;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserServiceInterface {
    private final UserDAOInterface userDAO;

    // Constructor Injection of UserDAOInterface
    public UserServiceImpl(UserDAOInterface userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public List<User> getParticipants() {
        return userDAO.getUsers().stream()
                .filter(user -> user.getRole() == Role.PARTICIPANT)
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void modifyUser(int index, User updatedUser) {
        userDAO.modifyUser(index, updatedUser);
    }


    @Override
    public void removeUser(int userId) {
        userDAO.removeUser(userId);
    }

    @Override
    public void displayUsers() {
        List<User> users = userDAO.getUsers();
        if (users.isEmpty()) {
            System.out.println("There are no users");
        } else {
            for (int i = 0; i < users.size(); i++) {
                System.out.println((i + 1) + ". " + users.get(i));
            }
        }
    }

    @Override
    public void displayParticipants() {
        List<User> participants = getParticipants();
        if (participants.isEmpty()) {
            System.out.println("There are no participants.");
        } else {
            for (int i = 0; i < participants.size(); i++) {
                System.out.println((i + 1) + ". " + participants.get(i).getName());
            }
        }
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }







}


