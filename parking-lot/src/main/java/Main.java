import exceptions.InvalidModeException;
import executors.CommandExecutorFactory;
import mode.FileMode;
import mode.InteractiveMode;
import printer.OutputPrinter;
import service.ParkingLotService;

public class Main {
    public static void main(final String[] args) {
        OutputPrinter outputPrinter = new OutputPrinter();
        ParkingLotService parkingLotService = new ParkingLotService();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);

        if (isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory, outputPrinter).process();
        } else if (isFileMode(args)) {
            new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
        } else {
            throw new InvalidModeException();
        }
    }

    private static boolean isInteractiveMode(final String[] args) {
        return args.length == 0;
    }

    private static boolean isFileMode(final String[] args) {
        return args.length == 1;
    }
}
