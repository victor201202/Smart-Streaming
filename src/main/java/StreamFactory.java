public class StreamFactory {
    private static StreamFactory instance = null;

    private StreamFactory() {
    }

    public Stream createStream(Integer type, Integer id, Integer streamGenre, Integer streamerId, Long noOfStreams, Long length, Long dateAdded, String name) {
        switch (type) {
            case 1:
                return new Song(id, streamGenre, streamerId, noOfStreams, length, dateAdded, name);
            case 2:
                return new Podcast(id, streamGenre, streamerId, noOfStreams, length, dateAdded, name);
            case 3:
                return new Audiobook(id, streamGenre, streamerId, noOfStreams, length, dateAdded, name);
            default:
                return null;
        }
    }

    public static StreamFactory getInstance() {
        if (instance == null)
            instance = new StreamFactory();

        return instance;
    }
}
