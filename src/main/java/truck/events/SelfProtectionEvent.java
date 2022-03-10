package truck.events;

public class SelfProtectionEvent {
    private final int id;
    private final int amount;

    public SelfProtectionEvent(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }
}
