import java.util.ArrayList;
import java.util.List;

enum Role{
    ADMIN, PARTICIPANT
}

public class User {
    private String name;
    private String email;
    private Role role;
    private List<Event> events;


    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.events = new ArrayList<>();

    }
    public String getName(){
        return name;
    }
    public String setName(String name){
        this.name = name;
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String setEmail(String email){
        this.email = email;
        return email;
    }
    public Role getRole(){
        return role;
    }
    public String setRole(Role role){
        this.role = role;
        String string = role.toString();
        return string;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    @Override
    public String toString(){
        return "["+name+","+email+","+role+"]";
    }





}


