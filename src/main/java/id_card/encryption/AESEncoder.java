package id_card.encryption;

import id_card.RFIDChip;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;
import java.util.Base64;

public class AESEncoder implements IEncoder {
    private final String algorithm = "AES/CBC/PKCS5Padding";

    public SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    public IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    @Override
    public void encode(RFIDChip chip, String plainText) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        chip.setSecretKey(generateKey(256));
        chip.setIv(generateIv());
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, chip.getSecretKey(), chip.getIv());
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        chip.setCipher(Base64.getEncoder().encodeToString(cipherText));
    }

    @Override
    public String decode(RFIDChip chip) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, chip.getSecretKey(), chip.getIv());
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(chip.getCipher()));
        return new String(plainText);
    }
}

