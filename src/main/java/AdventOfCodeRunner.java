import answer.Result;
import cache.Cache;
import http.AocClient;
import http.AocSubmitResult;
import solution.ISolution;
import util.AocUtil;

import java.util.List;
import java.util.Properties;


public class AdventOfCodeRunner {

    public static void main(String[] args) {
        final Properties props = AocUtil.getProperties();
        final int year = Integer.parseInt(props.getProperty("year"));
        final int day = Integer.parseInt(props.getProperty("day"));
        final Result result = Cache.getResult(year, day);
        if (result.bothPartsCompleted()) {
            printResult(result);
            System.exit(0);
        }

        final AocClient aocClient = new AocClient();
        final List<String> input = Cache.getInput(day)
                .orElseGet(() -> {
                    List<String> fetchedInput = aocClient.getInput(year, day);
                    Cache.persistInput(day, fetchedInput);
                    return fetchedInput;
                });

        final ISolution solution = AocUtil.getSolution(day);
        if (result.partNotCompleted(1)) {
            solution.solvePartOne(input)
                    .map(answer -> aocClient.submitAnswer(year, day, 1, answer))
                    .filter(AocSubmitResult::isGoodAnswer)
                    .ifPresent(submitResult -> result.completePart(1, submitResult.submittedAnswer()));
        }
        if (result.partNotCompleted(2)) {
            solution.solvePartTwo(input)
                    .map(answer -> aocClient.submitAnswer(year, day, 2, answer))
                    .filter(AocSubmitResult::isGoodAnswer)
                    .ifPresent(submitResult -> result.completePart(2, submitResult.submittedAnswer()));
        }

        printResult(result);
        Cache.persistResult(result);
    }

    private static void printResult(Result result) {
        System.out.println(result);
    }
}