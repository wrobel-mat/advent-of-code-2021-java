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
        List<String> input = new AocClient().getInput(year, day);
        ISolution solution = AocUtil.getSolution(day);
        System.out.println(STR."Part 1: \{solution.solvePartOne(input)}");
        System.out.println(STR."Part 2: \{solution.solvePartTwo(input)}");
    }
}