package strategy;

import exceptions.NoFreeSlotFoundException;
import java.util.TreeSet;

public class NaturalOrderingParkingStrategy implements ParkingStrategy {

    private TreeSet<Integer> slotNums;

    public NaturalOrderingParkingStrategy() {
        slotNums = new TreeSet<>();
    }

    @Override
    public void addSlot(Integer slot) {
        slotNums.add(slot);
    }

    @Override
    public void removeSlot(Integer slot) {
        slotNums.remove(slot);
    }

    @Override
    public Integer getNextSlot() {
        if (slotNums.isEmpty()) {
            throw new NoFreeSlotFoundException();
        }
        return slotNums.first();
    }
}
