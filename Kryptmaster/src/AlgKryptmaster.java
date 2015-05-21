import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JProgressBar;

/**
 * From the authors of kryptmaster comes a new revolutinary way to encrypt text.
 * It uses a variety of algs to come to an amazing performance.
 * 
 * @author rickard & jonathan
 * @version 2015-05-14
 *
 */
// untested code, not yet pushed
public class AlgKryptmaster {
	AlgCaesar cae = new AlgCaesar();
	AlgRSA rsa = new AlgRSA();

	/**
	 * Encrypts a linked list using a specified key
	 * 
	 * @param in
	 *            LinkedList containing strings
	 * @param key
	 *            A key to be used in the encryption, remember this if you want
	 *            to decrypt
	 * @param jpb Reference to JProgressBar used to indicate progress when dealing with files
	 * 
	 */
	public LinkedList<String> encrypt(LinkedList<String> in, String key) {
		// creates a random with key as the seed, converts the string into ascii
		// values
		Random rand = new Random((cae.stringToInt(key) * 23));
		// encrypts the list with caesar algorithm
		LinkedList<String>encrypt = cae.encrypt(in, key);
		// shuffle the encrypted list based on the seed from key
		Collections.shuffle(encrypt, rand);
		//encrypts with rsa
		encrypt= rsa.encrypt(encrypt,key);

		// returns the now shuffled list
		return encrypt;
	}

	/**
	 * Decrypts a LinkedList that has been encrypted with the kryptmaster
	 * algortihm
	 * 
	 * @param in
	 *            LinkedList containing encrypted text
	 * @param key
	 *            The key used to encrypt will now be used to decrypt
	 * @param jpb JProgressBar to be used when dealing with larger files
	 */
	public LinkedList<String> decrypt(LinkedList in, String key) {
		// gets the key in ascii for the random seed
		Random rand = new Random((cae.stringToInt(key) * 23));
		// creates a linked list that is to be used to find the original pos
		LinkedList<Integer> shuffleList = new LinkedList<Integer>();
		LinkedList<String> decryptedList = new LinkedList<String>();
		//last encrypt first decrypt
		in = rsa.decrypt(in, key);
		// fills it with ints up to the size of the list we want to decrypt
		for (int i = 0; i < in.size(); i++) {
			shuffleList.add(i);
			decryptedList.add(null);
		}
		// shuffles the list with ints with the same seed as used to encrypt
		// to get what positions the list is shuffled
		Collections.shuffle(shuffleList, rand);
		// based on how the shuffleList got shuffled we can return
		// the list we want to decrypt to its original positions
		// on each positions is an int representing its original pos
		// this enables us to now how it gets shuffled
		int pos;
		int iter = 0;
		String s;
		for (Integer i : shuffleList) {
			pos = i;
			// adds each object into its original pos
			s = (String) in.get(iter);
			decryptedList.set(pos, s);
			iter++;
		}
		decryptedList = cae.decrypt(decryptedList, key);
		return decryptedList;
	}

}
