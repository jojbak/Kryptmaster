import java.util.StringTokenizer;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;

/**
 * Turns strings into LinkedLists
 * @author Jonathan & Rickard
 * @version 2015-05-12
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
	// This is a kind of hacky version, change to make work with algorithm objects.
	public String parseEncrypt(String in, String key, String alg) {
		switch(alg){
		case "Caesar":
			output = cae.encrypt(stringToList(in), key);
		case "Playfair":
			output = cae.encrypt(stringToList(in), key); //byt ut till playfair
		case "Kryptmaster":
			output = cae.encrypt(stringToList(in), key); //byt till kryptmaster
		}
		
		StringBuilder sb = new StringBuilder();
		for(String word : output){
			sb.append(word);
			sb.append(" ");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * Sends a encrypted string to a specified algorithm for decryption
	 * @param in String to decrypt
	 * @param key Key to use
	 * @return String with decrypted text
	 */
	public String parseDecrypt(String in, String key, String alg) {
		switch(alg){
		case "Caesar":
			output = cae.decrypt(stringToList(in), key);
		case "Playfair":
			output = cae.decrypt(stringToList(in), key); //byt ut till playfair
		case "Kryptmaster":
			output = cae.decrypt(stringToList(in), key); //byt till kryptmaster
		}
		StringBuilder sb = new StringBuilder();
		for(String word : output){
			sb.append(word);
			sb.append(" ");
		}

		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	/**
	 * With a given key and algorithm this method encrypts
	 * the contents of a textfile. It creates a new textfile
	 * with the output.
	 * @param key
	 * @param alg
	 * @throws IOException
	 */
	public void parseEncryptFile(String key, String alg) throws IOException{
		ChooseFile cf = new ChooseFile();
		File chosenFile = cf.file;
		InputFile input = new InputFile();
		LinkedList<String> inputStrings = input.openFile(chosenFile);
		
		switch(alg){
		case "Caesar":
			output = cae.encrypt(inputStrings, key);
		case "Playfair":
			output = cae.encrypt(inputStrings, key); //byt ut till playfair
		case "Kryptmaster":
			output = cae.encrypt(inputStrings, key); //byt till kryptmaster
		}
		StringBuilder sb = new StringBuilder();
		for(String word : output){
			sb.append(word);
			sb.append(" ");
		}

		sb.setLength(sb.length() - 1);
		
		PrintFile print = new PrintFile();
		print.printFile(chosenFile.getParent(), sb.toString(), chosenFile.getName());
		
	}
	/**
	 * With a given key and algorithm this method decrypts
	 * the contents of a textfile. It creates a new textfile
	 * with the output.
	 * @param key
	 * @param alg
	 * @throws IOException
	 */
	public void parseDecryptFile(String key, String alg) throws IOException{
		ChooseFile cf = new ChooseFile();
		File chosenFile = cf.file;
		InputFile input = new InputFile();
		LinkedList<String> inputStrings = input.openFile(chosenFile);
		
		switch(alg){
		case "Caesar":
			output = cae.decrypt(inputStrings, key);
		case "Playfair":
			output = cae.decrypt(inputStrings, key); //byt ut till playfair
		case "Kryptmaster":
			output = cae.decrypt(inputStrings, key); //byt till kryptmaster
		}
		StringBuilder sb = new StringBuilder();
		for(String word : output){
			sb.append(word);
			sb.append(" ");
		}

		sb.setLength(sb.length() - 1);
		
		PrintFile print = new PrintFile();
		print.printFile(chosenFile.getParent(), sb.toString(), chosenFile.getName());
		
	}
	/**
	 * Transforms a string into a linked list where each object is
	 * a word that was separated with a blank space
	 * @param in Strint to turn into linkLinkedList<String> inputStringed list
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
