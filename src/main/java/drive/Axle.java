package drive;

public class Axle {

    private final BreakDisc[] breakDiscsLeft;
    private final BreakDisc[] breakDiscsRight;
    private final Wheel leftWheel;
    private final Wheel rightWheel;

    public Axle() {
        this.breakDiscsLeft = new BreakDisc[3];
        for (int i = 0; i < breakDiscsLeft.length; i++) {
            breakDiscsLeft[i] = new BreakDisc();
        }
        this.breakDiscsRight = new BreakDisc[3];
        for (int i = 0; i < breakDiscsRight.length; i++) {
            breakDiscsRight[i] = new BreakDisc();
        }
        this.leftWheel = new Wheel();
        this.rightWheel = new Wheel();
    }

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

}
