package solution.day4;

import java.util.List;

public record Board(List<Row> rows, List<Column> columns) {
    public static final int SIZE = 5;

    public boolean wins() {
        return rows.stream().anyMatch(row -> row.numbers().stream().allMatch(Number::marked)) ||
                columns.stream().anyMatch(column -> column.numbers().stream().allMatch(Number::marked));
    }
}
