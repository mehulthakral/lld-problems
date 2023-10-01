package model;

import request.Request;
import java.util.TreeSet;

public class Elevator {

    TreeSet<Request> currentJobs = new TreeSet<>();
    TreeSet<Request> upPendingJobs = new TreeSet<>();
    TreeSet<Request> downPendingJobs = new TreeSet<>();
    int currentFloor = 0;
    State state = State.IDLE;
    Direction direction = Direction.UP;

    public void startElevator() {
        while (true) {
            if (!currentJobs.isEmpty()) {
                if (direction == Direction.UP) {
                    Request request = currentJobs.pollFirst();
                    System.out.println("Received up request - " + request.toString());
                    processUpRequest(request);
                    if (currentJobs.isEmpty()) {
                        processDownPendingJobs();
                    }
                } else {
                    Request request = currentJobs.pollLast();
                    System.out.println("Received down request - " + request.toString());
                    processDownRequest(request);
                    if (currentJobs.isEmpty()) {
                        processUpPendingJobs();
                    }
                }
            } else {
                System.out.println("No requests to process");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addJob(Request request) {
        if (state == State.IDLE) {
            currentJobs.add(request);
            state = State.MOVING;
            direction = request.getExternalRequest().getRequestDirection();
        } else if (requestEligibleToPickedUp(request)) {
            currentJobs.add(request);
        } else if (request.getExternalRequest().getRequestDirection() == Direction.UP) {
            upPendingJobs.add(request);
        } else {
            downPendingJobs.add(request);
        }
    }

    private boolean requestEligibleToPickedUp(Request request) {
        return direction == request.getExternalRequest().getRequestDirection()
                && (
                (direction == Direction.UP && currentFloor <= request.getExternalRequest().getSourceFloor())
                ||
                (direction == Direction.DOWN && currentFloor >= request.getExternalRequest().getSourceFloor())
        );
    }

    private void processUpRequest(Request request) {
//        if (currentFloor < request.getExternalRequest().getSourceFloor()) {
//            while(currentFloor < request.getExternalRequest().getSourceFloor()) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println("Got exception in sleep");
//                }
//                currentFloor++;
//                System.out.println("Reached floor - " + currentFloor);
//            }
//        }
        reachSourceFloor(request);

        currentFloor = request.getExternalRequest().getSourceFloor();
        System.out.println("Doors opened at floor - " + currentFloor);
        while(currentFloor < request.getInternalRequest().getDestinationFloor()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Got exception in sleep");
            }
            currentFloor++;
            System.out.println("Reached floor - " + currentFloor);

            if (checkJobAtCurrentFloor(request)) {
                break;
            }
        }
        System.out.println("Doors opened at floor - " + currentFloor);
    }

    private void processDownRequest(Request request) {
//        if (currentFloor < request.getExternalRequest().getSourceFloor()) {
//            while(currentFloor < request.getExternalRequest().getSourceFloor()) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println("Got exception in sleep");
//                }
//                currentFloor++;
//                System.out.println("Reached floor - " + currentFloor);
//            }
//        }
        reachSourceFloor(request);

        currentFloor = request.getExternalRequest().getSourceFloor();
        System.out.println("Doors opened at floor - " + currentFloor);
        while(currentFloor > request.getInternalRequest().getDestinationFloor()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Got exception in sleep");
            }
            currentFloor--;
            System.out.println("Reached floor - " + currentFloor);

            if (checkJobAtCurrentFloor(request)) {
                break;
            }
        }
        System.out.println("Doors opened at floor - " + currentFloor);
    }

    private void reachSourceFloor(Request request) {
        while(currentFloor < request.getExternalRequest().getSourceFloor()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Got exception in sleep");
            }
            currentFloor++;
            System.out.println("Reached floor - " + currentFloor);
        }
        while(currentFloor > request.getExternalRequest().getSourceFloor()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Got exception in sleep");
            }
            currentFloor--;
            System.out.println("Reached floor - " + currentFloor);
        }
    }

    private void processDownPendingJobs() {
        if (!downPendingJobs.isEmpty()) {
            direction = Direction.DOWN;
            currentJobs = downPendingJobs;
            downPendingJobs = new TreeSet<>();
        } else {
            state = State.IDLE;
        }
    }

    private void processUpPendingJobs() {
        if (!upPendingJobs.isEmpty()) {
            direction = Direction.UP;
            currentJobs = upPendingJobs;
            upPendingJobs = new TreeSet<>();
        } else {
            state = State.IDLE;
        }
    }

    private boolean checkJobAtCurrentFloor(Request request) {
        if (!currentJobs.isEmpty()) {
            Request latestRequest;
            if (direction == Direction.UP) {
                latestRequest = currentJobs.pollFirst();
                if (latestRequest.getInternalRequest().getDestinationFloor()
                        < request.getInternalRequest().getDestinationFloor()) {
                    currentJobs.add(latestRequest);
                    currentJobs.add(request);
                    return true;
                }
            } else {
                latestRequest = currentJobs.pollLast();
                if (latestRequest.getInternalRequest().getDestinationFloor()
                        > request.getInternalRequest().getDestinationFloor()) {
                    currentJobs.add(latestRequest);
                    currentJobs.add(request);
                    return true;
                }
            }
            currentJobs.add(latestRequest);
        }
        return false;
    }
}
