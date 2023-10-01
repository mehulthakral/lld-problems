package executors;

import model.Command;
import model.ParkingLot;
import printer.OutputPrinter;
import service.ParkingLotService;
import strategy.NaturalOrderingParkingStrategy;
import strategy.ParkingStrategy;
import validator.IntegerValidator;
import java.util.List;

public class CreateParkingLotCommandExecutor extends CommandExecutor {

    static final String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        List<String> params = command.getParams();
        if (params.size() != 1) {
            return false;
        }
        return IntegerValidator.isInteger(params.get(0));
    }

    @Override
    public void execute(Command command) {
        int capacity = Integer.parseInt(command.getParams().get(0));
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingStrategy parkingStrategy = new NaturalOrderingParkingStrategy();
        parkingLotService.createParkingLot(parkingLot, parkingStrategy);
        outputPrinter.printWithNewLine("Parking Lot created with " + parkingLot.getCapacity()
                + " slots.");
    }
}
