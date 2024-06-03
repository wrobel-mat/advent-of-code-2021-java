package solution.day4;

import java.util.List;

public record Board(List<Row> rows, List<Column> columns) {

    public static final int SIZE = 5;

    public boolean wins() {
        return rows.stream().anyMatch(row -> row.numbers().stream().allMatch(Number::marked)) ||
                columns.stream().anyMatch(column -> column.numbers().stream().allMatch(Number::marked));
    }

    public record Row(List<Number> numbers) {
    }

    public record Column(List<Number> numbers) {
    }

    public static class Number {

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
}
