package vault;

public class User {
    private String username;

    private String password;

    private String storagePath;


    public User(String username, String password, String storagePath) {
        this.username = username;
        this.password = password;
        this.storagePath = storagePath;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
