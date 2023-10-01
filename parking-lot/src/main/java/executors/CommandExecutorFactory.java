package executors;

import exceptions.InvalidCommandException;
import model.Command;
import printer.OutputPrinter;
import service.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    Map<String, CommandExecutor> commandExecutors = new HashMap<>();

    public CommandExecutorFactory(ParkingLotService parkingLotService) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        commandExecutors.put(StatusCommandExecutor.COMMAND_NAME, new StatusCommandExecutor(parkingLotService, outputPrinter));
        commandExecutors.put(CreateParkingLotCommandExecutor.COMMAND_NAME, new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter));
        commandExecutors.put(RegNumToSlotCommandExecutor.COMMAND_NAME, new RegNumToSlotCommandExecutor(parkingLotService, outputPrinter));
        commandExecutors.put(SlotsForColorCommandExecutor.COMMAND_NAME, new SlotsForColorCommandExecutor(parkingLotService, outputPrinter));
        commandExecutors.put(RegNumsForColorCommandExecutor.COMMAND_NAME, new RegNumsForColorCommandExecutor(parkingLotService, outputPrinter));
        commandExecutors.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(parkingLotService, outputPrinter));
        commandExecutors.put(ParkCommandExecutor.COMMAND_NAME, new ParkCommandExecutor(parkingLotService, outputPrinter));
        commandExecutors.put(LeaveCommandExecutor.COMMAND_NAME, new LeaveCommandExecutor(parkingLotService,outputPrinter));
    }

    public CommandExecutor getCommand(Command command) {
        if (commandExecutors.containsKey(command.getCommandName())) {
            return commandExecutors.get(command.getCommandName());
        } else {
            throw new InvalidCommandException();
        }
    }
}
