package cabin;

import enums.ButtonType;
import enums.JoyStickType;
import enums.SeatPositions;
import truck.ICentralUnit;


public class Cabin {
    private final BusDoor leftDoor;
    private final BusDoor rightDoor;
    private final Seat[] seats;
    private final JoyStick leftJoyStick;
    private final JoyStick rightJoyStick;
    private final ControlPanel controlPanel;
    private final SteeringWheel steeringWheel;
    private final GasPedal gasPedal;
    private final BreakPedal breakPedal;
    private final SpeedDisplay speedDisplay;
    private final BatteryDisplay batteryDisplay;

    // todo hier die composition raus ziehen maybe
    public Cabin(ICentralUnit centralUnit) {
        this.seats = new Seat[SeatPositions.values().length];
        int i = 0;
        for (SeatPositions pos : SeatPositions.values()) {
            seats[i] = new Seat(pos);
            i++;
        }
        this.batteryDisplay = new BatteryDisplay();
        this.speedDisplay = new SpeedDisplay();
        this.steeringWheel = new SteeringWheel(centralUnit);
        this.breakPedal = new BreakPedal(centralUnit);
        this.gasPedal = new GasPedal(centralUnit);
        this.controlPanel = new ControlPanel(centralUnit);
        this.leftJoyStick = new JoyStick(centralUnit, JoyStickType.LEFT);
        this.rightJoyStick = new JoyStick(centralUnit, JoyStickType.RIGHT);
        this.leftDoor = new BusDoor(centralUnit, ButtonType.leftDoorButton);
        this.rightDoor = new BusDoor(centralUnit, ButtonType.rightDoorButton);
    }

    public Seat getSeat(int pos) {
        return seats[pos];
    }

    public JoyStick getLeftJoyStick() {
        return leftJoyStick;
    }

    public JoyStick getRightJoyStick() {
        return rightJoyStick;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public GasPedal getGasPedal() {
        return gasPedal;
    }

    public BreakPedal getBreakPedal() {
        return breakPedal;
    }

    public SpeedDisplay getSpeedDisplay() {
        return speedDisplay;
    }

    public BatteryDisplay getBatteryDisplay() {
        return batteryDisplay;
    }

    public BusDoor getLeftDoor() {
        return leftDoor;
    }

    public BusDoor getRightDoor() {
        return rightDoor;
    }
}
