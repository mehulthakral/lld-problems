import model.Direction;
import model.Elevator;
import request.ExternalRequest;
import request.InternalRequest;
import request.Request;
import workers.ElevatorWorker;
import workers.JobWorker;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Elevator elevator = new Elevator();

        new Thread(new ElevatorWorker(elevator)).start();

        Thread.sleep(5000);

        InternalRequest internalRequest = new InternalRequest(4);
        ExternalRequest externalRequest = new ExternalRequest(Direction.UP, 1);
        Request request = new Request(internalRequest, externalRequest);
        new Thread(new JobWorker(elevator, request)).start();

        Thread.sleep(1000);

        internalRequest = new InternalRequest(5);
        externalRequest = new ExternalRequest(Direction.UP, 3);
        request = new Request(internalRequest, externalRequest);
        new Thread(new JobWorker(elevator, request)).start();
    }

}
