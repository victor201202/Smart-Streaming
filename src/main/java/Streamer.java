import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Streamer {
    abstract Integer getId();

    abstract public LinkedHashMap<Integer, Stream> getStreams();

    abstract public void setStreams(LinkedHashMap<Integer, Stream> streams);

    abstract public void list();
    abstract public void deleteStream(Integer id);

    abstract String getName();
}
