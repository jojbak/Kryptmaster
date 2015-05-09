import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.*;
public class InputFile implements TextFileInput {
	
	private final static String NAME = InputFile.class.getName();
	
	public void openFile(String path)throws IOException{
		try (BufferedReader file = new BufferedReader(new FileReader(path))) {
	        readFile(file);
	    } catch (IOException e) {
	        System.err.printf("%s: %s%n", NAME, e);
	    }
	}
	
	public LinkedList readFile(BufferedReader file)throws IOException{ 
		String text;
		LinkedList<String> in = new LinkedList<String>();
		while((text = file.readLine()) != null){
			StringTokenizer stk = new StringTokenizer(text);
			String part = stk.nextToken();
			in.add(part);
		}
		return in;
		
	}

}
