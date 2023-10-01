import inventory.VehicleInventoryManagement;
import model.*;
import model.reservation.Reservation;
import model.vehicle.Vehicle;
import model.vehicle.VehicleStatus;
import model.vehicle.VehicleType;
import system.VehicleRentalSystem;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Setting Up
        VehicleRentalSystem vehicleRentalSystem = new VehicleRentalSystem();

        Vehicle vehicle1 = Vehicle.builder().vehicleID(1).vehicleType(VehicleType.CAR)
                .vehicleStatus(VehicleStatus.ACTIVE).dailyRentalCost(50).noOfSeat(5).build();
        Vehicle vehicle2 = Vehicle.builder().vehicleID(2).vehicleType(VehicleType.CAR)
                .vehicleStatus(VehicleStatus.ACTIVE).dailyRentalCost(55).noOfSeat(5).build();

        Location location = Location.builder().address("Shop 1").city("BLR").state("KAR")
                .pincode(5678).build();
        Store store = new Store(123, location);
        store.addVehicles(Arrays.asList(vehicle1, vehicle2));

        User user = User.builder().userId(123).drivingLicense("abc").build();

        vehicleRentalSystem.addStores(store);
        vehicleRentalSystem.addUsers(user);

        // User flow
        User currentUser = vehicleRentalSystem.getUser(123);

        Store currentStore = vehicleRentalSystem.getStore(5678);

        List<Vehicle> vehicles = currentStore.getVehicles(VehicleType.CAR);

        Date pickupDate = new Date(2023, Calendar.MARCH, 26);
        Date dropDate = new Date(2023, Calendar.MARCH, 28);

        Reservation reservation = vehicleRentalSystem.reserve(currentStore, vehicles.get(0), currentUser, pickupDate,
                dropDate);

        Bill bill = vehicleRentalSystem.generateBill(reservation);

        Payment payment = vehicleRentalSystem.getPayment(bill);

        payment.payBill();

        vehicleRentalSystem.completeReservation(reservation);
    }
}
