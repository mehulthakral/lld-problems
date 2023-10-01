package service;

import exceptions.ParkingLotException;
import model.Car;
import model.ParkingLot;
import model.Slot;
import strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotService {

    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    public void createParkingLot(ParkingLot parkingLot, ParkingStrategy parkingStrategy) {
        if (this.parkingLot != null) {
            throw new ParkingLotException("Parking Lot already exists, not required to re-create!");
        }
        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        for (Integer i=1; i <= parkingLot.getCapacity(); i++) {
            parkingStrategy.addSlot(i);
        }
    }

    public Integer park(Car car) {
        validateParkingLot();
        Integer slotNum = parkingStrategy.getNextSlot();
        parkingLot.park(car, slotNum);
        parkingStrategy.removeSlot(slotNum);
        return slotNum;
    }

    public void unPark(Integer slotNum) {
        validateParkingLot();
        parkingLot.makeSlotFree(slotNum);
        parkingStrategy.addSlot(slotNum);
    }

    public List<Slot> getOccupiedSlots() {
        validateParkingLot();
        List<Slot> occupiedSlots = new ArrayList<>();
        for (Integer i = 1; i <= parkingLot.getCapacity(); i++) {
            if (!parkingLot.getSlot(i).isSlotFree()) {
                occupiedSlots.add(parkingLot.getSlot(i));
            }
        }
        return occupiedSlots;
    }

    private void validateParkingLot() {
        if (parkingLot == null) {
            throw new ParkingLotException("Parking Lot doesn't exist. Create it first!");
        }
    }

    public List<Slot> getSlotsForColor(final String color) {
        List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream().filter(os -> os.getParkedCar().getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

}
