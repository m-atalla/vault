package vault;

import org.mindrot.jbcrypt.BCrypt;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;


public class CryptoManager {
    public Cipher cipher;
    private Key AESKey;

    public CryptoManager() {
        try {
            this.cipher = Cipher.getInstance("AES");
            this.setAESKey();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void setAESKey() {
        this.AESKey = new SecretKeySpec(Config.cryptoKey.getBytes(), "AES");
    }

    public byte[] encrypt(String text) throws GeneralSecurityException {
        cipher.init(Cipher.ENCRYPT_MODE, this.AESKey);

        byte[] cipherText = cipher.doFinal(text.getBytes());

        // Convert encryption bytes to Base64
        return Base64.getEncoder().encode(cipherText);
    }

    public String decrypt(byte[] encrypted) throws GeneralSecurityException {
        cipher.init(Cipher.DECRYPT_MODE, this.AESKey);
        // Decode base64 to encryption bytes
        byte[] decoded64 = Base64.getDecoder().decode(encrypted);
        // Decrypt bytes
        byte[] decryption = cipher.doFinal(decoded64);
        return new String(decryption);
    }

    public static String hash(String message) {
        return BCrypt.hashpw(message, BCrypt.gensalt());
    }

    public static boolean verifyHash(String input, String hash) {
        return BCrypt.checkpw(input, hash);
    }
}
