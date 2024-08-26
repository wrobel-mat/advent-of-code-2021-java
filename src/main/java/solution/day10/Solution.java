package solution.day10;

import solution.ISolution;

import java.util.*;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        long result = new NavigationSubsystemProcessor(input).calculateSyntaxErrorsScore();
        return String.valueOf(result);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        long result = new NavigationSubsystemProcessor(input).calculateAutocompleteScoreWinner();
        return String.valueOf(result);
    }
}
