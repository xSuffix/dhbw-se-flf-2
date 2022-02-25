package staff;

import id_card.IDCard;
import truck.IAirportFireTruck;

public class Driver {
    private final IAirportFireTruck airportFireTruck;

    public Driver(IAirportFireTruck aft) {
        airportFireTruck = aft;
    }

    public void takeSeat() {
        airportFireTruck.getCabin().getSeat(0).sitDown();
    }

    public void leaveSeat() {
        airportFireTruck.getCabin().getSeat(0).standUp();
    }

    public void pressInnerDoorButton() {
        airportFireTruck.getCabin().getLeftDoor().getInnerButton().press();
    }

    public void toggleBlueLights() {
        airportFireTruck.getCabin().getControlPanel().getBlueLightSwitch().press();
    }

    public void toggleWarningLights() {
        airportFireTruck.getCabin().getControlPanel().getWarningLightSwitch().press();
    }

    public void toggleFrontHeadLights() {
        airportFireTruck.getCabin().getControlPanel().getFrontHeadlightSwitch().press();
    }

    public void toggleRoofHeadLights() {
        airportFireTruck.getCabin().getControlPanel().getRoofHeadLightSwitch().press();
    }

    public void toggleSideLights() {
        airportFireTruck.getCabin().getControlPanel().getSideLightsSwitch().press();
    }

    public void pressMotorSwitch() {
        airportFireTruck.getCabin().getControlPanel().getMotorSwitch().press();
    }

    public void pressGasPedal() {
        airportFireTruck.getCabin().getGasPedal().press();
    }

    public void cruise() {
        airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity());
    }

    public void pressBreakPedal() {
        airportFireTruck.getCabin().getBreakPedal().press();
    }

    public void rotateSteeringWheel(int rotation) {
        airportFireTruck.getCabin().getSteeringWheel().rotate(rotation);
    }

    public void toggleProtection() {
        airportFireTruck.getCabin().getControlPanel().getSelfProtectionButton().press();
    }

    public void pressJoyStickFrontLeftButton() {
        airportFireTruck.getCabin().getLeftJoystick().getFrontLeftButton().press();
    }

    public void pressJoyStickFrontRightButton() {
        airportFireTruck.getCabin().getLeftJoystick().getFrontRightButton().press();
    }

    public void pressJoyStickFrontBackSwitch() {
        airportFireTruck.getCabin().getLeftJoystick().getBackSwitch().press();
    }

    public void turnLauncherKnobRight() {
        airportFireTruck.getCabin().getFrontLauncherKnob().turnRight();
    }

    public void turnLauncherKnobLeft() {
        airportFireTruck.getCabin().getFrontLauncherKnob().turnLeft();
    }

    public void useIDCard(IDCard idCard) {
        airportFireTruck.getCabin().getLeftDoor().getIdCardReceiver().read(idCard);
    }
}
