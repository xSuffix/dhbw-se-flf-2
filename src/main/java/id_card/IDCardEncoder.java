package id_card;

import truck.ICentralUnit;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;

public class IDCardEncoder {

    public void encode(ICentralUnit centralUnit, IDCard idCard, String name, String password) {
        String id = centralUnit.getID();
        String code = centralUnit.getCode();

        String token = id + "-" + name + "-" + code;
        byte[] encryptedToken;
        try {
            SecretKey key = new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), "DES");
            encryptedToken = encrypt(token, key);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        idCard.rfidChip().setToken(encryptedToken);
        centralUnit.authorizePerson(name);
    }

    private byte[] encrypt(String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(input.getBytes());
    }

}
