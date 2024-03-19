import java.util.LinkedHashMap;

public class DeleteStream extends Command{
    @Override
    public void execute(String command) {
        String[] params = command.split(" ");
        ApplicationData data = ApplicationData.getInstance();
        Streamer streamer = data.getStreamers().get(Integer.parseInt(params[0]));
        streamer.deleteStream(Integer.parseInt(params[2]));
        LinkedHashMap<Integer, Stream> streams = data.getStreams();
        streams.remove(Integer.parseInt(params[0]));
        data.setStreams(streams);
    }
}
