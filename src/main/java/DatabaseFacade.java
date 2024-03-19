import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;

public class DatabaseFacade {

    public ArrayList<User> fetchUsers() {
        try {
            FileReader filereader = new FileReader("src/main/resources/" + ProiectPOO.users);
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.skip(1);
            String[] nextRecord;
            ArrayList<User> users = new ArrayList<>();
            users.clear();
            while ((nextRecord = csvReader.readNext()) != null) {
                String[] streamsString = nextRecord[2].split(" ");
                ArrayList<Integer> streams = new ArrayList<>();
                for (String stream : streamsString)
                    streams.add(Integer.parseInt(stream));

                users.add(new User(Integer.parseInt(nextRecord[0]), nextRecord[1], streams));

            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Streamer> fetchStreamers() {
        try {
            FileReader filereader = new FileReader("src/main/resources/" + ProiectPOO.streamers);
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.skip(1);
            String[] nextRecord;
            ArrayList<Streamer> streamers = new ArrayList<>();
            StreamerFactory factory = StreamerFactory.getInstance();
            streamers.clear();
            while ((nextRecord = csvReader.readNext()) != null) {

                streamers.add(factory.createStreamer(Integer.parseInt(nextRecord[0]), Integer.parseInt(nextRecord[1]), nextRecord[2]));

            }
            return streamers;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Stream> fetchStreams() {
        try {
            FileReader filereader = new FileReader("src/main/resources/" + ProiectPOO.streams);
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.skip(1);
            String[] nextRecord;
            ArrayList<Stream> streams = new ArrayList<>();
            StreamFactory factory = StreamFactory.getInstance();
            streams.clear();
            while ((nextRecord = csvReader.readNext()) != null) {
                streams.add(factory.createStream(Integer.parseInt(nextRecord[0]), Integer.parseInt(nextRecord[1]),
                        Integer.parseInt(nextRecord[2]), Integer.parseInt(nextRecord[4]),
                        Long.parseLong(nextRecord[3]), Long.parseLong(nextRecord[5]),
                        Long.parseLong(nextRecord[6]), nextRecord[7]));
            }
            return streams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
