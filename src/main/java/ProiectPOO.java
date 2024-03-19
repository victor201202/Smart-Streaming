import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

public class ProiectPOO {
    static String streamers, users, streams;

    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Nothing to read here");
        } else {
            String strLine = null;
            BufferedReader br = null;
            CommandFactory commandFactory = CommandFactory.getInstance();
            try {
                br = new BufferedReader(new FileReader("src/main/resources/" + args[3]));
                streamers = args[0];
                streams = args[1];
                users = args[2];
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (true) {
                try {
                    if (((strLine = br.readLine()) == null)) break;
                    Command command = commandFactory.createCommand(strLine);
                    command.execute(strLine);

                } catch (IOException e) {
                    System.err.println("NullPointerException");
                }
            }
        }
    }
}

