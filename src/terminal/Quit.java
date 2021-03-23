package terminal;

import vault.Util;

public class Quit extends Command{
    @Override
    protected void setName() {
        this.name = "quit";
    }

    @Override
    protected void setDescription() {
        this.description = "kills the running process.";
    }
    @Override
    public void execute() {
        Util.info("Shutting down. \uD83D\uDC4B");
        System.exit(0);
    }
}
