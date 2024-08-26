package http;

import answer.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import util.AocUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AocClient {

    private static final String GOOD_ANSWER = "That's the right answer!";
    private static final String ALREADY_COMPLETED = "You don't seem to be solving the right level. Did you already complete it?";

    public List<String> getInput(int year, int day) {
        try {
            Path inputPath = Path.of(STR."./src/main/resources/inputs/day\{day}.input");
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
        if (answer.isEmpty()) {
            return "Skip submit - no answer provided";
        }
        try {
            Result result = getResult(year, day);
            if (result.isCompleted(part)) {
                return "Skip submit - this part is completed";
            }
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
            ByteArrayOutputStream response = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int length; (length = inputStream.read(buffer)) != -1; ) {
                response.write(buffer, 0, length);
            }
            Document document = Jsoup.parse(response.toString());
            String msg = document.getElementsByTag("article").text();
            if (msg.startsWith(GOOD_ANSWER) || msg.startsWith(ALREADY_COMPLETED)) {
                result.complete(part, answer);
                persistResult(result);
            }
            return msg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Result getResult(int year, int day) throws IOException {
        Path resultPath = Path.of(STR."./src/main/resources/results/day\{day}.result");
        Result result;
        if (Files.exists(resultPath)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            result = mapper.readValue(Files.readString(resultPath), Result.class);
        } else {
            result = Result.of(year, day);
        }
        return result;
    }

    private void persistResult(Result result) throws IOException {
        Path resultPath = Path.of(STR."./src/main/resources/results/day\{result.day()}.result");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String resultString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        Files.write(resultPath, resultString.getBytes());
    }
}
