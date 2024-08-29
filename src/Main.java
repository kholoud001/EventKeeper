import  java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Create a new Date object for the event date
        Date eventDate = new Date();

        // Create an instance of the Event class
        Event myEvent = new Event("Company Meeting", eventDate, "Conference Room A", "Business");

        // Print the event details using the toString() method
        System.out.println(myEvent.toString());

//        // Access and modify event properties using getters and setters
//        myEvent.setTitle("Annual Company Meeting");
//        myEvent.setLocation("Main Conference Hall");
//
//        // Print the updated event details
//        System.out.println(myEvent.toString());

    }
}