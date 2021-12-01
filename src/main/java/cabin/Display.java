package cabin;

public abstract class Display {
    private final String unit;
    private String value;

    public Display(String unit) {
        this.unit = unit;
        this.value = "0";
    }

    public void writeValue(String newValue) {
        this.value = newValue;
    }

    public String read() {
        return this.value + this.unit;
    }
}
