package cabin;

import enums.SeatPositions;




public class Cabin {
    private BusDoor leftDoor;
    private BusDoor rightDoor;
    private final Seat[] seats;
    private JoyStick leftJoyStick;
    private JoyStick rightJoyStick;
    private ControlPanel controlPanel;
    private SteeringWheel steeringWheel;
    private GasPedal gasPedal;
    private BreakPedal breakPedal;
    private SpeedDisplay speedDisplay;
    private BatteryDisplay batteryDisplay;

    public Cabin(){
        this.seats = new Seat[SeatPositions.values().length];
        int i = 0;
        for(SeatPositions pos : SeatPositions.values()) {
            seats[i] = new Seat(pos);
            i++;
        }
        this.batteryDisplay = new BatteryDisplay();
        this.speedDisplay = new SpeedDisplay();
        this.steeringWheel = new SteeringWheel();
    }

    public Seat getSeat(int pos){
        return seats[pos];
    }
}
