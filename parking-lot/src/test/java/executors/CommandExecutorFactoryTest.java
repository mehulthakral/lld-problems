package executors;

import exceptions.InvalidCommandException;
import model.Command;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ParkingLotService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CommandExecutorFactoryTest {

    CommandExecutorFactory commandExecutorFactory;

    @Before
    public void setUp() {
        ParkingLotService parkingLotService = mock(ParkingLotService.class);
        commandExecutorFactory = new CommandExecutorFactory(parkingLotService);
    }

    @Test
    public void getCommandValid() {
        CommandExecutor commandExecutor = commandExecutorFactory.getCommand(new Command("status"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof StatusCommandExecutor);
    }

    @Test(expected = InvalidCommandException.class)
    public void getCommandInValid() {
        commandExecutorFactory.getCommand(new Command("statusa"));
    }
}
