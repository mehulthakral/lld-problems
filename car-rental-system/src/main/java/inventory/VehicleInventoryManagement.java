package inventory;

import model.vehicle.Vehicle;
import model.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class VehicleInventoryManagement {

    List<Vehicle> vehicleList = new ArrayList<>();

    public void addVehicles(List<Vehicle> vehicles) {
        vehicleList.addAll(vehicles);
    }

    public List<Vehicle> getVehicles(VehicleType type) {
        return vehicleList.stream().filter(v -> v.getVehicleType() == type).collect(Collectors.toList());
    }
}
