public class Recommend extends Command{
    @Override
    public void execute(String command) {
        String[] params = command.split(" ");
        ApplicationData data = ApplicationData.getInstance();
        User user = data.getUsers().get(Integer.parseInt(params[0]));
        user.recommend(params[2]);
    }
}
