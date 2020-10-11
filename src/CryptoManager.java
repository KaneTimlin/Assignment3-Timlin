/*
 *
 *
 *
 */

/**
 * @author Kane Timlin
 */

public class CryptoManager {

    static final char LOWER_BOUND = ' ';
    static final char UPPER_BOUND = '_';
    static final char RANGE = (char)UPPER_BOUND - LOWER_BOUND + 1;


    /**
     *
     * @param key the bellaso cipher's key, to be lengthened or shortened
     * @param textLength the length of the text the key needs to match
     * @return the extended key
     */

    private static String extendKey(String key, int textLength) {

        String extendedKey = "";

        while (extendedKey.length() != textLength) {
            for (int i = 0; i < key.length(); i++) {
                extendedKey += key.charAt(i);
                if (extendedKey.length() == textLength) {
                    break;
                }
            }
        }
        return extendedKey;
    }

    /**
     *
     * @param text the string to check if it is in bounds
     * @return whether the string input is within the bounds
     */

    public static boolean stringInBounds(String text) {
        for (int i = 0; i < text.length(); i++) {
            if ((text.charAt(i) > UPPER_BOUND) || (text.charAt(i) < LOWER_BOUND)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param c the char variable to be checked
     * @return whether the variable in within the bounds
     */

    public static boolean charInBounds(char c) {
        return c <= UPPER_BOUND && c >= LOWER_BOUND;
    }

    /**
     *
     * @param plainText the text to be encrypted
     * @param key the key that will be used to encrypt the given text
     * @return the encrypted text
     */

    public static String encryptCaesar(String plainText, int key) {

        char append = 0;
        String encrypted = "";

        if (stringInBounds(plainText)) {
            while (key > RANGE)
                key -= RANGE;
            for (int i = 0; i < plainText.length(); i++) {
                append = (char)(plainText.charAt(i) + key);
                if (!charInBounds(append))
                    append -= RANGE;
                encrypted += append;
            }
            return encrypted;
        } else {
            return "String is out of bounds.";
        }

    }

    /**
     *
     * @param encrypted the text to be decrypted
     * @param key the key that will be used for decryption
     * @return the decrypted string
     */

    public static String decryptCaesar(String encrypted, int key) {

        char append = 0;
        String plainText = "";

        if (stringInBounds(encrypted)) {
            while (key > RANGE)
                key -= RANGE;
            for (int i = 0; i < encrypted.length(); i++) {
                append = (char)(encrypted.charAt(i) - key);
                if (!charInBounds(append))
                    append += RANGE;
                plainText += append;
            }
            return plainText;
        }
        return "String is out of bounds";

    }

    /**
     *
     * @param plainText the text to be encrypted
     * @param key the key that will be used to encrypt the given text
     * @return the encrypted text
     */

    public static String encryptBellaso(String plainText, String key) {

        char append = 0;
        String encrypted = "";
        key = extendKey(key, plainText.length());
        if (stringInBounds(plainText) && stringInBounds(key)) {
            for (int i = 0; i < plainText.length(); i++) {
                append = (char) (plainText.charAt(i) + key.charAt(i));
                while (append > UPPER_BOUND) {
                    append -= RANGE;
                }
                encrypted += append;

            }
            return encrypted;
        } else {
            return "String or key is out of bounds.";
        }
    }

    /**
     *
     * @param encrypted the text to be decrypted
     * @param key the key that will be used for decryption
     * @return the decrypted string
     */

    public static String decryptBellaso(String encrypted, String key) {

        char append = 0;
        String plainText = "";
        key = extendKey(key, encrypted.length());

        if (stringInBounds(encrypted) && stringInBounds(key)) {
            for (int i = 0; i < encrypted.length(); i++) {
                append = (char)(encrypted.charAt(i) - key.charAt(i));
                while (!charInBounds(append))
                    append += RANGE;
                plainText += append;
            }
            return plainText;
        } else {
            return "String or key is out of bounds.";
        }
    }

}