import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AdventOfCodeRunner {

    public static void main(String[] args) {
        Properties props = getProperties();
        int year = Integer.parseInt(args[0]);
        int day = Integer.parseInt(args[1]);
        String sessionKey = props.getProperty("session.key");
        List<String> input = getInput(year, day, sessionKey);
        System.out.println("all good");
    }

    private static Properties getProperties() {
        try {
            InputStream propsConfig = AdventOfCodeRunner.class.getClassLoader().getResourceAsStream("aoc.properties");
            Properties props = new Properties();
            props.load(propsConfig);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getInput(int year, int day, String sessionKey) {
        try {
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