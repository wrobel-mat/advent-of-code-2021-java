package solution.day5;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record VentLine(Point start, Point end) {

    public Set<Point> getLinePoints() {
        if (start.equals(end)) {
            return Set.of(start);
        }

        Set<Point> points = new HashSet<>();
        if (start.x() == end.x()) {
            int x = start.x();
            int startY = Math.min(start.y(), end.y());
            int endY = Math.max(start.y(), end.y());
            points.addAll(IntStream.range(startY, endY + 1).mapToObj(y -> new Point(x, y)).collect(Collectors.toSet()));
        } else if (start.y() == end.y()) {
            int y = start.y();
            int startX = Math.min(start.x(), end.x());
            int endX = Math.max(start.x(), end.x());
            points.addAll(IntStream.range(startX, endX + 1).mapToObj(x -> new Point(x, y)).collect(Collectors.toSet()));
        } else {
            Point startPoint = start.x() < end.x() ? start : end;
            Point endPoint = start.x() < end.x() ? end : start;
            int direction = startPoint.y() < endPoint.y() ? 1 : -1;
            for (int x = startPoint.x(), y = startPoint.y(); x <= endPoint.x(); x++, y += direction) {
                points.add(new Point(x, y));
            }
        }

        return points;
    }
}
