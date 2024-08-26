package answer;

import java.util.Map;
import java.util.TreeMap;

public class Result {

    private int year;
    private int day;
    private Map<Integer, Answer> answers;

    public static Result of(int year, int day) {
        Result result = new Result();
        result.year = year;
        result.day = day;
        result.answers = new TreeMap<>(Map.of(1, Answer.forPart(1), 2, Answer.forPart(2)));
        return result;
    }

    public void complete(int partNum, String answer) {
        Answer currentAnswer = answers.get(partNum);
        currentAnswer.setCompleted();
        currentAnswer.setAnswer(answer);
    }

    public boolean isCompleted(int partNum) {
        return answers.get(partNum).isCompleted();
    }

    public int day() {
        return day;
    }
}