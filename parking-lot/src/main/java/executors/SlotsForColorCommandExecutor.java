package executors;

import model.Command;
import model.Slot;
import printer.OutputPrinter;
import service.ParkingLotService;
import java.util.List;
import java.util.stream.Collectors;

public class SlotsForColorCommandExecutor extends CommandExecutor {

    static final String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

    public SlotsForColorCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        String color = command.getParams().get(0);
        List<Slot> foundSlots = parkingLotService.getSlotsForColor(color);
        if (foundSlots.isEmpty()) {
            outputPrinter.printWithNewLine(String.format("No car of color %s exists", color));
        } else {
            String resultString = foundSlots.stream().map(fs -> Integer.toString(fs.getSlotNum()))
                    .collect(Collectors.joining(", "));
            outputPrinter.printWithNewLine(String.format("Slot Nums of cars of color %s are %s", color, resultString));
        }
    }
}
