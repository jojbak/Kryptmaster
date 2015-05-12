import java.util.StringTokenizer;
import java.util.LinkedList;

public class Parser {
	AlgCaesar cae = new AlgCaesar();

	// anpassa för att skicka algoritmobjekt
	public String parseEncrypt(String in, String key) {
		StringTokenizer stk = new StringTokenizer(in);
		LinkedList<String> input = new LinkedList<String>();
		LinkedList<String> output = new LinkedList<String>();
		while (stk.hasMoreTokens()) {
			input.add(stk.nextToken());
		}
		output = cae.encrypt(input, Integer.parseInt(key));
		StringBuilder sb = new StringBuilder();
		for(String word : output){
			sb.append(word);
			sb.append(" ");
		}

		return sb.toString();
	}

	public void parseDecrypt() {

	}
}
