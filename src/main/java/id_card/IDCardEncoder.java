package id_card;

import id_card.encryption.IEncoder;
import staff.Person;
import truck.Configuration;
import truck.ICentralUnit;

public class IDCardEncoder {
    IEncoder algorithm = Configuration.INSTANCE.encryptionAlgorithm;

    public void encode(ICentralUnit centralUnit, IDCard idCard, Person owner) {
        if (centralUnit != null && idCard != null && owner != null) {
            String token = String.format("%s-%s-%s", centralUnit.getID(), owner.getName(), centralUnit.getCode());
            try {
                algorithm.encode(idCard.getChip(), token);
                idCard.getChip().setOwner(owner);
                centralUnit.addAuthorization(owner);
                System.out.printf("[Security] Authorizing access for %s to %s using ID card encrypted with %s%n", owner.getName(), centralUnit.getID(), algorithm.getClass().getSimpleName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("[Security] Cannot encode ID Card because input is invalid");
        }
    }

    public void erase(ICentralUnit centralUnit, IDCard idCard) {
        if (idCard != null) {
            RFIDChip chip = idCard.getChip();
            if (centralUnit != null) centralUnit.removeAuthorization(chip.getOwner());
            chip.setOwner(null);
            chip.setCipher(null);
            chip.setSecretKey(null);
            chip.setIv(null);
            chip.setPrivateKey(null);
        }
    }
}
