package vault;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class UsersList {
    private static ArrayList<User> users;
    private static Path document = Paths.get("users.json");

    public static ArrayList<User> getUsers(){
        try {
            User[] usersArr = new Gson().fromJson(FileManager.readAllBytes(document.toString()), User[].class);

            users = new ArrayList<>(Arrays.asList(usersArr));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void add(User newUser) {
        if (users == null) getUsers();

        users.add(newUser);

        save();
    }

    private static void save() {
        try {
            // creates Gson instance with pretty printing
            GsonBuilder jsonBuild = new GsonBuilder();

            jsonBuild.setPrettyPrinting();

            Gson json = jsonBuild.create();

            Files.write(document, json.toJson(users).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User findUser(String username) {
        User user = null;

        for (User item : getUsers()) {
            if (item.getUsername().equals(username)) {
                user = item;
                break;
            }
        }
        return user;
    }

    /**
     * Clears users list from memory
     */
    public static void freeList() {
        users.clear();
    }
}
