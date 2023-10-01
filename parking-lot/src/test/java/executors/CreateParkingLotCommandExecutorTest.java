package executors;

import model.Command;
import model.ParkingLot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import printer.OutputPrinter;
import service.ParkingLotService;
import strategy.NaturalOrderingParkingStrategy;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateParkingLotCommandExecutorTest {

    ParkingLotService parkingLotService;
    OutputPrinter outputPrinter;
    CreateParkingLotCommandExecutor createParkingLotCommandExecutor;

    @Before
    public void setUp() {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        createParkingLotCommandExecutor = new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidate() {
        assertTrue(createParkingLotCommandExecutor.isValid(new Command("create_parking_lot 5")));
        assertFalse(createParkingLotCommandExecutor.isValid(new Command("create_parking_lot")));
        assertFalse(createParkingLotCommandExecutor.isValid(new Command("create_parking_lot abc")));
        assertFalse(createParkingLotCommandExecutor.isValid(new Command("create_parking_lot 5 abc")));
    }

    @Test
    public void testExecute() {
        createParkingLotCommandExecutor.execute(new Command("create_parking_lot 5"));
        ArgumentCaptor<ParkingLot> argument = ArgumentCaptor.forClass(ParkingLot.class);
        verify(parkingLotService).createParkingLot(argument.capture(), any(NaturalOrderingParkingStrategy.class));
        assertEquals(new Integer(5), argument.getValue().getCapacity());
        verify(outputPrinter).printWithNewLine("Parking Lot created with 5 slots.");
    }

}
