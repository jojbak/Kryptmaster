import java.util.StringTokenizer;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * Turns strings into LinkedLists that are either encrypted or decrypted
 * 
 * @author Jonathan & Rickard
 * @version 2015-05-12
 *
 */

public class Parser {
	AlgCaesar cae = new AlgCaesar();
	AlgPlayfair play = new AlgPlayfair();
	AlgKryptmaster krypt = new AlgKryptmaster();
	AlgRSA rsa = new AlgRSA();
	LinkedList<String> output = new LinkedList<String>();
	String out;
	JProgressBar jpb;

	public Parser(JProgressBar jpb) {
		this.jpb = jpb;
	}

	/**
	 * Sends a string to specified encryption algorithm
	 * 
	 * @param in
	 *            String to encrypt
	 * @param key
	 *            Key to use in encryption
	 * @param alg
	 *            The chosen algorithm
	 * @return String with encrypted text
	 */
	// This is a kind of hacky version, change to make work with algorithm
	// objects.
	public String parseEncrypt(String in, String key, String alg) {
		switch (alg) {
		case "Caesar":
			output = cae.encrypt(stringToList(in), key);
			break;
		case "Playfair":
			out = play.encrypt(stringWithoutSpaces(in), key);
			return out;
		case "Kryptmaster":
			output = krypt.encrypt(stringToList(in), key);
			break;
		case "RSA":
			output = rsa.encrypt(stringToList(in), key);
			break;
		}

		StringBuilder sb = new StringBuilder();
		for (String word : output) {
			sb.append(word);
			sb.append(" ");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * Sends a encrypted string to a specified algorithm for decryption
	 * 
	 * @param in
	 *            String to decrypt
	 * @param key
	 *            Key to use
	 * @param alg
	 *            The chosen algorithm
	 * @return String with decrypted text
	 */
	public String parseDecrypt(String in, String key, String alg) {
		switch (alg) {
		case "Caesar":
			output = cae.decrypt(stringToList(in), key);
			break;
		case "Playfair":
			out = play.decrypt(stringWithoutSpaces(in), key);
			return out;
		case "Kryptmaster":
			output = krypt.decrypt(stringToList(in), key);
			break;
		case "RSA":
			output = rsa.decrypt(stringToList(in), key);
			break;
		}
		StringBuilder sb = new StringBuilder();
		for (String word : output) {
			sb.append(word);
			sb.append(" ");
		}

		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * With a given key and algorithm this method encrypts the contents of a
	 * textfile. It creates a new textfile with the output.
	 * 
	 * @param key
	 *            The encryption key
	 * @param alg
	 *            The chosen algorithm
	 * @throws IOException
	 */
	public void parseEncryptFile(String key, String alg) throws IOException {
		jpb.setString("Reading from file..");
		ChooseFile cf = new ChooseFile();
		File chosenFile = cf.file;
		if (chosenFile == null)
			return;
		InputFile input = new InputFile();
		LinkedList<String> inputStrings = input.openFile(chosenFile);
		jpb.setString("Encrypting text...");
		switch (alg) {
		case "Caesar":
			output = cae.encrypt(inputStrings, key);
			break;
		case "Playfair":
			out = play.encrypt(listToString(inputStrings), key);
			PrintFile print = new PrintFile();
			print.printFile(chosenFile.getParent(), out, chosenFile.getName());
			return;
		case "Kryptmaster":
			output = krypt.encrypt(inputStrings, key);
			break;
		case "RSA":
			output = rsa.encrypt(inputStrings, key);
			break;
		}
		StringBuilder sb = new StringBuilder();
		for (String word : output) {
			sb.append(word);
			sb.append(" ");
		}

		sb.setLength(sb.length() - 1);

		PrintFile print = new PrintFile();
		print.printFile(chosenFile.getParent(), sb.toString(),
				chosenFile.getName());
		jpb.setString("Done");

	}

	/**
	 * With a given key and algorithm this method decrypts the contents of a
	 * textfile. It creates a new textfile with the output.
	 * 
	 * @param key
	 *            The encryption key
	 * @param alg
	 *            The chosen algorithm
	 * @throws IOException
	 */
	public void parseDecryptFile(String key, String alg) throws IOException {
		jpb.setString("Reading from file..");
		ChooseFile cf = new ChooseFile();
		File chosenFile = cf.file;
		if (chosenFile == null)
			return;
		InputFile input = new InputFile();
		LinkedList<String> inputStrings = input.openFile(chosenFile);
		jpb.setString("Decrypting File...");
		switch (alg) {
		case "Caesar":
			output = cae.decrypt(inputStrings, key);
			break;
		case "Playfair":
			out = play.decrypt(listToString(inputStrings), key);
			PrintFile print = new PrintFile();
			print.printFile(chosenFile.getParent(), out, chosenFile.getName());
			return;
		case "Kryptmaster":
			output = krypt.decrypt(inputStrings, key);
			break;
		case "RSA":
			output = rsa.decrypt(inputStrings, key);
			break;
		}
		StringBuilder sb = new StringBuilder();
		for (String word : output) {
			sb.append(word);
			sb.append(" ");
		}

		sb.setLength(sb.length() - 1);

		PrintFile print = new PrintFile();
		print.printFile(chosenFile.getParent(), sb.toString(),
				chosenFile.getName());
		jpb.setString("Done");
	}

	/**
	 * Transforms a string into a linked list where each object is a word that
	 * was separated with a blank space
	 * 
	 * @param in
	 *            Strint to turn into linkLinkedList<String> inputStringed list
	 * @return LinkedList with strings
	 */
	private LinkedList<String> stringToList(String in) {
		StringTokenizer stk = new StringTokenizer(in);
		LinkedList<String> input = new LinkedList<String>();
		while (stk.hasMoreTokens()) {
			input.add(stk.nextToken());
		}
		return input;
	}

	/**
	 * Removes the whitespaces in a String.
	 * 
	 * @param in
	 *            , The String to be manipulated
	 * @return The String without whitespaces
	 */
	private String stringWithoutSpaces(String in) {
		StringTokenizer stk = new StringTokenizer(in);
		StringBuilder sb = new StringBuilder();
		while (stk.hasMoreTokens()) {
			sb.append(stk.nextToken());
		}

		return sb.toString();
	}

	/**
	 * Creates a String with the contents of a LinkedList.
	 * 
	 * @param in
	 *            , The LinkedList to be converted
	 * @return The String converted from the LinkedList
	 */
	private String listToString(LinkedList<String> in) {
		StringBuilder sb = new StringBuilder();
		for (String word : in) {
			sb.append(word);
		}
		return sb.toString();
	}

	/**
	 * easteregg
	 * 
	 * @param in
	 */
	public String easteregg(String in) {
		// easteregg can only handle lowercase letters
		in = in.toLowerCase();
		AlgEasteregg east = new AlgEasteregg();
		LinkedList<String> out = new LinkedList<String>();
		output = east.encrypt(stringToList(in));

		StringBuilder sb = new StringBuilder();
		for (String word : output) {
			sb.append(word);
			sb.append(" ");
		}

		return sb.toString();
	}
}
