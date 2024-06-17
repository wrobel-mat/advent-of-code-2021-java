package solution.day7;

import java.util.Arrays;
import java.util.List;

class CrabSwarm {

    private final List<CrabSubmarinePosition> submarines;

    CrabSwarm(String input) {
        this.submarines = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .map(CrabSubmarinePosition::new)
                .toList();
    }

    List<CrabSubmarinePosition> submarines() {
        return this.submarines;
    }
}
