package solution.day1;

import java.util.ArrayList;
import java.util.List;

class SonarSweepMeasurements {

    private final List<SeaFloorDepth> floorDepths = new ArrayList<>();

    SonarSweepMeasurements(List<String> sonarSweepReport) {
        for (String measurement : sonarSweepReport) {
            floorDepths.add(new SeaFloorDepth(Integer.parseInt(measurement)));
        }
    }

    String countDepthIncreases(int measurementWindowSize) {
        int counter = 0;
        int currentMeasurement;
        int previousMeasurement = getMeasurementWindowSum(0, measurementWindowSize);
        for (int i = 1; i + measurementWindowSize <= floorDepths.size(); i++) {
            currentMeasurement = getMeasurementWindowSum(i, i + measurementWindowSize);
            if (currentMeasurement > previousMeasurement) {
                counter++;
            }
            previousMeasurement = currentMeasurement;
        }
        return String.valueOf(counter);
    }

    private int getMeasurementWindowSum(int from, int to) {
        return floorDepths.subList(from, to).stream()
                .map(SeaFloorDepth::measurement)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
