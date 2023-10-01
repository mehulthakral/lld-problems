package mode;

import executors.CommandExecutor;
import executors.CommandExecutorFactory;
import model.Command;
import printer.OutputPrinter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InteractiveMode extends Mode {

    public InteractiveMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        super(commandExecutorFactory, outputPrinter);
    }

    @Override
    public void process() {
        try {

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                outputPrinter.welcome();
                String inputLine = bufferedReader.readLine();
                while(true) {
                    Command command = new Command(inputLine);
                    processCommand(command);
                    if (command.getCommandName().equals("exit")) {
                        break;
                    }
                    inputLine = bufferedReader.readLine();
                }
            }

        } catch (Exception e) {
            outputPrinter.exception(e.toString());
        }
    }
}
