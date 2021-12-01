package cabin;

import enums.SeatPositions;
import truck.ICentralUnit;

public class Cabin {

    private final BusDoor leftDoor;
    private final BusDoor rightDoor;
    private final Seat[] seats;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;
    private final ControlPanel controlPanel;
    private final SteeringWheel steeringWheel;
    private final BatteryDisplay batteryDisplay;
    private final SpeedDisplay speedDisplay;
    private final BreakPedal breakPedal;
    private final GasPedal gasPedal;

    // todo hier die composition raus ziehen maybe
    public Cabin(ICentralUnit centralUnit) {
        this.leftDoor = new BusDoor(centralUnit, ButtonType.LEFT_DOOR);
        this.rightDoor = new BusDoor(centralUnit, ButtonType.RIGHT_DOOR);
        this.seats = new Seat[SeatPositions.values().length];
        int i = 0;
        for (SeatPositions pos : SeatPositions.values()) seats[i++] = new Seat(pos);
        this.leftJoystick = new Joystick(centralUnit, JoystickType.LEFT);
        this.rightJoystick = new Joystick(centralUnit, JoystickType.RIGHT);
        this.controlPanel = new ControlPanel(centralUnit);
        this.steeringWheel = new SteeringWheel(centralUnit);
        this.batteryDisplay = new BatteryDisplay();
        this.speedDisplay = new SpeedDisplay();
        this.breakPedal = new BreakPedal(centralUnit);
        this.gasPedal = new GasPedal(centralUnit);
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

    public Joystick getLeftJoystick() {
        return leftJoystick;
    }

    public Joystick getRightJoystick() {
        return rightJoystick;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public BatteryDisplay getBatteryDisplay() {
        return batteryDisplay;
    }

    public SpeedDisplay getSpeedDisplay() {
        return speedDisplay;
    }

    public BreakPedal getBreakPedal() {
        return breakPedal;
    }

    public GasPedal getGasPedal() {
        return gasPedal;
    }

}
