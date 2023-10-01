package model;

import exceptions.InvalidParkingCapacityException;
import exceptions.InvalidSlotException;
import exceptions.SlotUnavailableException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParkingLotTest {

    @Test
    public void createParkingLot() {
        ParkingLot parkingLot = new ParkingLot(5);
        assertEquals(new Integer(5), parkingLot.getCapacity());
    }

    @Test(expected = InvalidParkingCapacityException.class)
    public void parkingLotWithHigherCapacity() {
        new ParkingLot(1000000);
    }

    @Test(expected = InvalidParkingCapacityException.class)
    public void parkingLotWithInvalidCapacity() {
        new ParkingLot(-1);
    }

    @Test
    public void getParkingLotSlot() {
        ParkingLot parkingLot = new ParkingLot(5);
        Slot slot = parkingLot.getSlot(5);
        assertEquals(new Integer(5), slot.getSlotNum());
        assertNull(slot.getParkedCar());
    }

    @Test(expected = InvalidSlotException.class)
    public void getParkingLotHigherSlot() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.getSlot(6);
    }

    @Test(expected = InvalidSlotException.class)
    public void getParkingLotInvalidSlot() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.getSlot(0);
    }

    @Test
    public void parkCar() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park(new Car("KA-01-JR-0779", "White"), 1);
        Slot slot = parkingLot.getSlot(1);
        assertEquals(Boolean.FALSE, slot.isSlotFree());
        assertEquals("White", slot.getParkedCar().getColor());
        assertEquals("KA-01-JR-0779", slot.getParkedCar().getVehicleNumber());
    }

    @Test(expected = SlotUnavailableException.class)
    public void parkCarWithNonFreeSlot() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park(new Car("KA-01-JR-0779", "White"), 1);
        parkingLot.park(new Car("KA-01-JR-0779", "White"), 1);
    }

    @Test
    public void unParkCar() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park(new Car("KA-01-JR-0779", "White"), 1);
        parkingLot.makeSlotFree(1);
        Slot slot = parkingLot.getSlot(1);
        assertEquals(Boolean.TRUE, slot.isSlotFree());
    }


}
