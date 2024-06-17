package solution.day7;

record CrabSubmarinePosition(long position) {

    boolean onTheSamePositionAs(CrabSubmarinePosition other) {
        return this.position == other.position;
    }

    long measureDistanceFrom(CrabSubmarinePosition other) {
        return Math.abs(this.position - other.position);
    }
}
