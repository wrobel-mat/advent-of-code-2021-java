package solution.day2;

import java.util.ArrayList;
import java.util.List;

public class SubmarineCoursePlan {

    private final List<Instruction> instructions = new ArrayList<>();

    public SubmarineCoursePlan(List<String> input) {
        for (String command : input) {
            String[] s = command.split(" ");
            instructions.add(new Instruction(Command.valueOf(s[0].toUpperCase()), Integer.parseInt(s[1])));
        }
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }
}
