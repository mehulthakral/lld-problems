package request;

import java.util.Objects;

public class Request implements Comparable<Request> {

    InternalRequest internalRequest;
    ExternalRequest externalRequest;

    public Request(InternalRequest internalRequest, ExternalRequest externalRequest) {
        this.internalRequest = internalRequest;
        this.externalRequest = externalRequest;
    }

    public InternalRequest getInternalRequest() {
        return internalRequest;
    }

    public ExternalRequest getExternalRequest() {
        return externalRequest;
    }

    @Override
    public int compareTo(Request r) {
        if (this.getInternalRequest().getDestinationFloor() == r.getInternalRequest().getDestinationFloor()) {
            return 0;
        } else if (this.getInternalRequest().getDestinationFloor() > r.getInternalRequest().getDestinationFloor()) {
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(internalRequest, request.internalRequest) && Objects.equals(externalRequest, request.externalRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalRequest, externalRequest);
    }
}
