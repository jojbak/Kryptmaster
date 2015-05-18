import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Scanner;

/**
 * Implements the playfair cipher The playfair cypher can only encrypt plain
 * text, no numbers is allowed. If you want to communicate a number you will
 * have to do it in text. The output will also lack spaces and punctuations.
 * Also observe that all j's will become i's.
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
		char[][] matrix = new char[5][5];
		matrix = buildPlayfairMatrix(key);
		displayMatrix(matrix);

		// TODO
		// returns in to avoid errors
		return in;
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
	 * This method formats the input String, enabling it to be encrypted
	 * @param input
	 * @return
	 */
	private String[] format(String input) {
		char[] chars = input.toCharArray();
		/*for (int i = 0; i < chars.length; i++) {
			if (chars[i] == 'j')
				chars[i] = 'i';
		}*/ //borde ej behövas iom preptext

		String text = new String(chars);

		int len = text.length();
		for (int i = 0; i < len; i = i + 2) {
			if (text.charAt(i) == text.charAt(i + 1)) {
				text = text.substring(0, i + 1) + 'X' + text.substring(i + 1); // add X to separate two of the same char in a row
			}																	
		}																
				
		if (text.length() % 2 != 0)
			text = text + "X"; // add X if the last char is "alone"

		return partition(text);
	}
	
	/**
	 * This method divides the string into digraphs
	 * @param input
	 * @return
	 */
	private String[] partition(String input) { // kolla datastruktur
		int len = input.length() / 2;
		String[] pairs = new String[len];
		char[] chars = input.toCharArray();

		for (int i = 0; i < chars.length; i = i + 2) {
			String s1 = Character.toString(chars[i]);
			String s2 = Character.toString(chars[i + 1]);
			int j = i - 1;
			if (i == 0)
				j = 0;
			pairs[j] = s1 + s2;
		}

		return pairs;
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
		String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // alphabet w/out j
		// preps the key
		key = prepText(key);
		// adds the alphabet to the end of the key
		key += alphabet;
		char[][] matrix = new char[5][5];
		// uses the key and the alphabet to set up the matrix, no duplicates
		StringBuilder sb = new StringBuilder();
		Set<Character> uniqueChar = new HashSet<Character>();

		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);

			if (!uniqueChar.contains(c)) {
				uniqueChar.add(c);
				sb.append(c);
			}
		}
		// debugging purposes println
		System.out.println("The letters : " + sb.toString());
		String uniqueLetters = sb.toString();
		// adds the letters into the matrix, 5 per row.
		int insertLetter = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// System.out.println(uniqueLetters.charAt(insertLetter));
				matrix[i][j] = uniqueLetters.charAt(insertLetter);
				insertLetter++;
			}
		}
		return matrix;
	}

	/**
	 * Preps the inputed key for its use in the matrix i.e to upper case,
	 * replace j with i and removes all non characters.
	 * 
	 * @param key
	 *            The key to be used
	 * @return The prepped key
	 */
	private String prepText(String text) {
		Scanner sc = new Scanner(text);
		String fixed = sc.nextLine();
		fixed = fixed.toUpperCase();
		// removes anything not in the alphabet
		fixed = fixed.replaceAll("[^A-Z]", "");
		fixed = fixed.replace("J", "I");
		sc.close();
		return fixed;
	}

	/**
	 * Displays a 5x5 matrix filled with chars
	 * 
	 * @param matrix
	 *            The matrix to be displayed
	 */
	private void displayMatrix(char[][] matrix) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(String.valueOf(matrix[i][j]) + " ");
			}
			System.out.println();
		}
	}

}
