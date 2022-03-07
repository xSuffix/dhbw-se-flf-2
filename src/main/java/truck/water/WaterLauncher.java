package truck.water;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class WaterLauncher {

    private final Object mixingUnit;
    private final ITank waterTank;
    private final ITank foamTank;
    protected LauncherState state;
    private MixingRatio ratio;

    public WaterLauncher(Object mixingUnit, ITank waterTank, ITank foamTank) {
        this.mixingUnit = mixingUnit;
        this.state = LauncherState.INACTIVE;
        this.ratio = MixingRatio.A;
        this.waterTank = waterTank;
        this.foamTank = foamTank;
    }

    @SuppressWarnings("unchecked")
    public void sprayWater(int amount) {
        ExtinguishingType[] water = waterTank.getAgent(amount - ((amount * ratio.getValue()) / 100));
        ExtinguishingType[] foam = foamTank.getAgent((amount * ratio.getValue()) / 100);
        List<Object> mixedAgent = new ArrayList<>();

        try {
            Class<?>[] argTypes = new Class[]{List.class};
            List<Object[]> args = new ArrayList<>();
            args.add(water);
            args.add(foam);

            Method method = mixingUnit.getClass().getDeclaredMethod("getMixedAgent", argTypes);
            mixedAgent = (List<Object>) method.invoke(mixingUnit, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Sprühe " + mixedAgent.size() + " Einheiten Löschmittel!");
    }

    public LauncherState getState() {
        return this.state;
    }

    public MixingRatio getRatio() {
        return ratio;
    }

    public void switchRatio() {
        if (state == LauncherState.ACTIVE) {
            this.ratio = this.ratio.getNext();
        }
    }

}
