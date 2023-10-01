package executors;

import model.Command;
import model.Slot;
import printer.OutputPrinter;
import service.ParkingLotService;

import java.util.List;

public class StatusCommandExecutor extends CommandExecutor {

    static final String COMMAND_NAME = "status";

    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) {
        List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        if (occupiedSlots.isEmpty()) {
            outputPrinter.printWithNewLine("ParkingLot is empty");
        } else {
            outputPrinter.printWithNewLine("Status of parked cars:");
            for (Slot os: occupiedSlots) {
                outputPrinter.printWithNewLine(String.format("Slot - %d RegNum - %s Color - %s", os.getSlotNum(),
                        os.getParkedCar().getVehicleNumber(), os.getParkedCar().getColor()));
            }
        }
    }
}
