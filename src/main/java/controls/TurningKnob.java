package controls;

import truck.ICentralUnit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TurningKnob<E extends Enum<E>> {

    private final ICentralUnit centralUnit;
    private final TurningKnobType type;
    private E output;
    private final List<E> enumConstants;
    private final E min;
    private final E max;

    public TurningKnob(ICentralUnit centralUnit, TurningKnobType type, E output) {
        this.centralUnit = centralUnit;
        this.type = type;
        this.output = output;
        enumConstants = Arrays.asList(output.getDeclaringClass().getEnumConstants());
        min = Collections.min(enumConstants);
        max = Collections.max(enumConstants);
    }

    public void turnLeft() {
        int index = enumConstants.indexOf(output);

        if (output != min) {
            output = enumConstants.get(index - 1);
        }

        centralUnit.turningKnobTurn(this.type, output);
    }

    public void turnRight() {
        int index = enumConstants.indexOf(output);
        System.out.println(output);

        if (output != max) {
            output = enumConstants.get(index + 1);
        }
        centralUnit.turningKnobTurn(this.type, output);
    }

    public int getValue() {
        int value = (int) output;
    }

}
