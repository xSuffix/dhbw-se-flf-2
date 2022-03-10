package id_card;

import id_card.encryption.IEncoder;
import truck.Configuration;

public class IDCardDecoder {
    IEncoder algorithm = Configuration.INSTANCE.encryptionAlgorithm;

    public String decode(RFIDChip chip) {
        try {
            return algorithm.decode(chip);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
