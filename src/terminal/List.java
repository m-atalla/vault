package terminal;

import vault.FileManager;
import vault.Util;

import java.io.File;

public class List extends Command {

    private String storagePath;

    public List(String storagePath) {
        super();
        this.storagePath = storagePath;
    }

    @Override
    protected void setName() {
        this.name = "list";
    }

    @Override
    protected void setDescription() {
        this.description = "lists all domains stored.";
    }

    @Override
    public void execute() {
        Util.info("Domains stored:");

        // Get files stored in the user storage directory in File array
        File[] files = FileManager.getDirectoryFiles(this.storagePath);

        // Handles empty array case
        if (files.length == 0) {
            System.out.println("Empty..");
            return;
        }

        // Files (domains) listing
        for (File file : files) {
            System.out.println("\t" + file.getName());
        }
    }
}
