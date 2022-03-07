import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MixingUnit {
    private static final MixingUnit instance = new MixingUnit();
    public Port port;

    public MixingUnit() {
        port = new Port();
    }

    public static MixingUnit getInstance() {
        return instance;
    }

    public class Port implements IMixingUnit {

        public List<Object> getMixedAgent(List<Object[]> ingredients) {
            List<Object> mixedAgent = new ArrayList<>();
            for (Object[] ingredient : ingredients) mixedAgent.addAll(List.of(ingredient));
            Collections.shuffle(mixedAgent);
            return mixedAgent;
        }
    }
}
