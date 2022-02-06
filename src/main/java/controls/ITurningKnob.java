package controls;


public interface ITurningKnob<E extends Enum<E>>{
    
    E getState();

    void turnLeft();

    void turnRight();
}
