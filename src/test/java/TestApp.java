import drive.ElectricMotor;
import enums.ExtinguishingType;
import enums.FrontLauncherOutput;
import enums.LauncherState;
import enums.RoofLauncherOutput;
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
        airportFireTruck.chargeTruck(airportFireTruck.getDrive().getBatteryBox().getMaxCharge());
        airportFireTruck.getWaterTank().fill(airportFireTruck.getWaterTank().getTotalCapacity(), ExtinguishingType.WATER);
        airportFireTruck.getFoampowderTank().fill(airportFireTruck.getFoampowderTank().getTotalCapacity(), ExtinguishingType.FOAMPOWDER);
        //open doors in parking position!
        airportFireTruck.getCabin().getRightDoor().getOuterButton().press();
        airportFireTruck.getCabin().getLeftDoor().getOuterButton().press();
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
        checkMotors(true);
        airportFireTruck.getCabin().getControlPanel().getMotorSwitch().press();
        checkMotors(false);


    }

    @Test
    @Order(3)
    public void handleParking(){
        assertTrue(seatsUnoccupied());
        checkMotors(false);
        checkIfDoorsOpen(true);
        assertFalse(airportFireTruck.getRoofLauncher().isSecondSemgentExtended());
        assertNotSame(airportFireTruck.getFrontLauncher().getState(), LauncherState.ACTIVE);
        checkLights(airportFireTruck.getHeadLightsRoof(),false);
        checkLights(airportFireTruck.getHeadLightsFrontLeft(),false);
        checkLights(airportFireTruck.getHeadLightsFrontRight(),false);
        checkLights(airportFireTruck.getBlueLights(),false);
        checkLights(airportFireTruck.getWarningLights(),false);
        assertEquals(100,airportFireTruck.getDrive().getBatteryPercentage());
        assertEquals(100,airportFireTruck.getWaterTank().getCurrentFillPercentage());
        assertEquals(100,airportFireTruck.getFoampowderTank().getCurrentFillPercentage());
        assertEquals(FrontLauncherOutput.A.getValue(), airportFireTruck.getCabin().getControlPanel().getFrontLauncherKnob().getValue());
        assertEquals(RoofLauncherOutput.A.getValue(), airportFireTruck.getCabin().getControlPanel().getRoofLauncherKnob().getValue());
    }

    @Test
    @Order(4)
    public void handleInspectionDrive(){
        airportFireTruck.getCabin().getSeat(0).sitDown();
        airportFireTruck.getCabin().getSeat(1).sitDown();
        airportFireTruck.getCabin().getControlPanel().getMotorSwitch().press();
        checkMotors(true);
        assertTrue(airportFireTruck.getCabin().getSeat(0).isOccupied());
        assertTrue(airportFireTruck.getCabin().getSeat(1).isOccupied());
        airportFireTruck.getCabin().getLeftDoor().getInnerButton().press();
        airportFireTruck.getCabin().getRightDoor().getInnerButton().press();
        checkIfDoorsOpen(false);
        airportFireTruck.getCabin().getControlPanel().getWarningLightSwitch().press();
        airportFireTruck.getCabin().getControlPanel().getBlueLightSwitch().press();
        checkLights(airportFireTruck.getHeadLightsRoof(),false);
        checkLights(airportFireTruck.getHeadLightsFrontLeft(),false);
        checkLights(airportFireTruck.getHeadLightsFrontRight(),false);
        checkLights(airportFireTruck.getBlueLights(),true);
        checkLights(airportFireTruck.getWarningLights(),true);
        assertEquals(100,airportFireTruck.getWaterTank().getCurrentFillPercentage());
        assertEquals(100,airportFireTruck.getFoampowderTank().getCurrentFillPercentage());
        assertEquals(FrontLauncherOutput.A.getValue(), airportFireTruck.getCabin().getControlPanel().getFrontLauncherKnob().getValue());
        assertEquals(RoofLauncherOutput.A.getValue(), airportFireTruck.getCabin().getControlPanel().getRoofLauncherKnob().getValue());

        checkDriving(7,5,0,0);
        airportFireTruck.getCabin().getSteeringWheel().rotate(-5);
        checkDriving(0,3,0,-5);
        airportFireTruck.getCabin().getSteeringWheel().rotate(5);
        checkDriving(0,5,0,0);
        airportFireTruck.getCabin().getSteeringWheel().rotate(5);
        checkDriving(0,3,0,5);
        airportFireTruck.getCabin().getSteeringWheel().rotate(-5);
        checkDriving(0,0,7,0);
        assertEquals(0,airportFireTruck.getDrive().getCurrentVelocity());

    }

    @Test
    @Order(5)
    public void handleEmergencyService(){

    }

    @Test
    @Order(6)
    public void handleFuelTruckOnFire(){

    }

    @Test
    @Order(7)
    public void handlePushbackVehicleOnFire(){

    }

    @Test
    @Order(8)
    public void handleAirplaneEngineFire(){

    }

    public void checkDriving(int interationsAccelerating,int interationsCruising, int interationsBreaking,int direction){
        int startingEnergy = airportFireTruck.getDrive().getBatteryCharge();
        int expectedEnergyUsage = 0;
        for(int i = 0;i<interationsAccelerating;i++) {
            airportFireTruck.getCabin().getGasPedal().pressPedal();
            expectedEnergyUsage += airportFireTruck.getDrive().getCurrentVelocity()*4;
        }
        for(int i = 0;i<interationsCruising;i++) {
            airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity());
            expectedEnergyUsage += airportFireTruck.getDrive().getCurrentVelocity()*4;
        }
        for(int i = 0;i<interationsBreaking;i++) {
            airportFireTruck.getCabin().getBreakPedal().pressPedal();
            expectedEnergyUsage += airportFireTruck.getDrive().getCurrentVelocity()*4;
        }
        assertEquals(startingEnergy - expectedEnergyUsage,airportFireTruck.getDrive().getBatteryCharge());
        assertEquals(direction,airportFireTruck.getDrive().getAxleRotation());
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
    public void checkMotors(boolean state){
        for(ElectricMotor motor : airportFireTruck.getDrive().getElectricMotors()){
            assertEquals(state, motor.isStarted());
        }
    }

    public boolean seatsUnoccupied(){
        for (int i = 0; i<4;i++){
            if (airportFireTruck.getCabin().getSeat(i).isOccupied()) return false;
        }
        return true;
    }

    public void checkIfDoorsOpen(boolean state){
        assertEquals(state, airportFireTruck.getCabin().getLeftDoor().isOpen());
        assertEquals(state, airportFireTruck.getCabin().getRightDoor().isOpen());
    }


}