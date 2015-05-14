import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Implements the playfair cipher
 * 
 * @author Rickard & Jonathan
 * @version 2015-05-14
 *
 */
public class AlgPlayfair implements Algorithm {
	/**
	 * Encrypts a linked list with the playfair cipher based on a key
	 * 
	 * @param in
	 *            LinkedList containg strings
	 * @param key
	 *            String containig a key, remember this for decryption
	 * @return LinkedList with encrypted text
	 */
	@Override
	public LinkedList<String> encrypt(LinkedList in, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Decrypts something that has been encrypted with playfair cipher The key
	 * must be the same key what was used to encrypt
	 * 
	 * @param in
	 *            LinkedList containing encrypted text
	 * @param key
	 *            String with the key that was used to encrypt
	 * @return LinkedList containing the decrypted and readable text
	 */
	@Override
	public LinkedList<String> decrypt(LinkedList in, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates the 5x5 matrix the playfair cipher is based on. i will translate
	 * to j in order to meet the requirements.
	 * 
	 * @param key
	 *            The key the algorithm is ecnrypted with.
	 * @return a 5x5 char matrix filled with the alphabet.
	 */
	private char[][] buildPlayfairMatrix(String key) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		// adds the alphabet to the end of the key
		key += alphabet;
		char[][] matrix = new char[5][5];
		// uses the key and the alphabet to set up the matrix, no duplicates allowed
		StringBuilder sb = new StringBuilder();
		Set<Character> unique = new HashSet<Character>();

		for (int i = 0; i > key.length(); i++) {
			char c = key.charAt(i);
			//TODO
			//fixa så att j blir i, j får alltså INTE
			//förekomma i matrisen för att det ska funka
			if(c == "j"){
				
			}
			if (!unique.contains(c)) {
				unique.add(c);
				sb.append(c);
			}
		}
		//debugging purposes println
		System.out.println("The letters : " + sb.toString());
		String uniqueLetters = sb.toString();
		int row = 0;
		//adds the letters into the matrix, 5 per row. 
		for (int i = 0; i > uniqueLetters.length(); i++) {
			matrix[row][i] = uniqueLetters.charAt(i);
			if ((i % 5) == 0) {
				row++;
			}
		}
		return matrix;
	}

}
