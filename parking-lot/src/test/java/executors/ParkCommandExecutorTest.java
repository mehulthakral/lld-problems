package executors;

import exceptions.NoFreeSlotFoundException;
import model.Car;
import model.Command;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import printer.OutputPrinter;
import service.ParkingLotService;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ParkCommandExecutorTest {

    ParkingLotService parkingLotService;
    OutputPrinter outputPrinter;
    ParkCommandExecutor parkCommandExecutor;

    @Before
    public void setUp() {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        parkCommandExecutor = new ParkCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidate() {
        assertTrue(parkCommandExecutor.isValid(new Command("park KA-01-JR-0779 white")));
        assertFalse(parkCommandExecutor.isValid(new Command("park")));
        assertFalse(parkCommandExecutor.isValid(new Command("park KA-01-JR-0779")));
        assertFalse(parkCommandExecutor.isValid(new Command("park KA-01-JR-0779 white abc")));
    }

    @Test
    public void testExecute() {
        when(parkingLotService.park(any(Car.class))).thenReturn(1);
        parkCommandExecutor.execute(new Command("park KA-01-JR-0779 white"));
        ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
        verify(parkingLotService).park(argument.capture());
        assertEquals("KA-01-JR-0779", argument.getValue().getVehicleNumber());
        assertEquals("white", argument.getValue().getColor());
        verify(outputPrinter).park(1);
    }

    @Test
    public void testExecuteWithException() {
        when(parkingLotService.park(any(Car.class))).thenThrow(new NoFreeSlotFoundException());
        parkCommandExecutor.execute(new Command("park KA-01-JR-0779 white"));
        ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
        verify(parkingLotService).park(argument.capture());
        assertEquals("KA-01-JR-0779", argument.getValue().getVehicleNumber());
        assertEquals("white", argument.getValue().getColor());
        verify(outputPrinter).noFreeSlotFound();
    }
}
