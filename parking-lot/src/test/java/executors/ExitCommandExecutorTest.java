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

public class ExitCommandExecutorTest {

    ParkingLotService parkingLotService;
    OutputPrinter outputPrinter;
    ExitCommandExecutor exitCommandExecutor;

    @Before
    public void setUp() {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        exitCommandExecutor = new ExitCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidate() {
        assertTrue(exitCommandExecutor.isValid(new Command("exit")));
        assertFalse(exitCommandExecutor.isValid(new Command("exit abc")));
        assertFalse(exitCommandExecutor.isValid(new Command("exit 1")));
    }

    @Test
    public void testExecute() {
        exitCommandExecutor.execute(new Command("exit"));
        verify(outputPrinter).end();
    }
}
