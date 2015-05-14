import java.io.*;
public class PrintFile implements PrintToFile {

	private final static String NAME = PrintFile.class.getName();
	/**
	 * Create a new textfile and print the output to it
	 */
	public void printFile(String path, String out, String oldFileName) throws IOException{
		try{
			String newFileName = oldFileName.substring(0, oldFileName.length()-4); //delete ".txt"
			File f = new File(path, newFileName + "(1).txt");
			f.createNewFile();
			
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(out);
			bw.close();
			
		}catch(IOException e){
			System.err.printf("%s: %s%n", NAME, e);
		}
	}
	/**
	 * Deletes the inputfile if the user wants it
	 */
	public void deleteOldFile(String path)throws Exception{
		File f = new File(path);
		try{
			f.delete();
		}catch(Exception e){
			System.err.printf("%s: %s%n", NAME, e);
		}
	}
}
