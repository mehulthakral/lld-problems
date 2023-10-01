package mode;

import executors.CommandExecutor;
import executors.CommandExecutorFactory;
import model.Command;
import printer.OutputPrinter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileMode extends Mode {

    private String fileName;

    public FileMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter, String fileName) {
        super(commandExecutorFactory, outputPrinter);
        this.fileName = fileName;
    }

    @Override
    public void process() {
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
                outputPrinter.welcome();
                String inputLine = bufferedReader.readLine();
                while (inputLine != null) {
                    Command command = new Command(inputLine);
                    processCommand(command);
                    inputLine = bufferedReader.readLine();
                }
                outputPrinter.end();
            }
        } catch (FileNotFoundException e) {
            outputPrinter.fileNotFound();
        } catch (Exception e) {
            outputPrinter.exception(e.toString());
        }
    }
}
