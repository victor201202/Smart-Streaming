import java.util.*;

public class ApplicationData {
    private static ApplicationData instance = null;
    private HashMap<Integer, User> users;
    private HashMap<Integer, Streamer> streamers;
    private LinkedHashMap<Integer, Stream> streams;

    public LinkedHashMap<Integer, Stream> getStreams() {
        return streams;
    }

    public void setStreams(LinkedHashMap<Integer, Stream> streams) {
        this.streams = streams;
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Integer, User> users) {
        this.users = users;
    }

    public HashMap<Integer, Streamer> getStreamers() {
        return streamers;
    }

    public void setStreamers(HashMap<Integer, Streamer> streamers) {
        this.streamers = streamers;
    }

    private ApplicationData() {
        DatabaseFacade dataBase = new DatabaseFacade();
        this.users = new HashMap<>();
        this.users.clear();
        for (User user : dataBase.fetchUsers()) {
            this.users.put(user.getId(), user);
        }
        this.streamers = new HashMap<>();
        this.streamers.clear();
        for (Streamer streamer : dataBase.fetchStreamers()) {
            this.streamers.put(streamer.getId(), streamer);
        }
        this.streams = new LinkedHashMap<>();
        this.streams.clear();
        for (Stream stream : dataBase.fetchStreams()) {
            Streamer streamer = this.streamers.get(stream.getStreamerId());
            LinkedHashMap<Integer, Stream> streams = streamer.getStreams();
            streams.put(stream.getId(), stream);
            streamer.setStreams(streams);
            this.streamers.put(streamer.getId(), streamer);
            this.streams.put(stream.getId(), stream);
        }
        for(Map.Entry<Integer, Streamer> streamerPair: this.streamers.entrySet()) {
            Streamer streamer = streamerPair.getValue();
            LinkedHashMap<Integer, Stream> streams = streamer.getStreams();
            LinkedHashMap<Integer, Stream> reversedStreams = new LinkedHashMap<>();
            List<Integer> reverseOrderedKeys = new ArrayList<>(streams.keySet());
            Collections.reverse(reverseOrderedKeys);
            for (Integer key : reverseOrderedKeys)
                reversedStreams.put(key, streams.get(key));

            streamer.setStreams(reversedStreams);
            streamerPair.setValue(streamer);
        }
    }

    public static ApplicationData getInstance() {
        if (instance == null)
            instance = new ApplicationData();

        return instance;
    }
}
