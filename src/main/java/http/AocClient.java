package http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import util.AocUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AocClient {

    public List<String> getInput(int year, int day) {
        try {
            Path inputPath = Path.of(STR."./src/main/resources/day\{day}.input");
            if (Files.exists(inputPath)) {
                return Files.readAllLines(inputPath);
            }
            Properties props = AocUtil.getProperties();
            String sessionKey = props.getProperty("session.key");
            String userAgent = props.getProperty("user.agent");
            URL url = URI.create(STR."https://adventofcode.com/\{year}/day/\{day}/input").toURL();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", STR."session=\{sessionKey}");
            connection.setRequestProperty("User-Agent", userAgent);
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            List<String> input = new ArrayList<>();
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
            Files.write(inputPath, input);
            return input;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String submitAnswer(int year, int day, int part, String answer) {
        try {
            Properties props = AocUtil.getProperties();
            String sessionKey = props.getProperty("session.key");
            String userAgent = props.getProperty("user.agent");
            URL url = URI.create(STR."https://adventofcode.com/\{year}/day/\{day}/answer").toURL();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Cookie", STR."session=\{sessionKey}");
            connection.setRequestProperty("User-Agent", userAgent);
            connection.setDoOutput(true);
            connection.getOutputStream().write(STR."level=\{part}&answer=\{answer}".getBytes());
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int length; (length = inputStream.read(buffer)) != -1; ) {
                result.write(buffer, 0, length);
            }
            Document document = Jsoup.parse(result.toString());
            return document.getElementsByTag("article").text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
