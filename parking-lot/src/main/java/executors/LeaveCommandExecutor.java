package executors;

import model.Command;
import printer.OutputPrinter;
import service.ParkingLotService;
import validator.IntegerValidator;

public class LeaveCommandExecutor extends CommandExecutor {

    public static final String COMMAND_NAME = "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        return command.getParams().size() == 1 && IntegerValidator.isInteger(command.getParams().get(0));
    }

    @Override
    public void execute(Command command) {
        Integer slotNum = Integer.parseInt(command.getParams().get(0));
        parkingLotService.unPark(slotNum);
        outputPrinter.printWithNewLine(String.format("Car from slot %d got exited!", slotNum));
    }
}
