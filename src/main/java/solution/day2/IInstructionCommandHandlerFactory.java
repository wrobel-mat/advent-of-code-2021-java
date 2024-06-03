package solution.day2;

public interface IInstructionCommandHandlerFactory {
    IInstructionCommandHandler getInstructionCommandHandler(Instruction instruction);
}
