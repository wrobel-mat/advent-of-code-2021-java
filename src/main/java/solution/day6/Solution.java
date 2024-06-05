package solution.day6;

import solution.ISolution;

import java.util.List;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        LanternFishSpawnSimulation simulation = new LanternFishSpawnSimulation(input.getFirst());
        long populationCount = simulation.simulateSpawn(80).countPopulation();
        return String.valueOf(populationCount);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        LanternFishSpawnSimulation simulation = new LanternFishSpawnSimulation(input.getFirst());
        long populationCount = simulation.simulateSpawn(256).countPopulation();
        return String.valueOf(populationCount);
    }
}
