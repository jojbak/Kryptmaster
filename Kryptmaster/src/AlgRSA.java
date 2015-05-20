import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

/**
 * Encrypts text using the asymetrical RSA encryption algorithm
 * 
 * @author Rickard & jonathan
 * @version 2015-05-18
 *
 */

public class AlgRSA {
	private AlgCaesar cae = new AlgCaesar();
	private BigInteger publicKey;
	private BigInteger privateKey;
	private BigInteger modulus;
	
	private LinkedList<String> encryptedOut;
	private LinkedList<String> decryptedOut;

	/**
	 * Encrypts using the RSA algorithm, ie the public key is used
	 * @param in The LinkedList of string that wants to be encrypted
	 * @param key The key used to encrypt
	 * @return LinkedList with numbers
	 */
	public LinkedList<String> encrypt(LinkedList<String> in, String key) {
		keyGen(key);
		encryptedOut = new LinkedList<String>();
		// for each word in the thing we want to encrypt
		for (String str : in) {
			//transform string into bytes to enable conversion
			//to BigInteger
			BigInteger val = new BigInteger(str.getBytes());
			//gets the encrypted value using our public keys
			encryptedOut.add(((val.modPow(publicKey, modulus))).toString());
		}
		return encryptedOut;
	}
	/**
	 * Decrypts using the RSA algorithm, if it has been encrypted with
	 * the proper public key it should decrypt.
	 * @param in LinkedList of numbers that should be decrypted
	 * @param key Key used to ensure that the private key is correct
	 * @return LinkedList containing the decrypted text
	 */
	public LinkedList<String> decrypt(LinkedList<String> in, String key) {
		keyGen(key);
		decryptedOut = new LinkedList<String>();
		
		for(String str : in){
			//should represent the  correct numbers
			BigInteger val = new BigInteger(str);
			//gets our original value back through RSA
			BigInteger decrypt = val.modPow(privateKey, modulus);
			//turns BigInteger back into string
			String dec = new String(decrypt.toByteArray());
			decryptedOut.add(dec);
		}
		return decryptedOut;
	}

	/**
	 * Generates the necessary keys based on the userinputed key
	 * 
	 * @param key
	 *            The key inputed by the user
	 */
	private void keyGen(String key) {
		BigInteger one = new BigInteger("1"); // needed for phi
		// same seed everytime the same key is used
		Random rand = new Random(cae.stringToInt(key));

		BigInteger p = BigInteger.probablePrime(1024, rand);
		BigInteger q = BigInteger.probablePrime(1024, rand);
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));

		modulus = p.multiply(q);
		// used to encrypt, standard value
		publicKey = new BigInteger("65537");
		// decryptionKey depends on publicKey and phi
		privateKey = publicKey.modInverse(phi);
	}

}
