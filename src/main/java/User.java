import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class User {
    private Integer id;
    private String name;
    private ArrayList<Integer> streams;

    public User(Integer id, String name, ArrayList<Integer> streams) {
        this.id = id;
        this.name = name;
        this.streams = streams;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<Integer> streams) {
        this.streams = streams;
    }

    public void list() {
        ApplicationData data = ApplicationData.getInstance();
        System.out.print("[");
        boolean firstTime = false;
        for (Integer id : streams) {
            if (firstTime == false)
                firstTime = true;
            else
                System.out.print(",");

            Stream stream = data.getStreams().get(id);
            Streamer streamer = data.getStreamers().get(stream.getStreamerId());
            Duration duration = Duration.ofSeconds(stream.getLength());
            long HH = duration.toHours();
            long MM = duration.toMinutesPart();
            long SS = duration.toSecondsPart();
            String timeInHHMMSS;
            if (HH > 0)
                timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);
            else
                timeInHHMMSS = String.format("%02d:%02d", MM, SS);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String formatedDate = sdf.format(stream.getDateAdded() * 1000);
            System.out.print("{\"id\":\"" + stream.getId() + "\",\"name\":\"" + stream.getName() + "\",\"streamerName\":\""
                    + streamer.getName() + "\",\"noOfListenings\":\"" + stream.getNoOfStreams() + "\",\"length\":\"" + timeInHHMMSS +
                    "\",\"dateAdded\":\"" + formatedDate + "\"}");
        }
        System.out.println("]");
    }

    public void recommend(String type) {
        TreeMap<Long, Stream> recommandations = new TreeMap<>();
        ArrayList<Integer> streamers = new ArrayList<>();
        ApplicationData data = ApplicationData.getInstance();

        for (Integer streamId : this.streams) {
            for (Map.Entry<Integer, Streamer> streamerPair : data.getStreamers().entrySet()) {
                LinkedHashMap<Integer, Stream> streams = streamerPair.getValue().getStreams();
                if (streams.get(streamId) != null)
                    streamers.add(streamerPair.getKey());
            }
        }

        for (Integer streamerId : streamers) {
            //System.out.println(streamerId);
            Streamer streamer = data.getStreamers().get(streamerId);
            LinkedHashMap<Integer, Stream> streams = streamer.getStreams();
            for (Map.Entry<Integer, Stream> streamPair : streams.entrySet()) {
                Integer streamId = streamPair.getKey();
                Stream stream = streamPair.getValue();
                if (!this.streams.contains(streamId) && stream.type.equals(type))
                    recommandations.put(stream.getNoOfStreams(), stream);
            }
        }

        NavigableMap<Long, Stream> sortedRecommandations = recommandations.descendingMap();
        int count = 0;
        boolean firstTime = false;
        System.out.print("[");
        for (Map.Entry<Long, Stream> recomPair : sortedRecommandations.entrySet()) {
            if (count == 4)
                break;
            Stream stream = recomPair.getValue();
            if (firstTime == false)
                firstTime = true;
            else
                System.out.print(",");
            Streamer streamer = data.getStreamers().get(stream.getStreamerId());
            Duration duration = Duration.ofSeconds(stream.getLength());
            long HH = duration.toHours();
            long MM = duration.toMinutesPart();
            long SS = duration.toSecondsPart();
            String timeInHHMMSS;
            if (HH > 0)
                timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);
            else
                timeInHHMMSS = String.format("%02d:%02d", MM, SS);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String formatedDate = sdf.format(stream.getDateAdded() * 1000);
            System.out.print("{\"id\":\"" + stream.getId() + "\",\"name\":\"" + stream.getName() + "\",\"streamerName\":\""
                    + streamer.getName() + "\",\"noOfListenings\":\"" + stream.getNoOfStreams() + "\",\"length\":\"" + timeInHHMMSS +
                    "\",\"dateAdded\":\"" + formatedDate + "\"}");
            count++;
        }
        System.out.println("]");
    }

    public void surprise(String type) {
        ApplicationData data = ApplicationData.getInstance();
        ArrayList<Integer> listenededStreamers = new ArrayList<>();
        ArrayList<Streamer> unlistenedStreamers = new ArrayList<>();
        ArrayList<Stream> surpriseStreams = new ArrayList<>();

        for (Integer streamId : this.streams) {
            for (Map.Entry<Integer, Streamer> streamerPair : data.getStreamers().entrySet()) {
                LinkedHashMap<Integer, Stream> streams = streamerPair.getValue().getStreams();
                if (streams.get(streamId) != null)
                    listenededStreamers.add(streamerPair.getKey());
            }
        }

        for (Map.Entry<Integer, Streamer> streamersPair : data.getStreamers().entrySet()) {
            if (!listenededStreamers.contains(streamersPair.getKey()))
                unlistenedStreamers.add(streamersPair.getValue());
        }

        for (Streamer streamer : unlistenedStreamers) {
            LinkedHashMap<Integer, Stream> streams = streamer.getStreams();
            for (Map.Entry<Integer, Stream> streamsPair : streams.entrySet()) {
                if (streamsPair.getValue().type.equals(type))
                    surpriseStreams.add(streamsPair.getValue());
            }
        }

        Collections.sort(surpriseStreams, new CustomSortComparator());

        int count = 0;
        boolean firstTime = false;
        System.out.print("[");
        for (Stream stream : surpriseStreams) {
            if (count == 3)
                break;
            if (firstTime == false)
                firstTime = true;
            else
                System.out.print(",");
            Streamer streamer = data.getStreamers().get(stream.getStreamerId());
            Duration duration = Duration.ofSeconds(stream.getLength());
            long HH = duration.toHours();
            long MM = duration.toMinutesPart();
            long SS = duration.toSecondsPart();
            String timeInHHMMSS;
            if (HH > 0)
                timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);
            else
                timeInHHMMSS = String.format("%02d:%02d", MM, SS);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String formatedDate = sdf.format(stream.getDateAdded() * 1000);
            System.out.print("{\"id\":\"" + stream.getId() + "\",\"name\":\"" + stream.getName() + "\",\"streamerName\":\""
                    + streamer.getName() + "\",\"noOfListenings\":\"" + stream.getNoOfStreams() + "\",\"length\":\"" + timeInHHMMSS +
                    "\",\"dateAdded\":\"" + formatedDate + "\"}");
            count++;
        }
        System.out.println("]");
    }

    public void listen(Integer id) {
        ApplicationData data = ApplicationData.getInstance();
        LinkedHashMap<Integer, Stream> streams = data.getStreams();
        Stream stream = streams.get(id);
        Long noOfStreams = stream.getNoOfStreams();
        stream.setNoOfStreams(noOfStreams + 1);

        if (!this.streams.contains(id))
            this.streams.add(id);
    }
}
