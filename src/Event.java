import java.util.ArrayList;
import  java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class Event {
    private String title;
    private Date date;
    private String location;
    private String type;
    private List<User> participants;



    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Event(String title, Date date, String location, String type) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.type = type;
        this.participants = new ArrayList<>();

    }

    // Getters and setters for each attribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<User> getParticipants() {
        return participants;
    }

    public void addParticipant(User user) {
        if (!participants.contains(user)) {
            participants.add(user);
        } else {
            System.out.println(user.getName() + " is already registered for this event.");
        }
    }

    public boolean removeParticipant(User user) {
        return participants.remove(user);
    }

    public boolean isParticipantRegistered(User user) {
        return participants.contains(user);
    }

    public String toString() {
        return "[" + title + ", " + dateFormat.format(date) + ", " + location + ", " + type + "]";
    }

}
