package executors;

import model.Car;
import model.Command;
import model.Slot;
import org.junit.Before;
import org.junit.Test;
import printer.OutputPrinter;
import service.ParkingLotService;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class RegNumToSlotCommandExecutorTest {

    ParkingLotService parkingLotService;
    OutputPrinter outputPrinter;
    RegNumToSlotCommandExecutor regNumToSlotCommandExecutor;

    @Before
    public void setUp() {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        regNumToSlotCommandExecutor = new RegNumToSlotCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidate() {
        assertTrue(regNumToSlotCommandExecutor.isValid(new Command("slot_number_for_registration_number KA-01-JR-0779")));
        assertFalse(regNumToSlotCommandExecutor.isValid(new Command("slot_number_for_registration_number")));
        assertFalse(regNumToSlotCommandExecutor.isValid(new Command("slot_number_for_registration_number KA-01-JR-0779 1")));
    }

    @Test
    public void testExecuteWithMatch() {
        Car testCar = new Car("KA-01-JR-0779", "White");
        when(parkingLotService.getOccupiedSlots()).thenReturn(Arrays.asList(new Slot(testCar, 1),
                new Slot(testCar, 2)));
        regNumToSlotCommandExecutor.execute(new Command("slot_number_for_registration_number KA-01-JR-0779"));
        verify(parkingLotService).getOccupiedSlots();
        verify(outputPrinter).printWithNewLine("Found slot for RegNum: KA-01-JR-0779 is 1");
    }

    @Test
    public void testExecuteWithNoMatch() {
        Car testCar = new Car("KA-01-JR-0778", "White");
        when(parkingLotService.getOccupiedSlots()).thenReturn(Arrays.asList(new Slot(testCar, 1),
                new Slot(testCar, 2)));
        regNumToSlotCommandExecutor.execute(new Command("slot_number_for_registration_number KA-01-JR-0779"));
        verify(parkingLotService).getOccupiedSlots();
        verify(outputPrinter).printWithNewLine("Input car doesn't exist in ParkingLot");
    }

}
