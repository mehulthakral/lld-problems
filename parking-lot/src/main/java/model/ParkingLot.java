package model;

import exceptions.InvalidParkingCapacityException;
import exceptions.InvalidSlotException;
import exceptions.SlotUnavailableException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<Integer, Slot> slots;
    private Integer capacity;

    private final Integer MAX_CAPACITY = 100000;

    public ParkingLot(int capacity) {
        if (capacity<0 || capacity>MAX_CAPACITY) {
            throw new InvalidParkingCapacityException();
        }
        this.capacity = capacity;
        this.slots = new HashMap<>();
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Slot getSlot(Integer slotNum) {
        if (slotNum <= 0 || slotNum > getCapacity()) {
            throw new InvalidSlotException();
        }
        if (!getSlots().containsKey(slotNum)) {
            getSlots().put(slotNum, new Slot(null, slotNum));
        }
        return getSlots().get(slotNum);
    }

    public Slot park(Car car, Integer slotNum) {
        Slot slot = getSlot(slotNum);
        if (!slot.isSlotFree()) {
            throw new SlotUnavailableException();
        }
        slot.assignCar(car);
        return slot;
    }

    public Slot makeSlotFree(Integer slotNum) {
        Slot slot = getSlot(slotNum);
        slot.deAssignCar();
        return slot;
    }

}
