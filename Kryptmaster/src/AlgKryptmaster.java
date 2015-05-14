import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * From the authors of kryptmaster comes a new revolutinary way to encrypt text.
 * It uses a variety of algs to come to an amazing performance.
 * 
 * @author rickard & jonathan
 * @version 2015-05-14
 *
 */
//untested code
public class AlgKryptmaster implements Algorithm {
	AlgCaesar cae = new AlgCaesar();

	/**
	 * Encrypts a linked list using a specified key
	 * 
	 * @param in
	 *            LinkedList containing strings
	 * @param key
	 *            A key to be used in the encryption, remember this if you want
	 *            to decrypt
	 * 
	 */
	@Override
	public LinkedList<String> encrypt(LinkedList in, String key) {
		// creates a random with key as the seed, converts the string into ascii
		// values
		Random rand = new Random(cae.stringToInt(key));
		// encrypts the list with caesar algorithm
		LinkedList<String> list = cae.encrypt(in, key);
		// shuffle the encrypted list based on the seed from key
		Collections.shuffle(list, rand);
		// returns the now shuffled list
		return list;
	}

	/**
	 * Decrypts a LinkedList that has been encrypted with the kryptmaster
	 * algortihm
	 * 
	 * @param in
	 *            LinkedList containing encrypted text
	 * @param key
	 *            The key used to encrypt will now be used to decrypt
	 */
	@Override
	public LinkedList<String> decrypt(LinkedList in, String key) {
		// gets the key in ascii for the random seed
		Random rand = new Random(cae.stringToInt(key));
		// creates a linked list that is to be used to find the original pos
		LinkedList<Integer> shuffleList = new LinkedList<Integer>();
		LinkedList<String> decryptedList = new LinkedList<String>();
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
		for (Integer i : shuffleList) {
			pos = i;
			// adds each object into its original pos
			// ex: ele from place 4 to place 12
			decryptedList.add(pos, in.get(iter).toString());
			iter++;
		}
		return (cae.decrypt(decryptedList, key));
	}

}