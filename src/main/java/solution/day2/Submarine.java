package solution.day2;

public class Submarine {

    int horizontalPosition = 0;
    int depth = 0;
    int aim = 0;

    public void navigate(SubmarineCoursePlan coursePlan, IInstructionCommandHandlerFactory commandHandlerFactory) {
        coursePlan.getInstructions().forEach(instruction -> {
            IInstructionCommandHandler handler = commandHandlerFactory.getInstructionCommandHandler(instruction);
            handler.handle(this, instruction);
        });
    }
}
