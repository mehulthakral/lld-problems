package executors;

import exceptions.NoFreeSlotFoundException;
import model.Car;
import model.Command;
import printer.OutputPrinter;
import service.ParkingLotService;

import java.util.List;

public class ParkCommandExecutor extends CommandExecutor {

    public static final String COMMAND_NAME = "park";

    public ParkCommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        return command.getParams().size() == 2;
    }

    @Override
    public void execute(Command command) {
        try {
            final List<String> params = command.getParams();
            final Car car = new Car(params.get(0), params.get(1));
            Integer slotNum = parkingLotService.park(car);
            outputPrinter.park(slotNum);
        } catch (NoFreeSlotFoundException e) {
            outputPrinter.noFreeSlotFound();
        }
    }

}
