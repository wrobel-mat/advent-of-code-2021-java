package solution.day12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CaveTraversalRules {

    private final List<CaveTypeTraversalRule> rules;

    private CaveTraversalRules(List<CaveTypeTraversalRule> rules) {
        this.rules = rules;
    }

    boolean validate(CavePath path, Cave potentialNextCave) {
        return rules.stream()
                .allMatch(traversalRule -> traversalRule.canTraverseToNextCave(path, potentialNextCave));
    }

    static class Builder {

        private final List<CaveTypeTraversalRule> rules = new ArrayList<>();

        CaveTypeTraversalRule.Builder addRuleForCaveType(CaveType caveType) {
            CaveTypeTraversalRule.Builder caveTypeRuleBuilder = new CaveTypeTraversalRule.Builder(this);
            caveTypeRuleBuilder.caveType = caveType;
            return caveTypeRuleBuilder;
        }

        CaveTraversalRules build() {
            return new CaveTraversalRules(rules);
        }
    }

    static class CaveTypeTraversalRule {
        private final CaveType caveType;
        private final CaveVisitLimit visitLimit;
        private final CavePathApplicability cavePathApplicability;

        private CaveTypeTraversalRule(CaveType caveType, CaveVisitLimit visitLimit, CavePathApplicability cavePathApplicability) {
            this.caveType = caveType;
            this.visitLimit = visitLimit;
            this.cavePathApplicability = cavePathApplicability;
        }

        boolean canTraverseToNextCave(CavePath path, Cave potentialNextCave) {
            if (potentialNextCave.equals(path.steps().getFirst())) {
                return false;
            }

            List<Cave> visitedCavesWithoutTheFirstOne = path.steps().subList(1, path.steps().size());
            List<Cave> visitedCavesByType = visitedCavesWithoutTheFirstOne.stream().filter(caveType::matches).toList();
            List<Cave> visitedCavesByTypeIncludingNextCave = Stream.concat(visitedCavesByType.stream(), Stream.of(potentialNextCave)).toList();
            return validateCavePathConsideringVisitLimit(visitedCavesByTypeIncludingNextCave, visitLimit);
        }

        boolean validateCavePathConsideringVisitLimit(List<Cave> validatedCavePath, CaveVisitLimit visitLimit) {
            return switch (cavePathApplicability) {
                case ALL_CAVES_PER_PATH -> validatedCavePath.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .values().stream()
                        .allMatch(visitLimit::notExceeded);
                case ONE_CAVE_PER_PATH -> {
                    Collection<Long> visitCounters = validatedCavePath.stream()
                            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                            .values();
                    yield visitCounters.stream().allMatch(visitLimit::notExceeded)
                            && visitCounters.stream().filter(visitLimit::matchesLimit).count() <= cavePathApplicability.caveCountLimit;
                }
            };
        }

        static class Builder {
            private CaveTraversalRules.Builder parentBuilder;
            private CaveType caveType;
            private CaveVisitLimit visitLimit;
            private CavePathApplicability cavePathApplicability;

            private Builder(CaveTraversalRules.Builder parentBuilder) {
                this.parentBuilder = parentBuilder;
            }

            Builder applicableTo(CavePathApplicability cavePathApplicability) {
                this.cavePathApplicability = cavePathApplicability;
                return this;
            }

            Builder withVisitLimit(CaveVisitLimit visitLimit) {
                this.visitLimit = visitLimit;
                return this;
            }

            CaveTraversalRules.Builder and() {
                parentBuilder.rules.add(new CaveTypeTraversalRule(caveType, visitLimit, cavePathApplicability));
                return parentBuilder;
            }

            CaveTraversalRules build() {
                parentBuilder.rules.add(new CaveTypeTraversalRule(caveType, visitLimit, cavePathApplicability));
                return parentBuilder.build();
            }
        }
    }

    enum CaveType {
        BIG_CAVE,
        SMALL_CAVE;

        boolean matches(Cave cave) {
            return switch (this) {
                case BIG_CAVE -> cave.name().equals(cave.name().toUpperCase());
                case SMALL_CAVE -> cave.name().equals(cave.name().toLowerCase());
            };
        }
    }

    enum CaveVisitLimit {
        UNLIMITED(Long.MAX_VALUE),
        AT_MOST_ONCE(1),
        AT_MOST_TWICE(2);

        private final long limit;

        CaveVisitLimit(long limit) {
            this.limit = limit;
        }

        boolean notExceeded(long visitCount) {
            return visitCount <= limit;
        }

        boolean matchesLimit(long visitCount) {
            if (this.equals(UNLIMITED)) {
                return false;
            }
            return visitCount == limit;
        }
    }

    enum CavePathApplicability {
        ALL_CAVES_PER_PATH(-1),
        ONE_CAVE_PER_PATH(1);

        private final int caveCountLimit;

        CavePathApplicability(int caveCountLimit) {
            this.caveCountLimit = caveCountLimit;
        }
    }
}
