package solution.day2;

public interface INavigationHandlerFactory {
    INavigationCommandHandler getNavigationCommandHandler(Instruction instruction);
}
