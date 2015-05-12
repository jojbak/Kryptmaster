import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.*;
public class InputFile /*implements TextFileInput*/ {
	
	private final static String NAME = InputFile.class.getName();
	
	
	public void openFile(File file)throws IOException{
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        readFile(reader);
	    } catch (IOException e) {
	        System.err.printf("%s: %s%n", NAME, e);
	    }
	}
	
	public LinkedList readFile(BufferedReader reader)throws IOException{ 
		String text;
		LinkedList<String> in = new LinkedList<String>();
		while((text = reader.readLine()) != null){
			StringTokenizer stk = new StringTokenizer(text);
			String part = stk.nextToken();
			in.add(part);
		}
		return in;
		
	}

}
