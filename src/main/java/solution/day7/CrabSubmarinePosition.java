package solution.day7;

public record CrabSubmarinePosition(long position) {

    public boolean onTheSamePositionAs(CrabSubmarinePosition other) {
        return this.position == other.position;
    }

    public long measureDistanceFrom(CrabSubmarinePosition other) {
        return Math.abs(this.position - other.position);
    }
}
