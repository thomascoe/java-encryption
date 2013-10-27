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

    /**
     * Encrypts a message using the Playfair Cipher. First puts all letters into
     * an array and adds X's where there are two of the same characters in a
     * pair of characters. Adds an X to the end if the String is an odd length.
     *
     * @param message The String to be encrypted
     * @return        A String of the encrypted message
     */
    public String encrypt(String message) {
        message = message.toUpperCase();
        message = message.replace('J', 'I');
        message = message.replaceAll("\\s", "");
        ArrayList<Character> messageArray = new ArrayList<Character>();
        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            messageArray.add(message.charAt(i));
        }
        for (int i = 0; i < messageArray.size() - 1; i += 2) {
            if (messageArray.get(i).equals(messageArray.get(i + 1))) {
                messageArray.add(i + 1, 'X');
            }
        }
        if (messageArray.size() % 2 != 0) {
            messageArray.add('X');
        }
        for (int i = 0; i < messageArray.size() - 1; i += 2) {
            char a = messageArray.get(i);
            char b = messageArray.get(i + 1);
            int[] aCoord = getCoordinates(a);
            int[] bCoord = getCoordinates(b);
            if (aCoord[1] == bCoord[1]) { //same columns
                char c = keyTable[(aCoord[0] + 1) % 5][aCoord[1]];
                char d = keyTable[(bCoord[0] + 1) % 5][bCoord[1]];
                encrypted += c + "" + d;
            } else if (aCoord[0] == bCoord[0]) { //same rows
                char c = keyTable[aCoord[0]][(aCoord[1] + 1) % 5];
                char d = keyTable[bCoord[0]][(bCoord[1] + 1) % 5];
                encrypted += c + "" + d;
            } else { //rectangle
                char c = keyTable[aCoord[0]][bCoord[1]];
                char d = keyTable[bCoord[0]][aCoord[1]];
                encrypted += c + "" + d;
            }
        }
        return encrypted;
    }

    /**
     * Return the location in the keyTable of the specified character
     *
     * @param x The char to search for
     * @return  An int[] containing the coordinates of the character (row,col)
     */
    private int[] getCoordinates(char x) {
        for (int i = 0; i < keyTable.length; i++) {
            for (int j = 0; j < keyTable[i].length; j++) {
                if (keyTable[i][j] == x) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        PlayfairCipher test = new PlayfairCipher("playfairexample");
        System.out.println();
        System.out.println(test.encrypt("HIDE THE GOLD IN THE TREE STUMP"));
    }
}
