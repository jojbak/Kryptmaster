

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
	LinkedList<String> encrypt(LinkedList<String> in, String key); //bestäm datastruktur
	
	/**
	 * Decrypts a string
	 */
	LinkedList<String> decrypt(LinkedList<String> in, String key); //betäm datastruktur
	
}
