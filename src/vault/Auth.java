package vault;

import java.nio.file.Paths;
import java.util.Scanner;

public class Auth {
    private User sessionUser;
    private boolean authenticated;

    /**
     * First method to run authenticating users.
     */
    public Auth() {
        // Initialize authentication status
        this.authenticated = false;

        System.out.println("(1) Login \t(2) Register \t(3) Exit");

        System.out.print("> ");

        String input = new Scanner(System.in).next().trim();

        switch (input) {
            case "1" -> this.login();
            case "2" -> this.register();
            case "3" -> quit("\uD83D\uDC4B"); // Wave unicode
            default -> quit("Unexpected option.");
        }
    }

    public void quit(String message) {
        System.out.println(message);
        System.exit(0);
    }

    public User getSessionUser() {
        return this.sessionUser;
    }

    /**
     * User login sequence
     */
    private void login (){
        System.out.print("username: ");

        String username = new Scanner(System.in).next().trim();

        System.out.print("password: ");

        String password = new Scanner(System.in).next().trim();

        User user = UsersList.findUser(username);

        if (user == null) {
            System.err.println("User not found.");
            return;
        }

        // Attempt password
        if (!CryptoManager.verifyHash(password, user.getPassword())) {
            System.err.println("Failed to login");
            return;
        }

        this.sessionUser = user;

        UsersList.freeList();

        Util.success("Successful login!");

        Util.helpPrompt();

        this.authenticated = true;
    }

    /**
     * User registration sequence
     */
    public void register() {
        Scanner scan = new Scanner(System.in);

        System.out.print("username: ");

        String username = scan.next().trim();

        if (UsersList.findUser(username) != null){
            System.err.println("Username already exists");
            return;
        }

        System.out.print("Password: ");

        String password = scan.next().trim();

        String passwordHash = CryptoManager.hash(password);

        System.out.println("Storage path: (Type 'pwd' to use current directory)");

        System.out.print("> ");

        String storagePath = scan.next().trim();

        if (storagePath.equals("pwd")) {
            storagePath = Paths.get(username).toAbsolutePath().toString();
        }

        FileManager.createDirectory(storagePath);

        User user = new User(username, passwordHash, storagePath);

        UsersList.add(user);

        this.sessionUser = user;

        UsersList.freeList();

        Util.success("Successful register!");

        Util.helpPrompt();

        this.authenticated = true;
    }

    /**
     * Authentication status
     */
    public boolean isAuthenticated() {
        return this.authenticated;
    }
}
