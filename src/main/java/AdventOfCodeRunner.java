import http.AocClient;

import java.util.List;

public class AdventOfCodeRunner {

    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]);
        int day = Integer.parseInt(args[1]);
        List<String> input = new AocClient().getInput(year, day);
        System.out.println("all good");
    }
}