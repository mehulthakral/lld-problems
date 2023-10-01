package model;

public class Slot {
    private Car parkedCar;
    private final Integer slotNum;

    public Slot(Car parkedCar, Integer slotNum) {
        this.parkedCar = parkedCar;
        this.slotNum = slotNum;
    }

    public void assignCar(Car parkedCar) {
        this.parkedCar = parkedCar;
    }

    public void deAssignCar() {
        this.parkedCar = null;
    }

    public boolean isSlotFree(){
        return this.parkedCar == null;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public Integer getSlotNum() {
        return slotNum;
    }
}
