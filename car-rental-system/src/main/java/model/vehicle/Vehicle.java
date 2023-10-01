package model.vehicle;


import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class Vehicle {

    int vehicleID;
    int vehicleNumber;
    VehicleType vehicleType;
    String companyName;
    String modelName;
    int kmDriven;
    Date manufacturingDate;
    int average;
    int cc;
    int dailyRentalCost;
    int hourlyRentalCost;
    int noOfSeat;
    VehicleStatus vehicleStatus;

}
