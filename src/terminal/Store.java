package terminal;

import vault.CryptoManager;
import vault.FileManager;
import vault.Util;

public class Store extends Command {
    private final CryptoManager cryptoManager;

    private final String storagePath;

    public Store(CryptoManager cryptoManager, String storagePath) {
        super();
        this.cryptoManager = cryptoManager;
        this.storagePath = storagePath;
    }

    @Override
    protected void setName() {
        this.name = "store";
    }

    @Override
    protected void setDescription() {
        this.description = "stores a new domain password.";
    }

    @Override
    public void execute() {
        System.out.println("Enter domain: ");

        String domain = Util.getInput();

        System.out.println("Enter password: ");

        String password = Util.getInput();

        try {
            FileManager.write(Util.getPath(storagePath, domain),cryptoManager.encrypt(password));
            Util.success("domain " + domain + " was added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
