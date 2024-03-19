import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AddStream extends Command {
    @Override
    public void execute(String command) {
        String[] params = command.split(" ");
        ApplicationData data = ApplicationData.getInstance();
        Streamer streamer = data.getStreamers().get(Integer.parseInt(params[0]));
        LinkedHashMap<Integer, Stream> streams = streamer.getStreams();
        StreamFactory streamFactory = StreamFactory.getInstance();
        Date now = new Date();
        Stream stream = streamFactory.createStream(Integer.parseInt(params[2]), Integer.parseInt(params[3]),
                Integer.parseInt(params[4]), streamer.getId(), 0L, Long.parseLong(params[5]), now.getTime(), params[6]);
        streams.put(stream.getId(), stream);
        streamer.setStreams(streams);
    }
}
