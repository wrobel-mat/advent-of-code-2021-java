package solution.day3;

import solution.ISolution;

import java.util.List;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        DiagnosticReport diagnosticReport = new DiagnosticReport(input);
        int powerConsumption = diagnosticReport.calculatePowerConsumption();
        return String.valueOf(powerConsumption);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        DiagnosticReport diagnosticReport = new DiagnosticReport(input);
        int lifeSupportRating = diagnosticReport.calculateLifeSupportRating();
        return String.valueOf(lifeSupportRating);
    }
}
