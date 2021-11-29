package cabin;

import enums.SeatPositions;

public class Seat {
    private final SeatPositions position;
    private final Respirator respirator;
    private boolean occupied;
    public Seat(SeatPositions position){
        this.respirator = new Respirator();
        this.position = position;
        this.occupied = false;
    }

    public void sitDown(){
        if (occupied){
            System.out.println("Seat is already occupied");
        } else {
            occupied = true;
        }
    }

    public void standUp(){
        if (occupied){
            occupied = false;
        } else {
            System.out.println("Seat is not occupied");
        }
    }

    public SeatPositions getPosition(){
        return this.position;
    }

    public boolean isOccupied(){
        return this.occupied;
    }

    public Respirator getRespirator() { return this.respirator; }
}
