package solution.day5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class VentMap {

    private final List<VentLine> ventLines;

    public VentMap(List<String> input) {
        this.ventLines = input.stream().map(lineSegment -> {
            String[] coordinates = lineSegment.split(" -> ");
            String[] startCoordinates = coordinates[0].split(",");
            String[] endCoordinates = coordinates[1].split(",");
            Point start = new Point(Integer.parseInt(startCoordinates[0]), Integer.parseInt(startCoordinates[1]));
            Point end = new Point(Integer.parseInt(endCoordinates[0]), Integer.parseInt(endCoordinates[1]));
            return new VentLine(start, end);
        }).toList();
    }

    public long countOverlappingVentPoints(Predicate<VentLine> ventLineFilter) {
        Map<Point, Integer> pointCounters = new HashMap<>();
        ventLines.stream()
                .filter(ventLineFilter)
                .forEach(line ->
                        line.getLinePoints().forEach(point -> {
                            Integer counter = pointCounters.getOrDefault(point, 0);
                            pointCounters.put(point, counter + 1);
                        }));
        return pointCounters.entrySet().stream()
                .filter((entry) -> entry.getValue() >= 2)
                .count();
    }
}
