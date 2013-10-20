/**
 * A SubstitutionCipher uses an ordered String and mixedUp String to substitute
 * one letter for another to encrypt a message. The letters in matching
 * positions of the Strings are switched. The ordered and mixedUp Strings must
 * be the same length, and if they don't contain a character in a message that
 * character is not changed when the message is encrypted or decrypted.
 *
 * @author  Thomas Coe
 * @version 1.0 2013-10-19
 */
public class SubstitutionCipher {

    private String ordered;
    private String mixedUp;

    /**
     * Constructor for a SubstitutionCipher. Uses the ordered and mixedUp
     * Strings given to encrypt and decrypt messages.
     *
     * @param ordered The ordered list of characters to substitute
     * @param mixedUp The mixed up list of characters to substitute
     */
    public SubstitutionCipher(String ordered, String mixedUp) {
        this.ordered = ordered;
        this.mixedUp = mixedUp;
    }

    /**
     * Encrypt a String using the SubstitutionCipher's ordered and mixedUp
     * Strings.
     *
     * @param message The String to be encrypted
     * @return        An encrypted String
     */
    public String encrypt(String message) {
        message = message.toUpperCase();
        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            int pos = ordered.indexOf(message.charAt(i));
            if (pos > -1) {
                encrypted += mixedUp.charAt(pos);
            } else {
                encrypted += message.charAt(i);
            }
        }
        return encrypted;
    }

    /**
     * Decrypt a String using the SubstitutionCipher's ordered and mixedUp
     * Strings.
     *
     * @param message The encrypted String
     * @return        The original String
     */
    public String decrypt(String message) {
        message = message.toUpperCase();
        String decrypted = "";
        for (int i = 0; i < message.length(); i++) {
            int pos = mixedUp.indexOf(message.charAt(i));
            if (pos > -1) {
                decrypted += ordered.charAt(pos);
            } else {
                decrypted += message.charAt(i);
            }
        }
        return decrypted;
    }

    public static void main(String[] args) {
        String order = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String mixed = "ZEBRASCDFGHIJKLMNOPQTUVWXY";
        SubstitutionCipher test = new SubstitutionCipher(order, mixed);
        System.out.println(test.encrypt("flee at once. we are discovered!"));
        System.out.println(test.decrypt("SIAA ZQ LKBA. VA ZOA RFPBLUAOAR!"));
    }
}
