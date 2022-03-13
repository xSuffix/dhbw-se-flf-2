package id_card;

public class IDCard {
    private final RFIDChip chip = new RFIDChip();

    public RFIDChip getChip() {
        return chip;
    }
}
