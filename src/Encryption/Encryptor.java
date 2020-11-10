package Encryption;

public class Encryptor {

    private static String secretKey = "IcnFTkeOaTWwAcuCjksMCSNkLFClmBf4h5sw7qE22n8ga";

    public static String encryptwithAES(String originalString) {
        return AES.encrypt(originalString, secretKey);
    }

    public static String decryptAes(String encryptedString) {
        return AES.decrypt(encryptedString, secretKey);
    }


}
