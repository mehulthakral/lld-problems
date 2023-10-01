package model.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.Location;
import model.User;
import model.vehicle.Vehicle;

import java.util.Date;

@Builder
@Getter
@Setter
public class Reservation {

    long reservationId;
    User user;
    Vehicle vehicle;
    ReservationStatus reservationStatus;
    Date bookingDate;
    Date pickupDate;
    Date dropDate;
    Location location;

}
