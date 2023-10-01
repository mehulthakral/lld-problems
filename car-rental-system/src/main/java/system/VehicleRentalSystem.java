package system;

import model.*;
import model.reservation.Reservation;
import model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleRentalSystem {

    List<Store> stores = new ArrayList<>();
    List<User> users = new ArrayList<>();

    public void addUsers(User user) {
        users.add(user);
    }

    public void addStores(Store store) {
        stores.add(store);
    }

    public Store getStore(int pincode) {
        return stores.stream().filter(s -> s.getLocation().getPincode() == pincode).collect(Collectors.toList())
                .get(0);
    }

    public User getUser(int userId) {
        return users.stream().filter(u -> u.getUserId() == userId).collect(Collectors.toList()).get(0);
    }

    public Reservation reserve(Store store, Vehicle vehicle, User user, Date pickupDate, Date dropDate) {
        return store.createReservation(vehicle, user, pickupDate, dropDate);
    }

    public Bill generateBill(Reservation reservation) {
        long days = reservation.getDropDate().getDate() - reservation.getPickupDate().getDate();
        long amount = reservation.getVehicle().getDailyRentalCost() * days;
        return Bill.builder().reservation(reservation).amount(amount).isPaid(Boolean.FALSE).build();
    }

    public Payment getPayment(Bill bill) {
        return Payment.builder().bill(bill).build();
    }

    public void completeReservation(Reservation reservation) {
        Store store = stores.stream().filter(s
                -> !s.getReservationList().stream().filter(r -> r.getReservationId() == reservation.getReservationId())
                        .collect(Collectors.toList()).isEmpty())
                .collect(Collectors.toList()).get(0);
        store.completeReservation(reservation);
        System.out.println("Reservation completed successfully. See you soon!");
    }
}
