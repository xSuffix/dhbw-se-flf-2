package truck;

import enums.ButtonType;
import enums.PedalType;
import lights.BlueLight;
import lights.HeadLight;
import lights.TurnSignalLight;
import lights.WarningLight;

public class CentralUnit implements ICentralUnit {

    private final IAirportFireTruck airportFireTruck;

    public CentralUnit(IAirportFireTruck airportFireTruck) {
        this.airportFireTruck = airportFireTruck;
    }

    public void pedalPress(PedalType type) {
        switch (type) {
            case Gas -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity() + 4);
            case Brake -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity() - 4);
        }
        //update speed/battery displays while driving
        airportFireTruck.getCabin().getSpeedDisplay().writeValue(String.valueOf(airportFireTruck.getDrive().getCurrentVelocity()));
        airportFireTruck.getCabin().getBatteryDisplay().writeValue(String.valueOf(airportFireTruck.getDrive().getBatteryPercentage()));
    }

    //rotates axles to the exact rotation of steering wheel plus turn on
    public void turnSteeringWheel(int rotation) {
        airportFireTruck.getDrive().rotateAxles(rotation);
        if (rotation < 0) {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightLeft()) {
                light.turnOn();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightRight()) {
                light.turnOff();
            }
        } else if (rotation > 0) {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightLeft()) {
                light.turnOff();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightRight()) {
                light.turnOn();
            }
        } else {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightLeft()) {
                light.turnOff();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightRight()) {
                light.turnOff();
            }
        }
    }

    public void buttonPress(ButtonType type) {
        switch (type) {
            case motorSwitch -> {
                if (airportFireTruck.getDrive().motorsOn()) airportFireTruck.getDrive().stopMotors();
                else airportFireTruck.getDrive().startMotors();
            }
            case blueLightSwitch -> {
                for (BlueLight light : airportFireTruck.getBlueLights()) {
                    light.toggle();
                }
            }
            case warningLightSwitch -> {
                for (WarningLight light : airportFireTruck.getWarningLights()) {
                    light.toggle();
                }
            }
            case roofHeadLightSwitch -> {
                for (HeadLight light : airportFireTruck.getHeadLightsRoof()) {
                    light.toggle();
                }
            }
            case frontHeadlightSwitch -> {
                for (HeadLight light : airportFireTruck.getHeadLightsFrontLeft()) {
                    light.toggle();
                }
                for (HeadLight light : airportFireTruck.getHeadLightsFrontRight()) {
                    light.toggle();
                }

            }
            case leftDoorButton -> airportFireTruck.getCabin().getLeftDoor().toggle();
            case rightDoorButton -> airportFireTruck.getCabin().getRightDoor().toggle();
            // joystick for frontlauncher
            case leftJoyStickLeft -> airportFireTruck.getFrontLauncher().pan();
            case leftJoyStickRight -> airportFireTruck.getFrontLauncher().switchRatio();
            case leftJoyStickBack -> airportFireTruck.getFrontLauncher().sprayWater(airportFireTruck.getCabin().getControlPanel().getFrontLauncherKnob().getValue());
            // joystick for rooflauncher
            case rightJoyStickLeft -> airportFireTruck.getRoofLauncher().extend();
            case rightJoyStickRight -> airportFireTruck.getRoofLauncher().switchRatio();
            case rightJoyStickBack -> airportFireTruck.getRoofLauncher().sprayWater(airportFireTruck.getCabin().getControlPanel().getRoofLauncherKnob().getValue());

        }
    }
}
