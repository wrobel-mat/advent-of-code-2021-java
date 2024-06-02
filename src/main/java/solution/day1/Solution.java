package solution.day1;

import solution.ISolution;

import java.util.List;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        SonarSweepMeasurements measurements = new SonarSweepMeasurements(input);
        return measurements.countDepthIncreases(1);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        SonarSweepMeasurements measurements = new SonarSweepMeasurements(input);
        return measurements.countDepthIncreases(3);
    }
}
