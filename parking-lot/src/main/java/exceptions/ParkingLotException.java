package exceptions;

public class ParkingLotException extends RuntimeException {

    public ParkingLotException() {

    }

    public ParkingLotException(String msg) {
        super(msg);
    }
}
