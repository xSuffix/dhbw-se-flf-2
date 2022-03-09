import org.junit.jupiter.api.*;
import staff.Driver;
import staff.Operator;
import truck.AirportFireTruck;
import truck.water.ExtinguishingType;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestFLF2 {

    private AirportFireTruck airportFireTruck;
    private Driver driver;
    private Operator operator;

    @BeforeEach
    public void setup() {
        boolean enableSmartJoySticks = false;
        airportFireTruck = new AirportFireTruck.Builder(enableSmartJoySticks).build();
        driver = new Driver(airportFireTruck);
        operator = new Operator(airportFireTruck);
        airportFireTruck.chargeTruck(airportFireTruck.getDrive().getBatteryBox().getMaxCharge());
        airportFireTruck.getWaterTank().fill(airportFireTruck.getWaterTank().getTotalCapacity(), ExtinguishingType.WATER);
        airportFireTruck.getFoamPowderTank().fill(airportFireTruck.getFoamPowderTank().getTotalCapacity(), ExtinguishingType.FOAM_POWDER);
        //open doors in parking position!
        airportFireTruck.getCabin().getRightDoor().getOuterButton().press();
        airportFireTruck.getCabin().getLeftDoor().getOuterButton().press();
    }

    @Test
    @Order(1)
    public void useMixingUnitComponent() {
        airportFireTruck.getFrontLauncher().sprayExtinguisher(3);
    }

    @Test
    @Order(5)
    public void useChargingAdapter() {

    }

    @Test
    @Order(6)
    public void useButtonStates() {

    }
}
