package executors;

import model.Car;
import model.Command;
import model.Slot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import printer.OutputPrinter;
import service.ParkingLotService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class StatusCommandExecutorTest {

    ParkingLotService parkingLotService;
    OutputPrinter outputPrinter;
    StatusCommandExecutor statusCommandExecutor;

    @Before
    public void setUp() {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        statusCommandExecutor = new StatusCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidate() {
        assertTrue(statusCommandExecutor.isValid(new Command("status")));
        assertFalse(statusCommandExecutor.isValid(new Command("status 1")));
        assertFalse(statusCommandExecutor.isValid(new Command("status abc")));
    }

    @Test
    public void testExecuteWithTwoOccupiedSlots() {
        Car testCar = new Car("KA-01-JR-0779", "White");
        when(parkingLotService.getOccupiedSlots()).thenReturn(Arrays.asList(new Slot(testCar, 1),
                new Slot(testCar, 2)));
        statusCommandExecutor.execute(new Command("status"));
        verify(parkingLotService).getOccupiedSlots();
        verify(outputPrinter).printWithNewLine("Status of parked cars:");
        verify(outputPrinter).printWithNewLine("Slot - 1 RegNum - KA-01-JR-0779 Color - White");
        verify(outputPrinter).printWithNewLine("Slot - 2 RegNum - KA-01-JR-0779 Color - White");
        Collection<Invocation> invocations = Mockito.mockingDetails(outputPrinter).getInvocations();
        assertEquals(3, invocations.size());
    }

    @Test
    public void testExecuteWithNoSlots() {
        when(parkingLotService.getOccupiedSlots()).thenReturn(Collections.emptyList());
        statusCommandExecutor.execute(new Command("status"));
        verify(parkingLotService).getOccupiedSlots();
        verify(outputPrinter).printWithNewLine("ParkingLot is empty");
        Collection<Invocation> invocations = Mockito.mockingDetails(outputPrinter).getInvocations();
        assertEquals(1, invocations.size());
    }
}
