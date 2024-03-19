public class StreamerFactory {
    private static StreamerFactory instance = null;

    private StreamerFactory() { }

    public Streamer createStreamer(Integer type, Integer id, String name)
    {
        switch (type) {
            case 1:
                return new Musician(id, name);
            case 2:
                return new Host(id, name);
            case 3:
                return new Author(id, name);
            default:
                return null;
        }
    }

    public static StreamerFactory getInstance() {
        if (instance == null)
            instance = new StreamerFactory();

        return instance;
    }
}
