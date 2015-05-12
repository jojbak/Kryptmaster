import java.util.StringTokenizer;
import java.util.LinkedList;

/**
 * Turns strings into LinkedLists
 * @author Jonathan
 *
 */

public class Parser {
	AlgCaesar cae = new AlgCaesar();
	LinkedList<String> output = new LinkedList<String>();

	/**
	 * Sends a string to specified encryption algorithm
	 * @param in String to encrypt
	 * @param key Key to use in encryption
	 * @return String with encrypted text
	 */
	// anpassa för att skicka algoritmobjekt
	public String parseEncrypt(String in, String key) {
		output = cae.encrypt(stringToList(in), Integer.parseInt(key));
		StringBuilder sb = new StringBuilder();
		for(String word : output){
			sb.append(word);
			sb.append(" ");
		}

		return sb.toString();
	}

	/**
	 * Sends a encrypted string to a specified algorithm for decryption
	 * @param in String to decrypt
	 * @param key Key to use
	 * @return String with decrypted text
	 */
	public String parseDecrypt(String in, String key) {
		output = cae.decrypt(stringToList(in), Integer.parseInt(key));
		StringBuilder sb = new StringBuilder();
		for(String word : output){
			sb.append(word);
			sb.append(" ");
		}

		return sb.toString();
	}
	/**
	 * Transforms a string into a linked list where each object is
	 * a word that was separated with a blank space
	 * @param in Strint to turn into linked list
	 * @return LinkedList with strings
	 */
	private LinkedList<String> stringToList(String in){
		StringTokenizer stk = new StringTokenizer(in);
		LinkedList<String> input = new LinkedList<String>();
		while (stk.hasMoreTokens()) {
			input.add(stk.nextToken());
		}
		return input;
	}
}
