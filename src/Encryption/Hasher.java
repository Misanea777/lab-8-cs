package Encryption;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.SecureRandom;
import java.util.Base64;

public class Hasher {

    private Hasher(){}

    public static String genHash(String passwd) {
        return BCrypt.hashpw(passwd, BCrypt.gensalt());
    }

    public static boolean checkHash(String raw, String hash) {
        return  BCrypt.checkpw(raw, hash);
    }

    public static String generateNewToken() {
        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }


}