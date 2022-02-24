package cabin.controls;

public enum RoofLauncherOutput {
    A(500),
    B(1000),
    C(2500);

    private final int value;

    RoofLauncherOutput(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

}
