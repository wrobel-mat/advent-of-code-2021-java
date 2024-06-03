package solution.day2;

import solution.ISolution;

import java.util.List;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        SubmarineCoursePlan coursePlan = new SubmarineCoursePlan(input);
        INavigationHandlerFactory navigationHandlerFactory = new SimpleHandlerFactory();
        Submarine submarine = new Submarine(navigationHandlerFactory);
        submarine.navigate(coursePlan);
        return String.valueOf(submarine.horizontalPosition * submarine.depth);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        SubmarineCoursePlan coursePlan = new SubmarineCoursePlan(input);
        INavigationHandlerFactory navigationHandlerFactory = new ComplicatedHandlerFactory();
        Submarine submarine = new Submarine(navigationHandlerFactory);
        submarine.navigate(coursePlan);
        return String.valueOf(submarine.horizontalPosition * submarine.depth);
    }
}
