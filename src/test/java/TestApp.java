import drive.ElectricMotor;
import enums.FrontLauncherOutput;
import org.junit.jupiter.api.*;
import truck.AirportFireTruck;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApp {
    private AirportFireTruck airportFireTruck;

    @BeforeEach
    public void setup() {
        airportFireTruck = new AirportFireTruck.Builder().build();
    }

    @Test
    @Order(1)
    public void test1(){
        airportFireTruck.getDrive().startMotors();
        for(ElectricMotor motor : airportFireTruck.getDrive().getElectricMotors()){
            assertTrue(motor.isStarted());
        }

        assertFalse(airportFireTruck.getCabin().getLeftDoor().isOpen());
        airportFireTruck.getCabin().getLeftDoor().getOuterButton().press();
        assertTrue(airportFireTruck.getCabin().getLeftDoor().isOpen());

    }
}