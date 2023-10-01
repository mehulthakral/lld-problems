package executors;

import model.Command;
import org.junit.Before;
import org.junit.Test;
import printer.OutputPrinter;
import service.ParkingLotService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LeaveCommandExecutorTest {

    ParkingLotService parkingLotService;
    OutputPrinter outputPrinter;
    LeaveCommandExecutor leaveCommandExecutor;

    @Before
    public void setUp() {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        leaveCommandExecutor = new LeaveCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidate() {
        assertTrue(leaveCommandExecutor.isValid(new Command("leave 1")));
        assertFalse(leaveCommandExecutor.isValid(new Command("leave")));
        assertFalse(leaveCommandExecutor.isValid(new Command("leave abc")));
        assertFalse(leaveCommandExecutor.isValid(new Command("leave 1 abc")));
    }

    @Test
    public void testExecute() {
        leaveCommandExecutor.execute(new Command("leave 1"));
        verify(parkingLotService).unPark(1);
        verify(outputPrinter).printWithNewLine("Car from slot 1 got exited!");
    }
}
