import exceptions.InvalidModeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class MainTest {

    InputStream sysInBackup;
    PrintStream sysOutBackup;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        sysInBackup = System.in;
        sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    @Test(expected = InvalidModeException.class)
    public void isInvalidMode() {
        String[] testArgs = new String[]{"a", "b"};
        Main.main(testArgs);
    }

    @Test
    public void testInValidFile() {
        Main.main(new String[] {"random_file.txt"});

        assertEquals("Input File Not Found\n", outContent.toString());
    }

    @Test
    public void testInteractiveModeWithInvalidCommand() {
        String commands = "create_parking_lot 6 1\n";

        System.setIn(new ByteArrayInputStream(commands.getBytes()));

        Main.main(new String[]{});
        String expectedOutput = "Welcome to ParkingLot!\n" +
                "Got Exception - exceptions.InvalidCommandException\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testInteractiveMode() {
        String commands = "create_parking_lot 6\n" +
                "park KA-01-HH-1234 White\n" +
                "park KA-01-HH-9999 White\n" +
                "park KA-01-BB-0001 Black\n" +
                "park KA-01-HH-7777 Red\n" +
                "park KA-01-HH-2701 Blue\n" +
                "park KA-01-HH-3141 Black\n" +
                "leave 4\n" +
                "status\n" +
                "park KA-01-P-333 White\n" +
                "park DL-12-AA-9999 White\n" +
                "registration_numbers_for_cars_with_colour White\n" +
                "slot_numbers_for_cars_with_colour White\n" +
                "slot_number_for_registration_number KA-01-HH-3141\n" +
                "slot_number_for_registration_number MH-04-AY-1111\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(commands.getBytes()));

        Main.main(new String[]{});
        String expectedOutput = "Welcome to ParkingLot!\n" +
                "Parking Lot created with 6 slots.\n" +
                "Parked car at slot - 1\n" +
                "Parked car at slot - 2\n" +
                "Parked car at slot - 3\n" +
                "Parked car at slot - 4\n" +
                "Parked car at slot - 5\n" +
                "Parked car at slot - 6\n" +
                "Car from slot 4 got exited!\n" +
                "Status of parked cars:\n" +
                "Slot - 1 RegNum - KA-01-HH-1234 Color - White\n" +
                "Slot - 2 RegNum - KA-01-HH-9999 Color - White\n" +
                "Slot - 3 RegNum - KA-01-BB-0001 Color - Black\n" +
                "Slot - 5 RegNum - KA-01-HH-2701 Color - Blue\n" +
                "Slot - 6 RegNum - KA-01-HH-3141 Color - Black\n" +
                "Parked car at slot - 4\n" +
                "No free slot found to park\n" +
                "RegNums of cars of color White are KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n" +
                "Slot Nums of cars of color White are 1, 2, 4\n" +
                "Found slot for RegNum: KA-01-HH-3141 is 6\n" +
                "Input car doesn't exist in ParkingLot\n" +
                "Thanks for visiting ParkingLot!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testFileMode() {
        Main.main(new String[]{"file_input.txt"});
        String expectedOutput = "Welcome to ParkingLot!\n" +
                "Parking Lot created with 6 slots.\n" +
                "Parked car at slot - 1\n" +
                "Parked car at slot - 2\n" +
                "Parked car at slot - 3\n" +
                "Parked car at slot - 4\n" +
                "Parked car at slot - 5\n" +
                "Parked car at slot - 6\n" +
                "Car from slot 4 got exited!\n" +
                "Status of parked cars:\n" +
                "Slot - 1 RegNum - KA-01-HH-1234 Color - White\n" +
                "Slot - 2 RegNum - KA-01-HH-9999 Color - White\n" +
                "Slot - 3 RegNum - KA-01-BB-0001 Color - Black\n" +
                "Slot - 5 RegNum - KA-01-HH-2701 Color - Blue\n" +
                "Slot - 6 RegNum - KA-01-HH-3141 Color - Black\n" +
                "Parked car at slot - 4\n" +
                "No free slot found to park\n" +
                "RegNums of cars of color White are KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n" +
                "Slot Nums of cars of color White are 1, 2, 4\n" +
                "Found slot for RegNum: KA-01-HH-3141 is 6\n" +
                "Input car doesn't exist in ParkingLot\n" +
                "Thanks for visiting ParkingLot!\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testFileModeWithInvalidCommand() {
        Main.main(new String[] {"file_input_invalid.txt"});

        String expectedOutput = "Welcome to ParkingLot!\n" +
                "Got Exception - exceptions.InvalidCommandException\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
