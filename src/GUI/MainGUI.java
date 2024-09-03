package GUI;

import Entities.User;
import Enums.Role;
import services.interfaces.UserServiceInterface;

import java.util.Scanner;

public class MainGUI {

    private final UserServiceInterface userService;
    private final Scanner scanner;
    private final ParticipantGUI participantGUI;
    private final AdminGUI adminGUI;

    public MainGUI(UserServiceInterface userService, Scanner scanner, ParticipantGUI participantGUI, AdminGUI adminGUI) {
        this.userService = userService;
        this.scanner = scanner;
        this.participantGUI = participantGUI;
        this.adminGUI = adminGUI;
    }

    public void start() {

        while (true) {
            User currentUser = createUser();
            if (currentUser != null) {
                System.out.println("Welcome " + currentUser.getName() + " (" + currentUser.getRole().toString() + ")");

                if (currentUser.getRole() == Role.ADMIN) {
                    adminGUI.displayMenuAdmin();
                } else if (currentUser.getRole() == Role.PARTICIPANT) {
                    participantGUI.displayMenuParticipant();
                } else {
                    System.out.println("Invalid role. Exiting...");
                    break;
                }

                System.out.print("Do you want to switch accounts? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("yes")) {
                    loginUser();
                } else {
                    System.out.println("Exiting...");
                    break;
                }
            }
        }
    }


    private User createUser() {
        System.out.println(" ****** Please Enter your informations ************* ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Select your role: 1. Admin 2. Participant");
        int roleChoice = Integer.parseInt(scanner.nextLine());
        Role role = (roleChoice == 1) ? Role.ADMIN : Role.PARTICIPANT;

        User newUser = new User(name, email, role);
        userService.addUser(newUser);

        System.out.println("Entities.User added successfully as " + (role == Role.ADMIN ? "Admin." : "Particpant."));
        return newUser;
    }


    public void loginUser() {
        System.out.println(" ****** Log in as Participant  ************* ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Role role = Role.PARTICIPANT;

        User newUser = new User(name, email, role);
        userService.addUser(newUser);

        System.out.println("Entities.User Logged in successfully as Participant.");

    }


}
