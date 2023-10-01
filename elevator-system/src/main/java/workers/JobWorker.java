package workers;

import model.Elevator;
import request.Request;

public class JobWorker implements Runnable {

    Elevator elevator;
    Request request;

    public JobWorker(Elevator elevator, Request request) {
        this.elevator = elevator;
        this.request = request;
    }

    @Override
    public void run() {
        elevator.addJob(request);
    }

}
