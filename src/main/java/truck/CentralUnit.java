package truck;

import cabin.controls.FrontLauncherOutput;
import cabin.controls.PedalType;
import cabin.controls.RoofLauncherOutput;
import cabin.controls.TurningKnobType;
import cabin.controls.button.ButtonType;
import id_card.IDCardDecoder;
import lights.Light;
import truck.water.LauncherState;
import truck.water.MixingRatio;

import java.util.ArrayList;
import java.util.List;

public class CentralUnit implements ICentralUnit {

    private final IAirportFireTruck airportFireTruck;
    private final String name;
    private final String code;
    private final List<String> authorizedPersons;
    private final IDCardDecoder idCardDecoder;
    private int frontLauncherOutput;
    private int roofLauncherOutput;

    public CentralUnit(IAirportFireTruck airportFireTruck) {
        this.airportFireTruck = airportFireTruck;
        this.frontLauncherOutput = 500;
        this.roofLauncherOutput = 500;
        this.name = "DUS | FLF-5";
        this.code = "6072";
        this.authorizedPersons = new ArrayList<>();
        this.idCardDecoder = new IDCardDecoder();
    }

    @Override
    public String getID() {
        return "FT-" + name.replace("|", "").replaceAll("\\s+", "-");
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void authorizePerson(String name) {
        authorizedPersons.add(name);
    }

    @Override
    public void checkAuthentication(byte[] encryptedToken) {
        String token = idCardDecoder.decrypt(encryptedToken);
        if (token != null) {
            String id = getID();
            String name = token.substring(id.length() + 1, token.length() - code.length() - 1);
            boolean validToken = token.equals(id + "-" + name + "-" + code) && authorizedPersons.contains(name);
            if (validToken) {
                airportFireTruck.getCabin().getLeftDoor().toggleLock();
                airportFireTruck.getCabin().getRightDoor().toggleLock();
            }
        }
    }

    // rotates axles to the exact rotation of steering wheel plus turn on
    @Override
    public void turnSteeringWheel(int rotation) {
        airportFireTruck.getDrive().rotateAxles(rotation);
        for (Light light : airportFireTruck.getTurnSignalLightsLeft()) light.setOn(rotation < 0);
        for (Light light : airportFireTruck.getTurnSignalLightsRight()) light.setOn(rotation > 0);
    }

    @Override
    public void buttonPress(ButtonType type, boolean state) {
        switch (type) {
            case ELECTRIC_MOTOR -> airportFireTruck.getDrive().setMotorStarted(state);
            case BLUE_LIGHT -> {
                for (Light light : airportFireTruck.getBlueLights()) light.setOn(state);
            }
            case WARNING_LIGHT -> {
                for (Light light : airportFireTruck.getWarningLights()) light.setOn(state);
            }
            case ROOF_LIGHT -> {
                for (Light light : airportFireTruck.getHeadLightsRoof()) light.setOn(state);
            }
            case HEAD_LIGHT -> {
                for (Light light : airportFireTruck.getHeadLightsFrontLeft()) light.setOn(state);
                for (Light light : airportFireTruck.getHeadLightsFrontRight()) light.setOn(state);
            }
            case SIDE_LIGHT -> {
                for (Light light : airportFireTruck.getSideLightsLeft()) light.setOn(state);
                for (Light light : airportFireTruck.getSideLightsRight()) light.setOn(state);
            }
            case FIRE_SELF_PROTECTION -> airportFireTruck.useFloorNozzles(100);

            case LEFT_DOOR -> airportFireTruck.getCabin().getLeftDoor().setOpenIfUnlocked(state);
            case RIGHT_DOOR -> airportFireTruck.getCabin().getRightDoor().setOpenIfUnlocked(state);

            // joystick for front launcher
            case LEFT_JOYSTICK_LEFT -> airportFireTruck.getFrontLauncher().pan();
            case LEFT_JOYSTICK_RIGHT -> airportFireTruck.getFrontLauncher().switchRatio();
            case LEFT_JOYSTICK_BACK -> airportFireTruck.getFrontLauncher().sprayExtinguisher(frontLauncherOutput);

            // joystick for roof launcher
            case RIGHT_JOYSTICK_LEFT -> airportFireTruck.getRoofLauncher().extend();
            case RIGHT_JOYSTICK_RIGHT -> airportFireTruck.getRoofLauncher().switchRatio();
            case RIGHT_JOYSTICK_BACK -> airportFireTruck.getRoofLauncher().sprayExtinguisher(roofLauncherOutput);

            case SMART_JOYSTICK_LEFT -> {
                if (airportFireTruck.getFrontLauncher().getState() == LauncherState.INACTIVE)
                    airportFireTruck.getFrontLauncher().pan();
                else if (airportFireTruck.getFrontLauncher().getRatio() == MixingRatio.D)
                    airportFireTruck.getFrontLauncher().pan();
                else
                    airportFireTruck.getFrontLauncher().switchRatio();
            }

            case SMART_JOYSTICK_RIGHT -> {
                if (airportFireTruck.getRoofLauncher().getState() == LauncherState.INACTIVE)
                    airportFireTruck.getRoofLauncher().extend();
                else if (airportFireTruck.getRoofLauncher().getRatio() == MixingRatio.D)
                    airportFireTruck.getRoofLauncher().extend();
                else
                    airportFireTruck.getRoofLauncher().switchRatio();
            }
        }
    }

    @Override
    public void pedalPress(PedalType type) {
        switch (type) {
            case GAS -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity() + 4);
            case BRAKE -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity() - 4);
        }
        // update speed/battery displays while driving
        airportFireTruck.getCabin().getSpeedDisplay().setValue(String.valueOf(airportFireTruck.getDrive().getCurrentVelocity()));
        airportFireTruck.getCabin().getBatteryDisplay().setValue(String.valueOf(airportFireTruck.getDrive().getBatteryPercentage()));
    }

    @Override
    public <E> void turningKnobTurn(TurningKnobType type, E setting) {
        switch (type) {
            case FRONT_LAUNCHER -> frontLauncherOutput = Math.min(Math.max(((FrontLauncherOutput) setting).getValue(), 0), 3500);
            case ROOF_LAUNCHER -> roofLauncherOutput = Math.min(Math.max(((RoofLauncherOutput) setting).getValue(), 0), 10000);
        }
    }

}
