package solution.day4;

public class Number {

    private final Integer value;
    private boolean marked;

    public Number(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }

    public void mark() {
        this.marked = true;
    }

    public boolean marked() {
        return this.marked;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
