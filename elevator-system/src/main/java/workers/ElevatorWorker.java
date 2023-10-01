package workers;

import model.Elevator;

public class ElevatorWorker implements Runnable {

    Elevator elevator;

    public ElevatorWorker(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        System.out.println("Starting Elevator");
        elevator.startElevator();
    }
}
