public class ListStream extends Command {
    @Override
    public void execute(String command) {
        String[] params = command.split(" ");
        ApplicationData data = ApplicationData.getInstance();
        User user = data.getUsers().get(Integer.parseInt(params[0]));
        if (user == null) {
            Streamer streamer = data.getStreamers().get(Integer.parseInt(params[0]));
            streamer.list();
        } else
            user.list();
    }
}
