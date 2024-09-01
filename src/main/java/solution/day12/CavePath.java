package solution.day12;

import java.util.List;
import java.util.stream.Stream;

record CavePath(List<Cave> steps, CaveTraversalRules traversalRules) {

    CavePath addNextStep(Cave step) {
        return new CavePath(Stream.concat(steps.stream(), Stream.of(step)).toList(), traversalRules);
    }

    boolean canTraverseToNextCave(Cave potentialNextCave) {
        return traversalRules.validate(this, potentialNextCave);
    }
}
