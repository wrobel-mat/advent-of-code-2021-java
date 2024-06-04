package solution.day5;

import solution.ISolution;

import java.util.List;
import java.util.function.Predicate;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        VentMap ventMap = new VentMap(input);
        Predicate<VentLine> horizontalVents = ventLine -> ventLine.start().y() == ventLine.end().y();
        Predicate<VentLine> verticalVents = ventLine -> ventLine.start().x() == ventLine.end().x();
        long overlapCount = ventMap.countOverlappingVentPoints(horizontalVents.or(verticalVents));
        return String.valueOf(overlapCount);
    }

    @Override
    public String solvePartTwo(List<String> input) {
        VentMap ventMap = new VentMap(input);
        Predicate<VentLine> allVents = _ -> true;
        long overlapCount = ventMap.countOverlappingVentPoints(allVents);
        return String.valueOf(overlapCount);
    }
}
