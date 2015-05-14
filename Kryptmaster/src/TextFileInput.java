

/**
 * Imports text from a textfile
 * @author Jonathan & Rickard
 *
 */
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
public interface TextFileInput {
	
	/**
	 *This method opens a textfile.
	 */
	void openFile(String path)throws IOException;
	
	/**
	 * This method reads from the textfile
	 */
	LinkedList readFile(BufferedReader file)throws IOException; //bestäm datastruktur
	

}
