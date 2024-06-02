package http;

import util.AocUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AocClient {

    public List<String> getInput(int year, int day) {
        try {
            Properties props = AocUtil.getProperties();
            String sessionKey = props.getProperty("session.key");
            URL url = URI.create(STR."https://adventofcode.com/\{year}/day/\{day}/input").toURL();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", STR."session=\{sessionKey}");
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            List<String> input = new ArrayList<>();
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
            return input;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
