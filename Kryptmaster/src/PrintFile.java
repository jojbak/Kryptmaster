import java.io.*;
public class PrintFile implements PrintToFile {

	private final static String NAME = InputFile.class.getName();
	public void printFile(String path, String out) throws IOException{
		try{
			File f = new File(path);
			f.getParentFile().mkdirs(); 
			f.createNewFile();
			
			
		}catch(IOException e){
			System.err.printf("%s: %s%n", NAME, e);
		}
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(path), "utf-8"))) {
				  writer.write(out);
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
