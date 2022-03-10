package id_card.encryption;

import id_card.RFIDChip;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DESEncoder implements IEncoder {
    private final String algorithm = "DES/ECB/PKCS5Padding";

    public SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        return keyGenerator.generateKey();
    }

    @Override
    public void encode(RFIDChip chip, String plainText) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        chip.setSecretKey(generateKey());
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, chip.getSecretKey());
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        chip.setCipher(Base64.getEncoder().encodeToString(cipherText));
    }

    @Override
    public String decode(RFIDChip chip) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, chip.getSecretKey());
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(chip.getCipher()));
        return new String(plainText);
    }
}
