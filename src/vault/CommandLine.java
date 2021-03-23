package vault;

import terminal.*;
import terminal.List;
import java.util.*;

public class CommandLine {

    private User sessionUser;
    private final CryptoManager cryptoManager;
    private final Auth auth;
    private Map<String, Command> Commands;

    public CommandLine() {
        cryptoManager = new CryptoManager();
        auth = new Auth();
    }


    public void start() {
        // Get authenticated user resolved from resolving login or register.
        if (sessionUser == null) {
            sessionUser = auth.getSessionUser();
            this.registerCommands();
        }

        // Parse input as long as the user is authenticated
        while (auth.isAuthenticated()) this.parse(Util.getInput());
    }

    /**
     * Registers user commands
     */
    public void registerCommands() {
        Commands = new LinkedHashMap<>();

        Commands.put("list", new List(sessionUser.getUsername()));

        Commands.put("edit", new Edit(cryptoManager, sessionUser.getStoragePath()));

        Commands.put("store", new Store(cryptoManager, sessionUser.getStoragePath()));

        Commands.put("view", new View(cryptoManager, sessionUser.getStoragePath()));

        Commands.put("delete", new Delete(sessionUser.getStoragePath()));

        Commands.put("quit", new Quit());

        Commands.put("help", new Help(Commands.values()));
    }

    public void parse(String command) {
        if (!Commands.containsKey(command)) {
            Util.error("Invalid commands, type 'help' to see available commands.");
            return;
        }

        Commands.get(command).execute();
    }
}
