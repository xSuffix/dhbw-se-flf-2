package truck.water;

import truck.visitor.ISelfCheckVisitor;
import truck.visitor.ISelfTestElement;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class Launcher implements ISelfTestElement {

    private final Object mixingUnit;
    private final ITank waterTank;
    private final ITank foamTank;
    protected LauncherState state;
    private MixingRatio ratio;

    public Launcher(Object mixingUnit, ITank waterTank, ITank foamTank) {
        this.mixingUnit = mixingUnit;
        this.state = LauncherState.INACTIVE;
        this.ratio = MixingRatio.A;
        this.waterTank = waterTank;
        this.foamTank = foamTank;
    }

    @Override
    public abstract boolean accept(ISelfCheckVisitor visitor);

    @SuppressWarnings("unchecked")
    public int sprayExtinguisher(int amount) {
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

        final int size = mixedAgent.size();
        System.out.printf("[Launcher] Spraying %d units of extinguisher (%.2f%% Water, %.2f%% Foam)%n", size, ((float) mixedAgent.stream().filter(type -> type == ExtinguishingType.WATER).count() / size)* 100f, ((float) mixedAgent.stream().filter(type -> type == ExtinguishingType.FOAM_POWDER).count() / size)* 100f) ;
        return size;
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
