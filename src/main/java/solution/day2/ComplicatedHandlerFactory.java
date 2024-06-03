package solution.day2;

import java.util.Map;

public class ComplicatedHandlerFactory implements IInstructionCommandHandlerFactory {

    private final Map<Command, IInstructionCommandHandler> handlers =
            Map.of(
                    Command.FORWARD, new ForwardHandler(),
                    Command.DOWN, new DownHandler(),
                    Command.UP, new UpHandler()
            );

    @Override
    public IInstructionCommandHandler getInstructionCommandHandler(Instruction instruction) {
        return handlers.getOrDefault(instruction.command(), new DoNothingHandler());
    }

    private record DoNothingHandler() implements IInstructionCommandHandler {
        @Override
        public void handle(Submarine submarine, Instruction instruction) {
            // do nothing
        }
    }

    private record ForwardHandler() implements IInstructionCommandHandler {
        @Override
        public void handle(Submarine submarine, Instruction instruction) {
            submarine.horizontalPosition += instruction.units();
            submarine.depth += submarine.aim * instruction.units();
        }
    }

    private record DownHandler() implements IInstructionCommandHandler {
        @Override
        public void handle(Submarine submarine, Instruction instruction) {
            submarine.aim += instruction.units();
        }
    }

    private record UpHandler() implements IInstructionCommandHandler {
        @Override
        public void handle(Submarine submarine, Instruction instruction) {
            submarine.aim -= instruction.units();
        }
    }
}
