package solution.day7;

import solution.ISolution;

import java.util.List;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        CrabSwarm crabSwarm = new CrabSwarm(input.getFirst());
        CrabSubmarineFuelUsageCalculator fuelUsageCalculator = distance -> distance;
        CrabSwarmSimulator swarmSimulator = new CrabSwarmSimulator(fuelUsageCalculator);
        long fuelUsage = swarmSimulator.simulateSwarmAlignmentWithLowestFuelUsage(crabSwarm);
        return String.valueOf(fuelUsage);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        CrabSwarm crabSwarm = new CrabSwarm(input.getFirst());
        CrabSubmarineFuelUsageCalculator fuelUsageCalculator = distance -> distance * (distance + 1) / 2;
        CrabSwarmSimulator swarmSimulator = new CrabSwarmSimulator(fuelUsageCalculator);
        long fuelUsage = swarmSimulator.simulateSwarmAlignmentWithLowestFuelUsage(crabSwarm);
        return String.valueOf(fuelUsage);
    }
}
