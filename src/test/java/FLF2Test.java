import org.junit.jupiter.api.*;
import truck.water.ExtinguishingType;
import truck.water.MixingRatio;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FLF2Test extends FLFTest {

    @BeforeEach
    public void setup() {
        initialize(false);
    }

    @Test
    @Order(1)
    public void useMixingUnitComponent() {
        final int waterCount = 3;
        final int foamCount = 1;
        ExtinguishingType[] water = new ExtinguishingType[waterCount];
        ExtinguishingType[] foam = new ExtinguishingType[foamCount];
        for (int i = 0; i < waterCount; i++) water[i] = ExtinguishingType.WATER;
        for (int i = 0; i < foamCount; i++) foam[i] = ExtinguishingType.FOAM_POWDER;

        Object mixingUnit = airportFireTruck.getMixingUnit();
        List<Object> mixedAgent;

        try {
            Class<?>[] argTypes = new Class[]{List.class};
            List<Object[]> args = new ArrayList<>();
            args.add(water);
            args.add(foam);

            Method method = mixingUnit.getClass().getDeclaredMethod("getMixedAgent", argTypes);
            //noinspection unchecked
            mixedAgent = (List<Object>) method.invoke(mixingUnit, args);
            System.out.println(mixedAgent);
            assertEquals(mixedAgent.stream().filter(o -> o == ExtinguishingType.WATER).count(), waterCount);
            assertEquals(mixedAgent.stream().filter(o -> o == ExtinguishingType.FOAM_POWDER).count(), foamCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        preDeployment();
        armFrontLauncher();
        testFrontLauncher(3, 3000, MixingRatio.C);
    }

    @Test
    @Order(5)
    public void useChargingAdapter() {

    }

    @Test
    @Order(6)
    public void useButtonStates() {

    }
}
