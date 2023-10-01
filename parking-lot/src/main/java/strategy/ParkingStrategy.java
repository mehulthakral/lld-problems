package strategy;

public interface ParkingStrategy {

    void addSlot(Integer slotNum);
    void removeSlot(Integer slotNum);
    Integer getNextSlot();

}
