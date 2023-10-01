package executors;

import model.Command;
import model.Slot;
import printer.OutputPrinter;
import service.ParkingLotService;

import java.util.List;
import java.util.Optional;

public class RegNumToSlotCommandExecutor extends CommandExecutor {

    static final String COMMAND_NAME = "slot_number_for_registration_number";

    public RegNumToSlotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        String regNum = command.getParams().get(0);
        Optional<Slot> foundSlot = occupiedSlots.stream()
                .filter(os -> os.getParkedCar().getVehicleNumber().equals(regNum)).findFirst();
        if (foundSlot.isPresent()) {
            outputPrinter.printWithNewLine(String.format("Found slot for RegNum: %s is %d",
                    regNum, foundSlot.get().getSlotNum()));
        } else {
            outputPrinter.printWithNewLine("Input car doesn't exist in ParkingLot");
        }
    }
}
