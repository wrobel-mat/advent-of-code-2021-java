package solution.day7;

import java.util.Arrays;
import java.util.List;

public class CrabSwarm {

    private final List<CrabSubmarinePosition> submarines;

    public CrabSwarm(String input) {
        this.submarines = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .map(CrabSubmarinePosition::new)
                .toList();
    }

    public List<CrabSubmarinePosition> submarines() {
        return this.submarines;
    }
}
