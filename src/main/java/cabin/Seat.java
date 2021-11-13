package cabin;

import enums.SeatPositions;

public class Seat {
    private SeatPositions position;
    private Respirator respirator;
    public Seat(SeatPositions position){
        this.respirator = new Respirator();
        this.position = position;
    }

    public SeatPositions getPosition(){
        return this.position;
    }

    public Respirator getRespirator() { return this.respirator; }
}
