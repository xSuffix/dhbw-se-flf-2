package cabin.controls;

import truck.ICentralUnit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TurningKnob<E extends Enum<E>> implements ITurningKnob<E> {

    private final ICentralUnit centralUnit;
    private final TurningKnobType type;
    private final List<E> enumConstants;
    private final E min;
    private final E max;
    private E state;

    public TurningKnob(ICentralUnit centralUnit, TurningKnobType type, E output) {
        this.centralUnit = centralUnit;
        this.type = type;
        this.state = output;
        enumConstants = Arrays.asList(output.getDeclaringClass().getEnumConstants());
        min = Collections.min(enumConstants);
        max = Collections.max(enumConstants);
    }

    public E getState() {
        return state;
    }

    public void turnLeft() {
        if (state != min) state = enumConstants.get(enumConstants.indexOf(state) - 1);
        centralUnit.turningKnobTurn(this.type, state);
    }

    public void turnRight() {
        if (state != max) state = enumConstants.get(enumConstants.indexOf(state) + 1);
        centralUnit.turningKnobTurn(this.type, state);
    }

}
