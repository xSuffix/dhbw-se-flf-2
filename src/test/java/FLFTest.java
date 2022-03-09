import cabin.controls.FrontLauncherOutput;
import cabin.controls.RoofLauncherOutput;
import drive.ElectricMotor;
import lights.Light;
import staff.Driver;
import staff.Operator;
import truck.AirportFireTruck;
import truck.water.ExtinguishingType;
import truck.water.LauncherState;
import truck.water.MixingRatio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class FLFTest {
    protected AirportFireTruck airportFireTruck;
    protected Driver driver;
    protected Operator operator;

    public void initialize(boolean enableSmartJoySticks) {
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

    public void testRoofLauncher(int iterations, int amount, MixingRatio ratio) {
        int waterLevel = airportFireTruck.getWaterTank().getCurrentCapacity();
        int foamLevel = airportFireTruck.getFoamPowderTank().getCurrentCapacity();

        int i = 0;
        switch (ratio.getValue()) {
            case 3 -> i = 1;
            case 5 -> i = 2;
            case 10 -> i = 3;
        }
        for (int j = 0; j < i; j++)
            operator.pressJoyStickFrontRightButton();
        assertEquals(ratio.getValue(), airportFireTruck.getRoofLauncher().getRatio().getValue());

        switch (amount) {
            case 500 -> i = 0;
            case 1000 -> i = 1;
            case 2500 -> i = 2;
        }

        for (int j = 0; j < i; j++)
            operator.turnLauncherKnobRight();

        for (int j = 0; j < iterations; j++)
            operator.pressJoyStickFrontBackSwitch();

        for (int j = 0; j < i; j++)
            operator.turnLauncherKnobLeft();

        assertEquals(waterLevel - amount * iterations * (((double) 100 - (ratio.getValue())) / 100), airportFireTruck.getWaterTank().getCurrentCapacity());
        assertEquals(foamLevel - amount * iterations * (((double) ratio.getValue()) / 100), airportFireTruck.getFoamPowderTank().getCurrentCapacity());

    }

    public void testFrontLauncher(int iterations, int amount, MixingRatio ratio) {
        int waterLevel = airportFireTruck.getWaterTank().getCurrentCapacity();
        int foamLevel = airportFireTruck.getFoamPowderTank().getCurrentCapacity();

        int i = 0;
        switch (ratio.getValue()) {
            case 3 -> i = 1;
            case 5 -> i = 2;
            case 10 -> i = 3;
        }

        for (int j = 0; j < i; j++) driver.pressJoyStickFrontRightButton();

        assertEquals(ratio.getValue(), airportFireTruck.getFrontLauncher().getRatio().getValue());

        for (int j = 0; j < (amount / 500) - 1; j++) driver.turnLauncherKnobRight();
        for (int j = 0; j < iterations; j++) driver.pressJoyStickFrontBackSwitch();
        for (int j = 0; j < (amount / 500) - 1; j++) driver.turnLauncherKnobLeft();

        assertEquals(waterLevel - amount * iterations * (((double) 100 - (ratio.getValue())) / 100), airportFireTruck.getWaterTank().getCurrentCapacity());
        assertEquals(foamLevel - amount * iterations * (((double) ratio.getValue()) / 100), airportFireTruck.getFoamPowderTank().getCurrentCapacity());
    }

    public void armFrontLauncher() {
        driver.pressJoyStickFrontLeftButton();
        assertEquals(90, airportFireTruck.getFrontLauncher().getRotation());
        assertEquals(LauncherState.ACTIVE, airportFireTruck.getFrontLauncher().getState());
    }

    public void armRoofLauncher() {
        operator.pressJoyStickFrontLeftButton();
        assertEquals(90, airportFireTruck.getRoofLauncher().getFirstSegmentRotation());
        assertEquals(LauncherState.ACTIVE, airportFireTruck.getRoofLauncher().getState());
        assertTrue(airportFireTruck.getRoofLauncher().isSecondSegmentExtended());
    }

    public void turnAllLightsOn() {
        driver.toggleBlueLights();
        driver.toggleWarningLights();
        driver.toggleFrontHeadLights();
        driver.toggleRoofHeadLights();
        driver.toggleSideLights();

        checkLights(airportFireTruck.getHeadLightsRoof(), true);
        checkLights(airportFireTruck.getHeadLightsFrontLeft(), true);
        checkLights(airportFireTruck.getHeadLightsFrontRight(), true);
        checkLights(airportFireTruck.getBlueLights(), true);
        checkLights(airportFireTruck.getWarningLights(), true);
        checkLights(airportFireTruck.getSideLightsLeft(), true);
        checkLights(airportFireTruck.getSideLightsRight(), true);
    }

    public void preDeployment() {
        driver.takeSeat();
        operator.takeSeat();
        driver.pressMotorSwitch();
        checkMotors(true);
        assertTrue(airportFireTruck.getCabin().getSeat(0).isOccupied());
        assertTrue(airportFireTruck.getCabin().getSeat(1).isOccupied());
        driver.pressInnerDoorButton();
        operator.pressInnerDoorButton();
        checkIfDoorsOpen(false);
        assertEquals(100, airportFireTruck.getWaterTank().getCurrentFillPercentage());
        assertEquals(100, airportFireTruck.getFoamPowderTank().getCurrentFillPercentage());
        assertEquals(FrontLauncherOutput.A, airportFireTruck.getCabin().getFrontLauncherKnob().getState());
        assertEquals(RoofLauncherOutput.A, airportFireTruck.getCabin().getRoofLauncherKnob().getState());
    }

    public void checkDriving(int iterationsAccelerating, int iterationsCruising, int iterationsBreaking, int direction) {
        int startingEnergy = airportFireTruck.getDrive().getBatteryCharge();
        int expectedEnergyUsage = 0;
        for (int i = 0; i < iterationsAccelerating; i++) {
            driver.pressGasPedal();
            expectedEnergyUsage += airportFireTruck.getDrive().getCurrentVelocity() * 4;
        }
        for (int i = 0; i < iterationsCruising; i++) {
            driver.cruise();
            expectedEnergyUsage += airportFireTruck.getDrive().getCurrentVelocity() * 4;
        }
        for (int i = 0; i < iterationsBreaking; i++) {
            driver.pressBreakPedal();
            expectedEnergyUsage += airportFireTruck.getDrive().getCurrentVelocity() * 4;
        }
        assertEquals(startingEnergy - expectedEnergyUsage, airportFireTruck.getDrive().getBatteryCharge());
        assertEquals(direction, airportFireTruck.getDrive().getAxleRotation());
    }

    public void checkLights(Light[] lights, boolean state) {
        for (Light light : lights) {
            assertEquals(state, light.isOn());
        }
    }

    public void checkMotors(boolean state) {
        for (ElectricMotor motor : airportFireTruck.getDrive().getElectricMotors()) {
            assertEquals(state, motor.isStarted());
        }
    }

    public boolean seatsUnoccupied() {
        for (int i = 0; i < 4; i++) {
            if (airportFireTruck.getCabin().getSeat(i).isOccupied()) return false;
        }
        return true;
    }

    public void checkIfDoorsOpen(boolean state) {
        assertEquals(state, airportFireTruck.getCabin().getLeftDoor().isOpen());
        assertEquals(state, airportFireTruck.getCabin().getRightDoor().isOpen());
    }
}
