package exec.mode;

import cache.Cache;
import http.AocClient;
import solution.ISolution;
import solution.SolutionProvider;
import config.Configuration;

import java.util.List;
import java.util.Properties;

public class TestSolutionExecutor implements IModeExecutor {

    @Override
    public ApplicationMode mode() {
        return ApplicationMode.TEST;
    }

    @Override
    public void run() {
        final Properties props = Configuration.getProperties();
        final int year = Integer.parseInt(props.getProperty("year"));
        final int day = Integer.parseInt(props.getProperty("day"));

        final List<String> input = Cache.getInput(day)
                .orElseGet(() -> {
                    List<String> fetchedInput = new AocClient().getInput(year, day);
                    Cache.persistInput(day, fetchedInput);
                    return fetchedInput;
                });

        final ISolution solution = SolutionProvider.getSolution(day);
        solution.solvePartOne(input).ifPresent(answer -> System.out.println(STR."Part 1 answer: \{answer}"));
        solution.solvePartTwo(input).ifPresent(answer -> System.out.printf(STR."Part 2 answer: \{answer}"));
    }
}
