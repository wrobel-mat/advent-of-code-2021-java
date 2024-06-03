package solution.day2;

public class Submarine {

    private final INavigationHandlerFactory navigationHandlerFactory;
    int horizontalPosition = 0;
    int depth = 0;
    int aim = 0;

    public Submarine(INavigationHandlerFactory navigationHandlerFactory) {
        this.navigationHandlerFactory = navigationHandlerFactory;
    }

    public void navigate(SubmarineCoursePlan coursePlan) {
        coursePlan.getInstructions().forEach(instruction -> {
            INavigationCommandHandler handler = navigationHandlerFactory.getNavigationCommandHandler(instruction);
            handler.handle(this, instruction);
        });
    }
}
