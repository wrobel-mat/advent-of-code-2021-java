package solution.day4;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Bingo {

    private final Integer[] numbers;
    private final List<Board> boards;

    public Bingo(List<String> input) {
        this.numbers = getNumbers(input.getFirst());
        this.boards = getBoards(input.subList(2, input.size()));
    }

    private Integer[] getNumbers(String numbersInput) {
        return Arrays.stream(numbersInput.split(","))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }

    private List<Board> getBoards(List<String> boardsInput) {
        List<Board> result = new ArrayList<>();
        for (int i = 0; i < boardsInput.size(); i += Board.SIZE + 1) {
            List<Row> rows = getRows(boardsInput.subList(i, i + Board.SIZE));
            List<Column> columns = getColumns(rows);
            result.add(new Board(rows, columns));
        }
        return result;
    }

    private List<Row> getRows(List<String> rowsInput) {
        return rowsInput.stream()
                .map(row -> Arrays.stream(row.trim().split("\s+"))
                        .map(Integer::valueOf)
                        .map(Number::new)
                        .toList())
                .map(Row::new)
                .toList();
    }

    private List<Column> getColumns(List<Row> rows) {
        return IntStream.range(0, rows.size())
                .mapToObj(i -> rows.stream()
                        .map(row -> row.numbers().get(i))
                        .toList())
                .map(Column::new)
                .toList();
    }

    public int playUntilFirstWin() {
        for (Integer number : numbers) {
            for (Board board : boards) {
                for (Row row : board.rows()) {
                    for (Number rowNumber : row.numbers()) {
                        if (number.compareTo(rowNumber.value()) == 0) {
                            rowNumber.mark();
                        }
                    }
                }
                if (board.wins()) {
                    Integer unmarkedSum = getUnmarkedNumbersSum(board);
                    return unmarkedSum * number;
                }
            }
        }
        return 0;
    }

    public int playAllBoards() {
        Board lastWinnerBoard = new Board(Collections.emptyList(), Collections.emptyList());
        Integer lastWinnerNumber = 0;
        Set<Board> winnerBoards = new HashSet<>();
        for (Integer number : numbers) {
            for (Board board : boards) {
                if (winnerBoards.contains(board)) {
                    continue;
                }
                for (Row row : board.rows()) {
                    for (Number rowNumber : row.numbers()) {
                        if (number.compareTo(rowNumber.value()) == 0) {
                            rowNumber.mark();
                        }
                    }
                }
                if (board.wins()) {
                    lastWinnerBoard = board;
                    lastWinnerNumber = number;
                    winnerBoards.add(board);
                }
            }
        }
        Integer unmarkedSum = getUnmarkedNumbersSum(lastWinnerBoard);
        return unmarkedSum * lastWinnerNumber;
    }

    private Integer getUnmarkedNumbersSum(Board board) {
        return board.rows().stream()
                .flatMap(row ->
                        row.numbers().stream()
                                .filter(Predicate.not(Number::marked))
                                .map(Number::value))
                .reduce(Integer::sum)
                .orElse(0);
    }
}
