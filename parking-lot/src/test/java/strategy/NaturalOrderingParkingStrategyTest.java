package strategy;

import exceptions.NoFreeSlotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NaturalOrderingParkingStrategyTest {

    @Test
    public void getNextSlot() {
        ParkingStrategy parkingStrategy = new NaturalOrderingParkingStrategy();
        parkingStrategy.addSlot(1);
        assertEquals(new Integer(1), parkingStrategy.getNextSlot());
    }

    @Test(expected = NoFreeSlotFoundException.class)
    public void getNextSlotWithNoSlot() {
        ParkingStrategy parkingStrategy = new NaturalOrderingParkingStrategy();
        parkingStrategy.getNextSlot();
    }

    @Test(expected = NoFreeSlotFoundException.class)
    public void getNextSlotWithRemovedSlot() {
        ParkingStrategy parkingStrategy = new NaturalOrderingParkingStrategy();
        parkingStrategy.addSlot(1);
        parkingStrategy.removeSlot(1);
        parkingStrategy.getNextSlot();
    }
}
