import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

public class Musician extends Streamer {
    private Integer id;
    private String name;
    private LinkedHashMap<Integer, Stream> streams;

    public LinkedHashMap<Integer, Stream> getStreams() {
        return streams;
    }

    public void setStreams(LinkedHashMap<Integer, Stream> streams) {
        this.streams = streams;
    }

    public Musician(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.streams = new LinkedHashMap<>();
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

    public void list() {
        ApplicationData data = ApplicationData.getInstance();
        System.out.print("[");
        boolean firstTime = false;
        for (Map.Entry<Integer, Stream> streamPair : this.streams.entrySet()) {
            if (firstTime == false)
                firstTime = true;
            else
                System.out.print(",");

            Stream stream = streamPair.getValue();
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

    @Override
    public void deleteStream(Integer id) {
        this.streams.remove(id);
    }
}
