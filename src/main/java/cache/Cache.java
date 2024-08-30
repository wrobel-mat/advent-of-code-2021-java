package cache;

import answer.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Cache {

    public static Optional<List<String>> getInput(int day) {
        Path inputPath = Path.of(STR."./src/main/resources/inputs/day\{day}.txt");
        if (Files.exists(inputPath)) {
            try {
                return Optional.of(Files.readAllLines(inputPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    public static void persistInput(int day, List<String> input) {
        Path inputPath = Path.of(STR."./src/main/resources/inputs/day\{day}.txt");
        try {
            if (Files.notExists(inputPath.getParent())) {
                Files.createDirectory(inputPath.getParent());
            }
            Files.write(inputPath, input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Result getResult(int year, int day) {
        Path resultPath = Path.of(STR."./src/main/resources/results/day\{day}.json");
        Result result;
        if (Files.exists(resultPath)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            try {
                result = mapper.readValue(Files.readString(resultPath), Result.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            result = Result.of(year, day);
        }
        return result;
    }

    public static void persistResult(Result result) {
        Path resultPath = Path.of(STR."./src/main/resources/results/day\{result.day()}.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            if (Files.notExists(resultPath.getParent())) {
                Files.createDirectory(resultPath.getParent());
            }
            Files.write(resultPath, mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
