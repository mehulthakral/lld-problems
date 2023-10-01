package model;

import exceptions.InvalidCommandException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandTest {

    @Test
    public void createCommand() {
        String input = "park KA-01-JR-0779 White";
        Command command = new Command(input);
        assertEquals("park", command.getCommandName());
        assertEquals(Arrays.asList("KA-01-JR-0779", "White"), command.getParams());
    }

    @Test
    public void createCommandWithSpaces() {
        String input = "park   KA-01-JR-0779  White";
        Command command = new Command(input);
        assertEquals("park", command.getCommandName());
        assertEquals(Arrays.asList("KA-01-JR-0779", "White"), command.getParams());
    }

    @Test(expected = InvalidCommandException.class)
    public void createCommandEmpty() {
        new Command("");
    }

    @Test
    public void createCommandWithNoParams() {
        String input = "park   ";
        Command command = new Command(input);
        assertEquals("park", command.getCommandName());
        assertTrue(command.getParams().isEmpty());
    }
}
