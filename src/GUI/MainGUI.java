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
        User currentUser = createUser();
        while (true) {
            if (currentUser.getRole() == Role.ADMIN) {
                System.out.println("Welcome to the Admin GUI"+currentUser.getName());
                currentUser = adminGUI.displayMenuAdmin();
            } else if (currentUser.getRole() == Role.PARTICIPANT) {
                System.out.println("Welcome to the Participant GUI"+currentUser.getName());
                currentUser = participantGUI.displayMenuParticipant(currentUser);            }

            System.out.println("Do you want to switch roles? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("no")) {
                break;
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

        System.out.println("User added successfully as " + (role == Role.ADMIN ? "Admin." : "Particpant."));
        return newUser;
    }

}
