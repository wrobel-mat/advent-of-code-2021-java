package solution.day2;

import solution.ISolution;

import java.util.List;
import java.util.Optional;

public class Solution implements ISolution {

    @Override
    public Optional<String> solvePartOne(List<String> input) {
        SubmarineCoursePlan coursePlan = new SubmarineCoursePlan(input);
        INavigationHandlerFactory navigationHandlerFactory = new SimpleHandlerFactory();
        Submarine submarine = new Submarine(navigationHandlerFactory);
        submarine.navigate(coursePlan);
        int answer = submarine.horizontalPosition * submarine.depth;
        return Optional.of(String.valueOf(answer));
    }

    @Override
    public Optional<String> solvePartTwo(List<String> input) {
        SubmarineCoursePlan coursePlan = new SubmarineCoursePlan(input);
        INavigationHandlerFactory navigationHandlerFactory = new ComplicatedHandlerFactory();
        Submarine submarine = new Submarine(navigationHandlerFactory);
        submarine.navigate(coursePlan);
        int answer = submarine.horizontalPosition * submarine.depth;
        return Optional.of(String.valueOf(answer));
    }
}
