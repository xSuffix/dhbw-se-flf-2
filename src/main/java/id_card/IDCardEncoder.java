package id_card;

import id_card.encryption.IEncoder;
import truck.Configuration;
import truck.ICentralUnit;

public class IDCardEncoder {
    IEncoder algorithm = Configuration.INSTANCE.encryptionAlgorithm;

    public void encode(ICentralUnit centralUnit, IDCard idCard, String name) {
        String token = String.format("%s-%s-%s", centralUnit.getID(), name, centralUnit.getCode());
        try {
            algorithm.encode(idCard.getChip(), token);
            centralUnit.authorizePerson(name);
            System.out.printf("[Security] Authorizing access for %s to %s using ID card encrypted with %s%n", name, centralUnit.getID(), algorithm.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
