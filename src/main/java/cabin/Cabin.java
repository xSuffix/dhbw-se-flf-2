package cabin;

import cabin.controls.*;
import truck.ICentralUnit;

public class Cabin {

    private final BusDoor leftDoor;
    private final BusDoor rightDoor;
    private final Seat[] seats;
    private final IJoyStick leftJoystick;
    private final IJoyStick rightJoystick;
    private final ControlPanel controlPanel;
    private final ITurningKnob<FrontLauncherOutput> frontLauncherKnob;
    private final ITurningKnob<RoofLauncherOutput> roofLauncherKnob;
    private final ISteeringWheel steeringWheel;
    private final BatteryDisplay batteryDisplay;
    private final SpeedDisplay speedDisplay;
    private final Pedal breakPedal;
    private final Pedal gasPedal;

    public Cabin(ICentralUnit centralUnit, boolean smartJoySticks) {
        this.leftDoor = new BusDoor(centralUnit, ButtonType.LEFT_DOOR);
        this.rightDoor = new BusDoor(centralUnit, ButtonType.RIGHT_DOOR);
        this.seats = new Seat[SeatPositions.values().length];
        for (int i = 0; i < seats.length; i++) seats[i] = new Seat(SeatPositions.values()[i]);
        if (smartJoySticks){
            this.leftJoystick = new SmartJoyStick(centralUnit, JoystickType.LEFT);
            this.rightJoystick = new SmartJoyStick(centralUnit, JoystickType.RIGHT);
        } else {
            this.leftJoystick = new Joystick(centralUnit, JoystickType.LEFT);
            this.rightJoystick = new Joystick(centralUnit, JoystickType.RIGHT);
        }
        
        this.controlPanel = new ControlPanel(centralUnit);
        this.frontLauncherKnob = new TurningKnob<>(centralUnit, TurningKnobType.FRONT_LAUNCHER, FrontLauncherOutput.A);
        this.roofLauncherKnob = new TurningKnob<>(centralUnit, TurningKnobType.ROOF_LAUNCHER, RoofLauncherOutput.A);
        this.steeringWheel = new SteeringWheel(centralUnit);
        this.batteryDisplay = new BatteryDisplay();
        this.speedDisplay = new SpeedDisplay();
        this.breakPedal = new Pedal(centralUnit, PedalType.BRAKE);
        this.gasPedal = new Pedal(centralUnit, PedalType.GAS);
    }

    public BusDoor getLeftDoor() {
        return leftDoor;
    }

    public BusDoor getRightDoor() {
        return rightDoor;
    }

    public Seat getSeat(int pos) {
        return seats[pos];
    }

    public IJoyStick getLeftJoystick() {
        return leftJoystick;
    }

    public IJoyStick getRightJoystick() {
        return rightJoystick;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public ITurningKnob<FrontLauncherOutput> getFrontLauncherKnob() {
        return frontLauncherKnob;
    }

    public ITurningKnob<RoofLauncherOutput> getRoofLauncherKnob() {
        return roofLauncherKnob;
    }

    public ISteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public BatteryDisplay getBatteryDisplay() {
        return batteryDisplay;
    }

    public SpeedDisplay getSpeedDisplay() {
        return speedDisplay;
    }

    public Pedal getBreakPedal() {
        return breakPedal;
    }

    public Pedal getGasPedal() {
        return gasPedal;
    }

}
