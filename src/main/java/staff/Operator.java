package staff;

import id_card.IDCard;
import truck.IAirportFireTruck;

public class Operator {

    private final IAirportFireTruck airportFireTruck;

    public Operator(IAirportFireTruck aft) {
        airportFireTruck = aft;
    }

    public void takeSeat() {
        airportFireTruck.getCabin().getSeat(1).sitDown();
    }

    public void leaveSeat() {
        airportFireTruck.getCabin().getSeat(1).standUp();
    }

    public void pressInnerDoorButton() {
        airportFireTruck.getCabin().getRightDoor().getInnerButton().press();
    }

    public void pressJoyStickFrontLeftButton() {
        airportFireTruck.getCabin().getRightJoystick().getFrontLeftButton().press();
    }

    public void pressJoyStickFrontRightButton() {
        airportFireTruck.getCabin().getRightJoystick().getFrontRightButton().press();
    }

    public void pressJoyStickFrontBackSwitch() {
        airportFireTruck.getCabin().getRightJoystick().getBackSwitch().press();
    }

    public void turnLauncherKnobRight() {
        airportFireTruck.getCabin().getRoofLauncherKnob().turnRight();
    }

    public void turnLauncherKnobLeft() {
        airportFireTruck.getCabin().getRoofLauncherKnob().turnLeft();
    }

    public void useIDCard(IDCard idCard) {
        airportFireTruck.getCabin().getRightDoor().getIdCardReceiver().read(idCard);
    }
}
