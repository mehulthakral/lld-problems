package model;

import inventory.VehicleInventoryManagement;
import lombok.Builder;
import lombok.Getter;
import model.reservation.Reservation;
import model.reservation.ReservationStatus;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class Store {

    int storeId;
    List<Reservation> reservationList;
    Location location;
    VehicleInventoryManagement vehicleInventoryManagement;

    public Store(int storeId, Location location) {
        this.storeId = storeId;
        this.location = location;
        this.vehicleInventoryManagement = new VehicleInventoryManagement();
        this.reservationList = new ArrayList<>();
    }

    public void addVehicles(List<Vehicle> vehicles) {
        vehicleInventoryManagement.addVehicles(vehicles);
    }

    public List<Vehicle> getVehicles(VehicleType vehicleType) {
        return vehicleInventoryManagement.getVehicles(vehicleType);
    }

    public Reservation createReservation(Vehicle vehicle, User user, Date pickupDate, Date dropDate) {
        Reservation reservation = Reservation.builder().reservationId(1234).reservationStatus(ReservationStatus.SCHEDULED)
                .vehicle(vehicle).user(user).pickupDate(pickupDate).dropDate(dropDate).build();
        reservationList.add(reservation);
        return reservation;
    }

    public void completeReservation(Reservation reservation) {
        reservationList.get(reservationList.indexOf(reservation)).setReservationStatus(ReservationStatus.COMPLETED);
    }
}
