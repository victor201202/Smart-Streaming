public class CommandFactory {
    private static CommandFactory instance = null;

    private CommandFactory() {};

    public Command createCommand(String command) {
        if(command.contains(" ADD"))
            return new AddStream();
        else if(command.contains(" LISTEN"))
            return new Listen();
        else if(command.contains(" DELETE"))
            return new DeleteStream();
        else if(command.contains(" LIST"))
            return new ListStream();
        else if(command.contains(" RECOMMEND"))
            return new Recommend();
        else if(command.contains(" SURPRISE"))
            return new Surprise();
        return null;
    }

    public static CommandFactory getInstance() {
        if (instance == null)
            instance = new CommandFactory();

        return instance;
    }
}
