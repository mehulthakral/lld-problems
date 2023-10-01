package executors;

import model.Car;
import model.Command;
import model.Slot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import printer.OutputPrinter;
import service.ParkingLotService;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class RegNumsForColorCommandExecutorTest {
    ParkingLotService parkingLotService;
    OutputPrinter outputPrinter;
    RegNumsForColorCommandExecutor regNumsForColorCommandExecutor;

    @Before
    public void setUp() {
        parkingLotService = mock(ParkingLotService.class);
        outputPrinter = mock(OutputPrinter.class);
        regNumsForColorCommandExecutor = new RegNumsForColorCommandExecutor(parkingLotService, outputPrinter);
    }

    @Test
    public void testValidate() {
        assertTrue(regNumsForColorCommandExecutor.isValid(new Command("registration_numbers_for_cars_with_colour white")));
        assertFalse(regNumsForColorCommandExecutor.isValid(new Command("registration_numbers_for_cars_with_colour")));
        assertFalse(regNumsForColorCommandExecutor.isValid(new Command("registration_numbers_for_cars_with_colour white 1")));
    }

    @Test
    public void testExecuteWithMatch() {
        Car testCar = new Car("KA-01-JR-0779", "White");
        when(parkingLotService.getSlotsForColor(anyString())).thenReturn(Arrays.asList(new Slot(testCar, 1),
                new Slot(testCar, 2)));
        regNumsForColorCommandExecutor.execute(new Command("registration_numbers_for_cars_with_colour White"));
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(parkingLotService).getSlotsForColor(argument.capture());
        assertEquals("White", argument.getValue());
        verify(outputPrinter).printWithNewLine("RegNums of cars of color White are KA-01-JR-0779, KA-01-JR-0779");
    }

    @Test
    public void testExecuteWithNoMatch() {
        when(parkingLotService.getSlotsForColor(anyString())).thenReturn(Collections.emptyList());
        regNumsForColorCommandExecutor.execute(new Command("registration_numbers_for_cars_with_colour White"));
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(parkingLotService).getSlotsForColor(argument.capture());
        assertEquals("White", argument.getValue());
        verify(outputPrinter).printWithNewLine("No car of color White exists");
    }
}
