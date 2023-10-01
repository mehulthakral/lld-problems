package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.reservation.Reservation;

@Getter
@Setter
@Builder
public class Bill {

    Reservation reservation;
    boolean isPaid;
    long amount;

}
