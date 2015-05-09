

/**
 * Defines how the algorithm classes should be implemented.
 * @author jonathan
 *
 */
import java.util.LinkedList;
public interface Algorithm {

	/**
	 * Encrypts a string
	 */
	String encrypt(LinkedList in, String key); //bestäm datastruktur
	
	/**
	 * Decrypts a string
	 */
	String decrypt(LinkedList in, String key); //betäm datastruktur
	
}
