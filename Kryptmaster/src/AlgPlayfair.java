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
public class AlgPlayfair{
	char[][] matrix = new char[5][5];

	/**
	 * Encrypts a String with the playfair cipher based on a key
	 * 
	 * @param input
	 *            String containing the text to be encrypted.
	 * @param key
	 *            String containig a key, remember this for decryption
	 * @return String with encrypted text
	 */
	public String encrypt(String input, String key) {
		String uppercased = prepText(input);
		matrix = buildPlayfairMatrix(key);
		displayMatrix(matrix);

		String[] pairs = format(uppercased);
		int length = pairs.length;
		char[] output = new char[length * 2];
		for (int i = 0; i < length; i++) {
			char one = pairs[i].charAt(0);
			char two = pairs[i].charAt(1);
			//get the letters coordinates
			Point pointOne = getCoord(one);
			Point pointTwo = getCoord(two);
			int col1 = pointOne.getX();
			int row1 = pointOne.getY();
			int col2 = pointTwo.getX();
			int row2 = pointTwo.getY();
			//the letters are in the same column
			if (col1 == col2) {
				if (row1 != 4)
					row1++;
				else
					row1 = 0;

				if (row2 != 4)
					row2++;
				else
					row2 = 0;
				//the letters are in the same row
			} else if (row1 == row2) {
				if (col1 != 4)
					col1++;
				else
					col1 = 0;

				if (col2 != 4)
					col2++;
				else
					col2 = 0;
				//the letters create a rectangle
			} else {
				int temp = col1;
				col1 = col2;
				col2 = temp;
			}

			output[i * 2] = matrix[row1][col1];
			output[i * 2 + 1] = matrix[row2][col2];
		}
		String outString = new String(output);
		return outString;
	}

	/**
	 * Decrypts something that has been encrypted with playfair cipher The key
	 * must be the same key what was used to encrypt
	 * 
	 * @param input
	 *            String containing encrypted text
	 * @param key
	 *            String with the key that was used to encrypt
	 * @return String containing the decrypted and readable text
	 */
	public String decrypt(String input, String key) {
		matrix = buildPlayfairMatrix(key);
		displayMatrix(matrix);
		String uppercased = prepText(input);
		String[] pairs = partition(uppercased);
		int length = pairs.length;

		char[] output = new char[length * 2];
		for (int i = 0; i < length; i++) {
			char one = pairs[i].charAt(0);
			char two = pairs[i].charAt(1);
			//get the letters coordinates
			Point pointOne = getCoord(one);
			Point pointTwo = getCoord(two);
			int col1 = pointOne.getX();
			int row1 = pointOne.getY();
			int col2 = pointTwo.getX();
			int row2 = pointTwo.getY();
			//the letters are in the same column
			if (col1 == col2) {
				if (row1 != 0)
					row1--;
				else
					row1 = 4;

				if (row2 != 0)
					row2--;
				else
					row2 = 4;
				//the letters are in the same row
			} else if (row1 == row2) {
				if (col1 != 0)
					col1--;
				else
					col1 = 4;

				if (col2 != 0)
					col2--;
				else
					col2 = 4;
				//the letters create a rectangle
			} else {
				int temp = col2;
				col2 = col1;
				col1 = temp;
			}

			output[i * 2] = matrix[row1][col1];
			output[i * 2 + 1] = matrix[row2][col2];
		}
		String outString = new String(output);
		String outputStr = formatDekrypt(outString);
		return outputStr;
	}

	/**
	 * This class describes a point in the cipher matrix.
	 * 
	 * @author Jonathan & Rickard
	 *
	 */
	private class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		//return column value
		public int getX() {
			return x;
		}
		//return row value
		public int getY() {
			return y;
		}
	}
	/**
	 * Finds a letter's coordinates in the matrix 
	 * @param ch, the letter to be found
	 * @return pt, the Point of the letter in the matrix
	 */
	private Point getCoord(char ch) {
		Point pt = new Point(0, 0);
		if (ch == 'j')
			ch = 'i';
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrix[i][j] == ch) {
					pt = new Point(j, i);
				}
			}
		}
		return pt;
	}

	/**
	 * This method formats the input String, enabling it to be encrypted
	 * 
	 * @param input
	 * @return
	 */
	private String[] format(String input) {
		int len = input.length();
		for (int i = 0; i < len; i = i + 2) {
			if (i < len - 1) {
				if (input.charAt(i) == input.charAt(i + 1)) {
					input = input.substring(0, i + 1) + 'X'
							+ input.substring(i + 1); // add X to separate two
														// of the same char in a
														// row
				}
			}
		}

		if (input.length() % 2 != 0)
			input = input + "X"; // add X if the last char is "alone"

		return partition(input);
	}

	/**
	 * This method formats the decrypted text, removing any 'X'
	 * that separates two of the same characters in a row.
	 * @param input, the dekrypted text
	 * @return
	 */
	private String formatDekrypt(String input) {
		int len = input.length();
		int i = 0;
		while (i < len) {
			if (i + 2 < len - 2) {
				if ((input.charAt(i) == input.charAt(i + 2)) && input.charAt(i+1) == 'X') {
					input = input.substring(0, i + 1) + input.substring(i + 2);

				}
			}
			i++;
		}
		input = input.toLowerCase();
		return input;
	}

	/**
	 * This method divides the string into digraphs.
	 * 
	 * @param input, the String to be partitioned
	 * @return An array of digraphs in the form of Strings
	 */
	private String[] partition(String input) {
		int len = input.length() / 2;
		String[] pairs = new String[len];
		char[] chars = input.toCharArray();

		for (int i = 0; i < chars.length; i = i + 2) {
			String s1 = Character.toString(chars[i]);
			String s2 = Character.toString(chars[i + 1]);
			int j = i / 2;
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
