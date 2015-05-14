import java.io.IOException;

/**
 * Creates a new textfile in the same directory as the one read and prints to it
 * @author Jonathan & Rickard
 *
 */
public interface PrintToFile {
	
	/**
	 * Create a new textfile and print to it
	 * @param String path, the path to the directory
	 * 		  String out, the output
	 * 		  String oldFileName, the name of the inputfile
	 */
	void printFile(String path, String out, String oldFileName)throws IOException;
	
	/**
	 * Deletes the original textfile if the user wants it
	 */
	void deleteOldFile(String path)throws Exception;

}
