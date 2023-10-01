package executors;

import model.Command;
import model.Slot;
import printer.OutputPrinter;
import service.ParkingLotService;

import java.util.List;
import java.util.stream.Collectors;

public class RegNumsForColorCommandExecutor extends CommandExecutor {

    static final String COMMAND_NAME = "registration_numbers_for_cars_with_colour";

    public RegNumsForColorCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
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
            String resultString = foundSlots.stream().map(fs -> fs.getParkedCar().getVehicleNumber())
                    .collect(Collectors.joining(", "));
            outputPrinter.printWithNewLine(String.format("RegNums of cars of color %s are %s", color, resultString));
        }
    }
}
