import  java.util.Date;

public class Event {
    private String title;
    private Date date;
    private String location;
    private String type;

    public Event(String title, Date date, String location, String type) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.type = type;
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

    @Override
    public String toString() {

        return "[" + title + "," + date + ", " + location + ", " + type + "]";
    }

}
