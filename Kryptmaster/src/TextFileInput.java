
/**
 * Imports text from a textfile
 * @author Jonathan & Rickard
 *
 */
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

public interface TextFileInput {

	/**
	 * This method opens a textfile.
	 */
	LinkedList openFile(File file) throws IOException;

	/**
	 * This method reads from the textfile
	 */
	LinkedList readFile(BufferedReader reader) throws IOException;

}
