/**
 * Encrypts messages using a Vigenere Cipher. Does not allow for encryption or
 * decryption of symbols, only the 26 letters of the English alphabet. There are
 * multiple different ways of handling extra symbols, this asssumes the user is
 * only encrypting/decrypting words.
 *
 * A Vigenere Cipher is similar to a Caesar Cipher in that it simply involves
 * the shifting of letters in a message. However, it is more secure than the
 * Caesar Cipher because the shift is not constant for each letter. Instead, the
 * shift is determined using a special key. In order to determine the shift, the
 * key is repeated a number of times until it's length is the same as the String
 * to be encrypted. Then, the shift for the nth character in the message is
 * equal to the numerical value of the nth character in the repeated key String.
 *
 * @author  Thomas coe
 * @version 1.1 2013-10-18
 */
public class VigenereCipher {

    /**
     * Encrypt a String using a Vigenere Cipher with the key given. First
     * removes all whitespace and converts letters to uppercase.
     *
     * @param message The original String to encrypt
     * @param key     The key to use to encrypt the message
     * @return        A String containing the encrypted message
     */
    public static String encrypt(String message, String key) {
        message = message.trim().toUpperCase();
        message = message.replaceAll("\\s", "");
        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            int c = message.charAt(i) + key.charAt(i % key.length()) - 65;
            if (c > 90) {
                while (c > 90) {
                    c -= 26;
                }
            } else if (c < 65) {
                while (c < 65) {
                    c += 26;
                }
            }
            encrypted += (char) c;
        }
        return encrypted;
    }

    /**
     * Decrypts a String encrypted with a Vigenere Cipher using a given key
     *
     * @param message The encrypted message String
     * @param key     A String containing the key used to encrypt the message
     * @return        A String containing the decrypted message
     */
    public static String decrypt(String message, String key) {
        message = message.trim().toUpperCase();
        message = message.replaceAll("\\s", "");
        String decrypted = "";
        for (int i = 0; i < message.length(); i++) {
            int c = message.charAt(i) - key.charAt(i % key.length()) + 65;
            if (c > 90) {
                while (c > 90) {
                    c -= 26;
                }
            } else if (c < 65) {
                while (c < 65) {
                    c += 26;
                }
            }
            decrypted += (char) c;
        }
        return decrypted;
    }
}
