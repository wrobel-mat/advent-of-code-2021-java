import http.AocClient;
import util.AocUtil;

import java.util.List;
import java.util.Properties;

public class AdventOfCodeRunner {

    public static void main(String[] args) {
        Properties props = AocUtil.getProperties();
        int year = Integer.parseInt(props.getProperty("year"));
        int day = Integer.parseInt(props.getProperty("day"));
        List<String> input = new AocClient().getInput(year, day);
        System.out.println("all good");
    }
}