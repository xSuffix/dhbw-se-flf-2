package truck;

import cabin.ButtonType;
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
            case GAS -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity() + 4);
            case BRAKE -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity() - 4);
        }
        //update speed/battery displays while driving
        airportFireTruck.getCabin().getSpeedDisplay().setValue(String.valueOf(airportFireTruck.getDrive().getCurrentVelocity()));
        airportFireTruck.getCabin().getBatteryDisplay().setValue(String.valueOf(airportFireTruck.getDrive().getBatteryPercentage()));
    }

    //rotates axles to the exact rotation of steering wheel plus turn on
    public void turnSteeringWheel(int rotation) {
        airportFireTruck.getDrive().rotateAxles(rotation);
        if (rotation < 0) {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsLeft()) {
                light.turnOn();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsRight()) {
                light.turnOff();
            }
        } else if (rotation > 0) {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsLeft()) {
                light.turnOff();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsRight()) {
                light.turnOn();
            }
        } else {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsLeft()) {
                light.turnOff();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsRight()) {
                light.turnOff();
            }
        }
    }

    public void buttonPress(ButtonType type) {
        switch (type) {
            case ELECTRIC_MOTOR -> {
                if (airportFireTruck.getDrive().motorsOn()) airportFireTruck.getDrive().stopMotors();
                else airportFireTruck.getDrive().startMotors();
            }
            case BLUE_LIGHT -> {
                for (BlueLight light : airportFireTruck.getBlueLights()) {
                    light.toggle();
                }
            }
            case WARNING_LIGHT -> {
                for (WarningLight light : airportFireTruck.getWarningLights()) {
                    light.toggle();
                }
            }
            case ROOF_LIGHT -> {
                for (HeadLight light : airportFireTruck.getHeadLightsRoof()) {
                    light.toggle();
                }
            }
            case HEAD_LIGHT -> {
                for (HeadLight light : airportFireTruck.getHeadLightsFrontLeft()) {
                    light.toggle();
                }
                for (HeadLight light : airportFireTruck.getHeadLightsFrontRight()) {
                    light.toggle();
                }

            }
            case FIRE_SELF_PROTECTION -> airportFireTruck.useFloorNozzles(100);

            case LEFT_DOOR -> airportFireTruck.getCabin().getLeftDoor().toggle();
            case RIGHT_DOOR -> airportFireTruck.getCabin().getRightDoor().toggle();

            // joystick for front launcher
            case LEFT_JOYSTICK_LEFT -> airportFireTruck.getFrontLauncher().pan();
            case LEFT_JOYSTICK_RIGHT -> airportFireTruck.getFrontLauncher().switchRatio();
            case LEFT_JOYSTICK_BACK -> airportFireTruck.getFrontLauncher().sprayWater(airportFireTruck.getCabin().getControlPanel().getFrontLauncherKnob().getValue());

            // joystick for roof launcher
            case RIGHT_JOYSTICK_LEFT -> airportFireTruck.getRoofLauncher().extend();
            case RIGHT_JOYSTICK_RIGHT -> airportFireTruck.getRoofLauncher().switchRatio();
            case RIGHT_JOYSTICK_BACK -> airportFireTruck.getRoofLauncher().sprayWater(airportFireTruck.getCabin().getControlPanel().getRoofLauncherKnob().getValue());
        }
    }

}
