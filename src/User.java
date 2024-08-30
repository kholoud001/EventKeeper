enum Role{
    ADMIN, PARTICIPANT
}

public class User {
    private String name;
    private String email;
    private Role role;

    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
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
    public String toString(){
        return "["+name+","+email+","+role+"]";
    }

}


