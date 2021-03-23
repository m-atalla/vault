package terminal;

public abstract class Command {
    protected String name;

    protected String description;

    public Command() {
        this.setName();
        this.setDescription();
    }
    protected abstract void setName();

    protected abstract void setDescription();

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public abstract void execute();
}
