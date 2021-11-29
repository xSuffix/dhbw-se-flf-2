import drive.ElectricMotor;
import enums.FrontLauncherOutput;
import lights.BlueLight;
import lights.HeadLight;
import lights.Light;
import lights.WarningLight;
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
    public void buildComplete(){
        //Cabin Stuff
        assertNotNull(airportFireTruck.getCabin());
        assertNotNull(airportFireTruck.getCabin().getSteeringWheel());
        assertNotNull(airportFireTruck.getCabin().getSeat(0));
        assertNotNull(airportFireTruck.getCabin().getSeat(1));
        assertNotNull(airportFireTruck.getCabin().getSeat(2));
        assertNotNull(airportFireTruck.getCabin().getSeat(3));
        assertNotNull(airportFireTruck.getCabin().getBreakPedal());
        assertNotNull(airportFireTruck.getCabin().getGasPedal());
        //Control Panel
        assertNotNull(airportFireTruck.getCabin().getControlPanel());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getRoofLauncherKnob());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getFrontLauncherKnob());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getBlueLightSwitch());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getWarningLightSwitch());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getFrontHeadlightSwitch());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getRoofHeadLightSwitch());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getMotorSwitch());

        assertNotNull(airportFireTruck.getCabin().getLeftJoyStick());
        assertNotNull(airportFireTruck.getCabin().getRightJoyStick());
        assertNotNull(airportFireTruck.getCabin().getSpeedDisplay());
        assertNotNull(airportFireTruck.getCabin().getBatteryDisplay());
        assertNotNull(airportFireTruck.getCabin().getLeftDoor());
        assertNotNull(airportFireTruck.getCabin().getRightDoor());


        //Drive Stuff
        assertNotNull(airportFireTruck.getDrive());
        assertNotNull(airportFireTruck.getDrive().getElectricMotors());
        assertNotNull(airportFireTruck.getDrive().getBatteryManagement());
        assertNotNull(airportFireTruck.getDrive().getBatteryBox());
        assertNotNull(airportFireTruck.getDrive().getBackAxles());
        assertNotNull(airportFireTruck.getDrive().getFrontAxles());

        //Lights
        assertNotNull(airportFireTruck.getHeadLightsFrontLeft());
        assertNotNull(airportFireTruck.getHeadLightsFrontRight());
        assertNotNull(airportFireTruck.getHeadLightsRoof());
        assertNotNull(airportFireTruck.getWarningLights());
        assertNotNull(airportFireTruck.getBlueLights());
        assertNotNull(airportFireTruck.getTurnSignalLightLeft());
        assertNotNull(airportFireTruck.getTurnSignalLightRight());
        assertNotNull(airportFireTruck.getBrakeLightLeft());
        assertNotNull(airportFireTruck.getBrakeLightRight());

        assertNotNull(airportFireTruck.getCentralUnit());
        assertNotNull(airportFireTruck.getFloorSprayingNoozles());
        assertNotNull(airportFireTruck.getMixingUnit());
        assertNotNull(airportFireTruck.getFoampowderTank());
        assertNotNull(airportFireTruck.getFrontLauncher());
        assertNotNull(airportFireTruck.getRoofLauncher());
        assertNotNull(airportFireTruck.getWaterTank());

        assertNotNull(airportFireTruck.getMixingUnit());
        assertNotNull(airportFireTruck.getDrive());
    }

    @Test
    @Order(2)
    public void usageControlPanel(){
        airportFireTruck.getCabin().getControlPanel().getBlueLightSwitch().press();
        checkLights(airportFireTruck.getBlueLights(),true);
        airportFireTruck.getCabin().getControlPanel().getBlueLightSwitch().press();
        checkLights(airportFireTruck.getBlueLights(),false);

        airportFireTruck.getCabin().getControlPanel().getWarningLightSwitch().press();
        checkLights(airportFireTruck.getWarningLights(),true);
        airportFireTruck.getCabin().getControlPanel().getWarningLightSwitch().press();
        checkLights(airportFireTruck.getWarningLights(),false);

        airportFireTruck.getCabin().getControlPanel().getFrontHeadlightSwitch().press();
        checkLights(airportFireTruck.getHeadLightsFrontLeft(),true);
        checkLights(airportFireTruck.getHeadLightsFrontRight(),true);
        airportFireTruck.getCabin().getControlPanel().getFrontHeadlightSwitch().press();
        checkLights(airportFireTruck.getHeadLightsFrontRight(),false);
        checkLights(airportFireTruck.getHeadLightsFrontLeft(),false);


        airportFireTruck.getCabin().getControlPanel().getRoofHeadLightSwitch().press();
        checkLights(airportFireTruck.getHeadLightsRoof(),true);
        airportFireTruck.getCabin().getControlPanel().getRoofHeadLightSwitch().press();
        checkLights(airportFireTruck.getHeadLightsRoof(),false);

        airportFireTruck.getCabin().getControlPanel().getMotorSwitch().press();
        assertTrue(motorsOn());
        airportFireTruck.getCabin().getControlPanel().getMotorSwitch().press();
        assertFalse(motorsOn());

    }

    @Test
    @Order(3)
    public void handleParking(){

    }

    @Test
    @Order(4)
    public void handleInspectionDrive(){

    }

    @Test
    @Order(5)
    public void handleFuelTruckOnFire(){

    }

    @Test
    @Order(6)
    public void handlePushbackVehicleOnFire(){

    }

    @Test
    @Order(7)
    public void handleAirplaneEngineFire(){

    }

    public void checkLights(BlueLight[] lights, boolean state){
        for (Light light : lights){
            assertEquals(state,light.isOn());
        }
    }
    public void checkLights(WarningLight[] lights,boolean state){
        for (Light light : lights){
            assertEquals(state,light.isOn());
        }
    }
    public void checkLights(HeadLight[] lights, boolean state){
        for (Light light : lights){
            assertEquals(state,light.isOn());
        }
    }
    public boolean motorsOn(){
        for(ElectricMotor motor : airportFireTruck.getDrive().getElectricMotors()){
            if(!motor.isStarted()) return false;
        }
        return true;
    }

}