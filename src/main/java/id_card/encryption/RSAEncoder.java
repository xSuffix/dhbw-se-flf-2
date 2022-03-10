package id_card.encryption;

import id_card.RFIDChip;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class RSAEncoder implements IEncoder {
    private final String algorithm = "RSA";

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    @Override
    public void encode(RFIDChip chip, String plainText) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        KeyPair keyPair = generateKeyPair();
        chip.setPrivateKey(keyPair.getPrivate());
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        chip.setCipher(Base64.getEncoder().encodeToString(cipherText));
    }

    @Override
    public String decode(RFIDChip chip) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, chip.getPrivateKey());
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(chip.getCipher()));
        return new String(plainText);
    }
}
