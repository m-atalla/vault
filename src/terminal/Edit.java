package terminal;

import vault.CryptoManager;
import vault.FileManager;
import vault.Util;

public class Edit extends Command {

    private CryptoManager cryptoManager;
    private String storagePath;

    public Edit(CryptoManager cryptoManager, String storagePath) {
        super();

        this.cryptoManager = cryptoManager;

        this.storagePath = storagePath;
    }
    @Override
    protected void setDescription() {
        this.description = "edit a given stored domain.";
    }

    @Override
    protected void setName() {
        this.name = "edit";
    }

    @Override
    public void execute() {
        System.out.println("Enter domain: ");
        String domain = Util.getInput();

        System.out.println("Enter new password: ");
        String password = Util.getInput();

        try {
            FileManager.write(Util.getPath(this.storagePath, domain),
                    this.cryptoManager.encrypt(password));

            Util.success("domain " + domain + " was edited successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
