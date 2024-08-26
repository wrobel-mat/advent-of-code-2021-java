import http.AocClient;
import solution.ISolution;
import util.AocUtil;

import java.util.List;
import java.util.Properties;

public class AdventOfCodeRunner {

    public static void main(String[] args) {
        Properties props = AocUtil.getProperties();
        int year = Integer.parseInt(props.getProperty("year"));
        int day = Integer.parseInt(props.getProperty("day"));
        AocClient aocClient = new AocClient();
        List<String> input = aocClient.getInput(year, day);
        ISolution solution = AocUtil.getSolution(day);
        String partOneAnswer = solution.solvePartOne(input);
        String partTwoAnswer = solution.solvePartTwo(input);
        String partOneResponseMsg = aocClient.submitAnswer(year, day, 1, partOneAnswer);
        String partTwoResponseMsg = aocClient.submitAnswer(year, day, 2, partTwoAnswer);
        System.out.println(STR."Year \{year}, Day \{day}");
        System.out.println(STR."Part 1: \{partOneAnswer}");
        System.out.println(partOneResponseMsg);
        System.out.println(STR."Part 2: \{partTwoAnswer}");
        System.out.println(partTwoResponseMsg);
    }
}