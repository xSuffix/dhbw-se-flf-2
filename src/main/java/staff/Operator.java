package staff;

import truck.IAirportFireTruck;

public class Operator {
    
    private IAirportFireTruck airportFireTruck;

    public Operator(IAirportFireTruck aft){
        airportFireTruck = aft;
    }

    public void takeSeat(){
        airportFireTruck.getCabin().getSeat(1).sitDown();
    }

    public void pressInnerDoorButton(){
        airportFireTruck.getCabin().getRightDoor().getInnerButton().press();
    }

    public void pressJoyStickFrontLeftButton(){
        airportFireTruck.getCabin().getRightJoystick().getFrontLeftButton().press();
    }

    public void pressJoyStickFrontRightButton(){
        airportFireTruck.getCabin().getRightJoystick().getFrontRightButton().press();
    }

    public void pressJoyStickFrontBackSwitch(){
        airportFireTruck.getCabin().getRightJoystick().getBackSwitch().press();
    }

    public void turnLauchnerKnobToRight(){
        airportFireTruck.getCabin().getRoofLauncherKnob().turnRight();
    }

    public void turnLauchnerKnobToLeft(){
        airportFireTruck.getCabin().getRoofLauncherKnob().turnLeft();
    }

}
