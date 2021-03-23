package terminal;

import java.util.Collection;

public class Help extends Command {

    Collection<Command> availableCommands;

    public Help(Collection<Command> commands) {
        this.availableCommands = commands;
    }

    @Override
    protected void setDescription() {
        this.description = "list all available commands";
    }

    @Override
    protected void setName() {
        this.name = "help";
    }

    @Override
    public void execute() {
        for (Command command : this.availableCommands) {
            System.out.println(command.getName() + "\t\t" + command.getDescription());
        }
    }
}
