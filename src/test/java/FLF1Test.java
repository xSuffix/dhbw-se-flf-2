import cabin.controls.FrontLauncherOutput;
import cabin.controls.RoofLauncherOutput;
import id_card.IDCard;
import id_card.IDCardEncoder;
import id_card.RFIDChip;
import org.junit.jupiter.api.*;
import truck.water.LauncherState;
import truck.water.MixingRatio;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FLF1Test extends FLFTest {

    @BeforeEach
    public void setup() {
        initialize(false);
    }

    @Test
    @Order(1)
    public void buildComplete() {
        //Cabin
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
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getBlueLightSwitch());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getWarningLightSwitch());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getFrontHeadlightSwitch());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getRoofHeadLightSwitch());
        assertNotNull(airportFireTruck.getCabin().getControlPanel().getMotorSwitch());

        assertNotNull(airportFireTruck.getCabin().getLeftDoor());
        assertNotNull(airportFireTruck.getCabin().getRightDoor());
        assertNotNull(airportFireTruck.getCabin().getRoofLauncherKnob());
        assertNotNull(airportFireTruck.getCabin().getFrontLauncherKnob());
        assertNotNull(airportFireTruck.getCabin().getLeftJoystick());
        assertNotNull(airportFireTruck.getCabin().getRightJoystick());
        assertNotNull(airportFireTruck.getCabin().getBatteryDisplay());
        assertNotNull(airportFireTruck.getCabin().getSpeedDisplay());

        //Drive
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
        assertNotNull(airportFireTruck.getTurnSignalLightsLeft());
        assertNotNull(airportFireTruck.getTurnSignalLightsRight());
        assertNotNull(airportFireTruck.getBrakeLightLeft());
        assertNotNull(airportFireTruck.getBrakeLightRight());

        assertNotNull(airportFireTruck.getCentralUnit());
        assertNotNull(airportFireTruck.getFloorSprayingNozzles());
        assertNotNull(airportFireTruck.getMixingUnit());
        assertNotNull(airportFireTruck.getFoamPowderTank());
        assertNotNull(airportFireTruck.getFrontLauncher());
        assertNotNull(airportFireTruck.getRoofLauncher());
        assertNotNull(airportFireTruck.getWaterTank());

        assertNotNull(airportFireTruck.getMixingUnit());
        assertNotNull(airportFireTruck.getDrive());
    }

    @Test
    @Order(2)
    public void usageControlPanel() {
        driver.toggleBlueLights();
        checkLights(airportFireTruck.getBlueLights(), true);
        driver.toggleBlueLights();
        checkLights(airportFireTruck.getBlueLights(), false);

        driver.toggleWarningLights();
        checkLights(airportFireTruck.getWarningLights(), true);
        driver.toggleWarningLights();
        checkLights(airportFireTruck.getWarningLights(), false);

        driver.toggleFrontHeadLights();
        checkLights(airportFireTruck.getHeadLightsFrontLeft(), true);
        checkLights(airportFireTruck.getHeadLightsFrontRight(), true);
        driver.toggleFrontHeadLights();
        checkLights(airportFireTruck.getHeadLightsFrontRight(), false);
        checkLights(airportFireTruck.getHeadLightsFrontLeft(), false);

        driver.toggleRoofHeadLights();
        checkLights(airportFireTruck.getHeadLightsRoof(), true);
        driver.toggleRoofHeadLights();
        checkLights(airportFireTruck.getHeadLightsRoof(), false);

        driver.pressMotorSwitch();
        checkMotors(true);
        driver.pressMotorSwitch();
        checkMotors(false);
    }

    @Test
    @Order(3)
    public void handleParking() {
        checkMotors(false);
        assertTrue(seatsUnoccupied());
        checkIfDoorsOpen(true);
        assertFalse(airportFireTruck.getRoofLauncher().isSecondSegmentExtended());
        assertNotSame(airportFireTruck.getFrontLauncher().getState(), LauncherState.ACTIVE);
        checkLights(airportFireTruck.getHeadLightsRoof(), false);
        checkLights(airportFireTruck.getHeadLightsFrontLeft(), false);
        checkLights(airportFireTruck.getHeadLightsFrontRight(), false);
        checkLights(airportFireTruck.getBlueLights(), false);
        checkLights(airportFireTruck.getWarningLights(), false);
        checkLights(airportFireTruck.getSideLightsLeft(), false);
        checkLights(airportFireTruck.getSideLightsRight(), false);
        assertEquals(100, airportFireTruck.getDrive().getBatteryPercentage());
        assertEquals(100, airportFireTruck.getWaterTank().getCurrentFillPercentage());
        assertEquals(100, airportFireTruck.getFoamPowderTank().getCurrentFillPercentage());
        assertEquals(FrontLauncherOutput.A, airportFireTruck.getCabin().getFrontLauncherKnob().getState());
        assertEquals(RoofLauncherOutput.A, airportFireTruck.getCabin().getRoofLauncherKnob().getState());
    }

    @Test
    @Order(4)
    public void handleInspectionDrive() {
        preDeployment();

        driver.toggleWarningLights();
        driver.toggleBlueLights();
        checkLights(airportFireTruck.getHeadLightsRoof(), false);
        checkLights(airportFireTruck.getHeadLightsFrontLeft(), false);
        checkLights(airportFireTruck.getHeadLightsFrontRight(), false);
        checkLights(airportFireTruck.getBlueLights(), true);
        checkLights(airportFireTruck.getWarningLights(), true);
        checkLights(airportFireTruck.getSideLightsLeft(), false);
        checkLights(airportFireTruck.getSideLightsRight(), false);

        checkDriving(7, 5, 0, 0);
        driver.rotateSteeringWheel(-5);
        checkDriving(0, 3, 0, -5);
        driver.rotateSteeringWheel(5);
        checkDriving(0, 5, 0, 0);
        driver.rotateSteeringWheel(5);
        checkDriving(0, 3, 0, 5);
        driver.rotateSteeringWheel(-5);
        checkDriving(0, 0, 7, 0);
        assertEquals(0, airportFireTruck.getDrive().getCurrentVelocity());
    }

    @Test
    @Order(5)
    public void handleEmergencyService() {
        preDeployment();

        driver.toggleWarningLights();
        driver.toggleBlueLights();
        driver.toggleRoofHeadLights();
        driver.toggleFrontHeadLights();
        checkLights(airportFireTruck.getHeadLightsRoof(), true);
        checkLights(airportFireTruck.getHeadLightsFrontLeft(), true);
        checkLights(airportFireTruck.getHeadLightsFrontRight(), true);
        checkLights(airportFireTruck.getBlueLights(), true);
        checkLights(airportFireTruck.getWarningLights(), true);
        checkLights(airportFireTruck.getSideLightsLeft(), false);
        checkLights(airportFireTruck.getSideLightsRight(), false);

        checkDriving(20, 10, 0, 0);
    }

    @Test
    @Order(6)
    public void handleFuelTruckOnFire() {
        preDeployment();
        turnAllLightsOn();
        int waterLevel = airportFireTruck.getWaterTank().getCurrentCapacity();
        driver.toggleProtection();
        assertEquals(waterLevel - 700, airportFireTruck.getWaterTank().getCurrentCapacity());
        armFrontLauncher();
        testFrontLauncher(3, 3000, MixingRatio.C);
        armRoofLauncher();
        testRoofLauncher(3, 2500, MixingRatio.B);
    }

    @Test
    @Order(7)
    public void handlePushbackVehicleOnFire() {
        preDeployment();
        turnAllLightsOn();
        armRoofLauncher();
        testRoofLauncher(5, 2500, MixingRatio.C);
        armFrontLauncher();
        testFrontLauncher(3, 1000, MixingRatio.B);
    }

    @Test
    @Order(8)
    public void handleAirplaneEngineFire() {
        preDeployment();
        turnAllLightsOn();
        armRoofLauncher();
        testRoofLauncher(5, 2500, MixingRatio.D);

        //reset ratio to initial value!
        operator.pressJoyStickFrontRightButton();

        testRoofLauncher(5, 2500, MixingRatio.D);
        armFrontLauncher();
        testFrontLauncher(5, 1000, MixingRatio.B);
    }

    @Test
    @Order(9)
    public void keyCardParking() {
        IDCard idCard = new IDCard();
        new IDCardEncoder().encode(airportFireTruck.getCentralUnit(), idCard, "Red Adair");
        driver.takeSeat();
        operator.takeSeat();
        driver.pressInnerDoorButton();
        operator.pressInnerDoorButton();
        assertFalse(seatsUnoccupied());
        checkIfDoorsOpen(false);

        // Start scenario
        operator.pressInnerDoorButton();
        operator.leaveSeat();
        driver.pressInnerDoorButton();
        driver.leaveSeat();
        checkIfDoorsOpen(true);
        assertTrue(seatsUnoccupied());

        driver.useIDCard(idCard);
        assertFalse(airportFireTruck.getCabin().getLeftDoor().isOpen());
        assertFalse(airportFireTruck.getCabin().getRightDoor().isOpen());
        assertTrue(airportFireTruck.getCabin().getLeftDoor().isLocked());
        assertTrue(airportFireTruck.getCabin().getRightDoor().isLocked());

        airportFireTruck.getCabin().getRightDoor().getOuterButton().press();
        assertFalse(airportFireTruck.getCabin().getLeftDoor().isOpen());
    }

    @Test
    @Order(10)
    public void keyCardInspectionAndEmergency() {
        IDCard idCard = new IDCard();
        new IDCardEncoder().encode(airportFireTruck.getCentralUnit(), idCard, "Red Adair");
        airportFireTruck.getCabin().getLeftDoor().toggleLock();
        airportFireTruck.getCabin().getRightDoor().toggleLock();
        checkIfDoorsOpen(false);
        assertTrue(airportFireTruck.getCabin().getLeftDoor().isLocked());
        assertTrue(airportFireTruck.getCabin().getRightDoor().isLocked());

        // Start scenario
        driver.useIDCard(idCard);
        checkIfDoorsOpen(true);
        operator.takeSeat();
        operator.pressInnerDoorButton();
        driver.takeSeat();
        driver.pressInnerDoorButton();
        assertFalse(seatsUnoccupied());
        checkIfDoorsOpen(false);
    }

}
