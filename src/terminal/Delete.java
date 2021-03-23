package terminal;

import vault.FileManager;
import vault.Util;

import java.io.IOException;

public class Delete extends Command{

    private String storagePath;

    public Delete(String storagePath) {
        super();
        this.storagePath = storagePath;
    }

    @Override
    protected void setName() {
        this.name = "delete";
    }

    @Override
    protected void setDescription() {
        this.description = "delete a specified domain";
    }

    @Override
    public void execute() {
        System.out.println("Enter domain to be deleted: ");
        String domain = Util.getInput();
        try {
            if (FileManager.delete(Util.getPath(storagePath, domain))) {
                Util.success("domain " + domain + " was deleted successfully.");
            }else {
                System.err.println("Storage file does not exist.");
            }
        } catch (IOException e) {
            System.err.println("Failed to delete file" + e.getMessage());
        }

    }
}
