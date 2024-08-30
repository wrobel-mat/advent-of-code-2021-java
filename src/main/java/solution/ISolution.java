package solution;

import java.util.List;
import java.util.Optional;

public interface ISolution {
    Optional<String> solvePartOne(List<String> input);
    Optional<String> solvePartTwo(List<String> input);
}
