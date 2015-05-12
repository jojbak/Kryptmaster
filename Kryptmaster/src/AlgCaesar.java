import java.util.LinkedList;

/**
 * Implements the simple caesar algorithm using words as key
 * 
 * @author Rickard & Jonathan
 * @version 2015-05-12
 *
 */

public class AlgCaesar /* implements Algorithm */{

	/**
	 * Encrypts a LinkedList containing strings using the caesar chipher.
	 * 
	 * @param in
	 *            LinkedList with strings
	 * @param key
	 *            A password which we base the encryption on
	 * @return LinkedList containing encrypted strings
	 */
	// implementera å ä och ö
	public LinkedList<String> encrypt(LinkedList<String> in, String key) {
		int keyInt = stringToInt(key);
		LinkedList<String> out = new LinkedList<String>();
		for (String str : in) {
			// manipulate each char based on the key
			char[] chars = str.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				// turns into ascii when casted to int

				int val = (int) chars[i];
				// if proper char based on ascii value range

				if (val > 32 && val < 126) {
					// reduce for easier calculations, will increase to proper
					// range later
					val = val - 32;
					// modulo to reduce into correct range
					val = (val + keyInt) % 96;
					if (val < 0) {
						val += 96;
					}

				}
				//the 32 we subtracted earlier
				chars[i] = (char) (val + 32);

			}
			String wordOut = new String(chars);
			out.add(wordOut);

		}

		return out;
	}

	/**
	 * Decrypts someting encrypted with the caesar algorithm, provided the key.
	 * 
	 * @param in
	 *            LinkedList with encrypted strings
	 * @param key
	 *            String with the password
	 * @return LinkedList with now decrypted and fully readable strings
	 */

	public LinkedList<String> decrypt(LinkedList<String> in, String key) {

		LinkedList<String> out = new LinkedList<String>();
		int keyInt = stringToInt(key);
		for (String str : in) {
			// manipulate each char based on the key
			char[] chars = str.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				// turns into ascii when casted to int
				int val = (int) chars[i];
				// if proper char based on ascii value range
				if (val > 32 && val < 126) {
					// reduce for easier calculations, will increase to proper
					// range later
					val = val - 32;
					// modulo to reduce into correct range
					val = (val - keyInt) % 96;
					if (val < 0) {
						val += 96;
					}
				}
				//the 32 we subtracted earlier
				chars[i] = (char) (val + 32);

			}
			String wordOut = new String(chars);
			out.add(wordOut);

		}
		return out;
	}

	/*
	 * Transforms a string into a number using its ascii values
	 */
	private int stringToInt(String key) {
		int number = 0;
		char[] chars = key.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			// transforms chars into ascii with cast
			number += (int) chars[i];
		}
		return (number);
	}
}
