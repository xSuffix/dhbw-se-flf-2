package drive;

public class Axle {
    private final BreakDisc[] breakDiscsLeft;
    private final BreakDisc[] breakDiscsRight;
    private final Wheel leftWheel;

    public BreakDisc[] getBreakDiscsLeft() {
        return breakDiscsLeft;
    }

    public BreakDisc[] getBreakDiscsRight() {
        return breakDiscsRight;
    }

    public Wheel getLeftWheel() {
        return leftWheel;
    }

    public Wheel getRightWheel() {
        return rightWheel;
    }

    private final Wheel rightWheel;

    public Axle(){
        this.breakDiscsLeft = new BreakDisc[3];
        for(BreakDisc disc : breakDiscsLeft){
            disc = new BreakDisc();
        }
        this.breakDiscsRight = new BreakDisc[3];
        for(BreakDisc disc : breakDiscsRight){
            disc = new BreakDisc();
        }
        this.leftWheel = new Wheel();
        this.rightWheel = new Wheel();
    }
}
