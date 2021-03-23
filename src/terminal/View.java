package terminal;

import vault.CryptoManager;
import vault.FileManager;
import vault.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class View extends Command{

    private CryptoManager cryptoManager;

    private String storagePath;

    public View(CryptoManager cryptoManager, String storagePath) {
        super();
        this.cryptoManager = cryptoManager;
        this.storagePath = storagePath;
    }

    @Override
    protected void setName() {
        this.name = "view";
    }

    @Override
    protected void setDescription() {
        this.description = "view a stored password for a specified domain.";
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter domain: ");

            String domain = Util.getInput();

            String encryption = FileManager.readAllBytes(Util.getPath(storagePath, domain));

            Util.info(cryptoManager.decrypt(encryption.getBytes()));
        }catch (IOException e) {
            System.err.println("No such domain found.");
        } catch (GeneralSecurityException e) {
            System.err.println("Failed to decrypt, shutting down.");
            System.exit(2);
        }
    }
}