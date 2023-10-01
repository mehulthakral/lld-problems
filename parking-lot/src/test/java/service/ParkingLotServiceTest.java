package service;

import exceptions.ParkingLotException;
import model.Car;
import model.ParkingLot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import strategy.NaturalOrderingParkingStrategy;
import strategy.ParkingStrategy;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ParkingLotServiceTest {

    ParkingLotService parkingLotService;

    @Before
    public void setUp() {
        parkingLotService = new ParkingLotService();
    }

    @Test
    public void createParkingLot() {
        ParkingLot parkingLot = new ParkingLot(5);
        ParkingStrategy parkingStrategy = mock(NaturalOrderingParkingStrategy.class);

        parkingLotService.createParkingLot(parkingLot, parkingStrategy);
        Collection<Invocation> invocations = Mockito.mockingDetails(parkingStrategy).getInvocations();
        assertEquals(5, invocations.size());
    }

    @Test(expected = ParkingLotException.class)
    public void createParkingLotDuplicate() {
        ParkingLot parkingLot = new ParkingLot(5);
        ParkingStrategy parkingStrategy = new NaturalOrderingParkingStrategy();

        parkingLotService.createParkingLot(parkingLot, parkingStrategy);
        parkingLotService.createParkingLot(parkingLot, parkingStrategy);
    }

    @Test(expected = ParkingLotException.class)
    public void parkWithoutParkingLot() {
        parkingLotService.park(new Car("KA-01-JR-0779", "White"));
    }
}
