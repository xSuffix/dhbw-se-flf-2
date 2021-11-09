package cabin;

import enums.SeatPositions;

public class Seat {
    private SeatPositions position;

    public Seat(SeatPositions position){
        this.position = position;
    }

    public SeatPositions getPosition(){
        return this.position;
    }
}
