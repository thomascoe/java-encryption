import java.util.ArrayList;
/**
 * Encrypts and decrypts messages using a Playfair Cipher.
 * Note: I and J are treated as the same letters in order to fit into a 5x5 grid
 *
 * @author  Thomas Coe
 * @version 1.0 2013-10-27
 */
public class PlayfairCipher {

    private char[][] keyTable;
    private static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    /**
     * Constructor for a PlayfairCipher. Builds a keytable using the String
     * provided, interchanging I's for J's.
     *
     * @param key A String containing the key used to create the cipher
     */
    public PlayfairCipher(String key) {
        key = key.toUpperCase();
        key = key.replace('J', 'I');
        keyTable = new char[5][5];
        ArrayList<Character> alpha = new ArrayList<Character>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            alpha.add(ALPHABET.charAt(i));
        }
        int keyIndex = 0;
        for (int i = 0; i < keyTable.length; i++) {
            for (int j = 0; j < keyTable[i].length; j++) {
                boolean added = false;
                while (keyIndex < key.length() && !added) {
                    if (alpha.contains(key.charAt(keyIndex))) {
                        keyTable[i][j] = key.charAt(keyIndex);
                        alpha.remove((Character) key.charAt(keyIndex));
                        keyIndex++;
                        added = true;
                    } else {
                        keyIndex++;
                    }
                }
                if (keyIndex >= key.length() && !added) {
                    keyTable[i][j] = alpha.get(0);
                    alpha.remove(0);
                }
                System.out.print(keyTable[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PlayfairCipher test = new PlayfairCipher("playfairexample");
    }
}
