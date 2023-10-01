package request;


import model.Direction;

public class ExternalRequest {

    Direction requestDirection;
    int sourceFloor;

    public ExternalRequest(Direction requestDirection, int sourceFloor) {
        this.requestDirection = requestDirection;
        this.sourceFloor = sourceFloor;
    }

    public Direction getRequestDirection() {
        return requestDirection;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }
}
