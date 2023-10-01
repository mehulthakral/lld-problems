package mode;

import exceptions.InvalidCommandException;
import executors.CommandExecutor;
import executors.CommandExecutorFactory;
import model.Command;
import printer.OutputPrinter;

public abstract class Mode {

    protected CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;

    protected Mode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    protected void processCommand(Command command) {
        CommandExecutor commandExecutor = commandExecutorFactory.getCommand(command);
        if (commandExecutor.isValid(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    public abstract void process();

}
