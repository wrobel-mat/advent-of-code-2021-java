package solution.day9;

import solution.ISolution;

import java.util.List;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        HeightMap heightMap = new HeightMap(input);
        long answer = heightMap.countLowPointsRiskLevel();
        return String.valueOf(answer);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        HeightMap heightMap = new HeightMap(input);
        long answer = heightMap.threeLargestBasinSizesMultiplied();
        return String.valueOf(answer);
    }
}
