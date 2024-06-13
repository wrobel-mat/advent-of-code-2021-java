package solution.day8;

import solution.ISolution;

import java.util.List;

public class Solution implements ISolution {
    
    @Override
    public String solvePartOne(List<String> input) {
        DisplaysReport displaysReport = new DisplaysReport(input);
        long answer = displaysReport.countEasyDigits();
        return String.valueOf(answer);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        DisplaysReport displaysReport = new DisplaysReport(input);
        long answer = displaysReport.decodeAndSumOutputValues();
        return String.valueOf(answer);
    }
}
