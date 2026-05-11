public class Profile {

    private final String username;
    private final String school;
    private final String sessionName;

    public Profile(String username, String school, String sessionName) {
        this.username = username;
        this.school = school;
        this.sessionName = sessionName;
    }

    public String getUsername() {
        return username;
    }

    public String getSchool() {
        return school;
    }

    public String getSessionName() {
        return sessionName;
    }
}
