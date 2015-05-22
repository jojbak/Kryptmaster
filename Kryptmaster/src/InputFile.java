import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.*;

public class InputFile implements TextFileInput {

	private final static String NAME = InputFile.class.getName();

	public LinkedList openFile(File file) throws IOException {
		LinkedList<String> inputString = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			inputString = readFile(reader);
		} catch (IOException e) {
			System.err.printf("%s: %s%n", NAME, e);
		}

		return inputString;
	}

	public LinkedList readFile(BufferedReader reader) throws IOException {
		String text;
		LinkedList<String> in = new LinkedList<String>();
		while ((text = reader.readLine()) != null) {
			StringTokenizer stk = new StringTokenizer(text);
			while (stk.hasMoreElements()) {
				String part = stk.nextToken();
				in.add(part);
			}
		}
		return in;

	}

}
