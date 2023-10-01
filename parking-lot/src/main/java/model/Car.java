package model;

public class Car {
    private final String vehicleNumber;
    private final String color;

    public Car(String vehicleNumber, String color) {
        this.vehicleNumber = vehicleNumber;
        this.color = color;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getColor() {
        return color;
    }
}
