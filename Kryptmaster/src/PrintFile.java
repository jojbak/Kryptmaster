import java.io.*;
public class PrintFile implements PrintToFile {

	private final static String NAME = PrintFile.class.getName();
	public void printFile(String path, String out) throws IOException{
		try{
			File f = new File(path, "asd(1).txt");
			f.createNewFile();
			
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(out);
			bw.close();
			
		}catch(IOException e){
			System.err.printf("%s: %s%n", NAME, e);
		}
	}
	
	public void deleteOldFile(String path)throws Exception{
		File f = new File(path);
		try{
			f.delete();
		}catch(Exception e){
			System.err.printf("%s: %s%n", NAME, e);
		}
	}
}
