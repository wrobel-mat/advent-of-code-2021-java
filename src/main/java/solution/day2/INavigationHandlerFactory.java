package solution.day2;

interface INavigationHandlerFactory {
    INavigationCommandHandler getNavigationCommandHandler(Instruction instruction);
}
