package solution.day4;

import solution.ISolution;

import java.util.List;

public class Solution implements ISolution {

    @Override
    public String solvePartOne(List<String> input) {
        Bingo bingo = new Bingo(input);
        return String.valueOf(bingo.playUntilFirstWin());
    }

    @Override
    public String solvePartTwo(List<String> input) {
        Bingo bingo = new Bingo(input);
        return String.valueOf(bingo.playAllBoards());
    }
}
