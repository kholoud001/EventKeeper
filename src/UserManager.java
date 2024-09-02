import java.util.ArrayList;

/**
 * Class for managing users.
 */
public class UserManager {
    private ArrayList<User> users;

    public UserManager() {
        users = new ArrayList<>();
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
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("There are no users");
        }else {
            for (int i = 0; i < users.size(); i++) {
                System.out.println((i + 1) + ". " + users.get(i));
            }
        }
    }
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


    public boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }



}

