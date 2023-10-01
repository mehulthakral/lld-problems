package model.vehicle;

import java.util.Date;

public class Bike extends Vehicle {
    Bike(int vehicleID, int vehicleNumber, VehicleType vehicleType, String companyName, String modelName, int kmDriven, Date manufacturingDate, int average, int cc, int dailyRentalCost, int hourlyRentalCost, int noOfSeat, VehicleStatus vehicleStatus) {
        super(vehicleID, vehicleNumber, vehicleType, companyName, modelName, kmDriven, manufacturingDate, average, cc, dailyRentalCost, hourlyRentalCost, noOfSeat, vehicleStatus);
    }
}
