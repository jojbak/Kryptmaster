import java.util.LinkedList;

public class AlgCaesar /* implements Algorithm */{

	//implementera � � och �
	public LinkedList encrypt(LinkedList<String> in, int key) {
		LinkedList<String> out = new LinkedList<String>();
		for (String str : in) {
			// manipulera varje char med angiven nyckel
			char[] chars = str.toCharArray();
			for (int i=0; i < chars.length;i++) {
				int val = (int) chars[i]; // skapa if-sats f�r gr�nsen av ascii v�rden
				val = val + key;
				
				if (val > 126) {
					val = 33 + (val - 126);
				}
				chars[i] = (char) val;
				
				
			}
			String wordOut = new String (chars);
			out.add(wordOut);
			
		}
		
		return out;
	}

	public LinkedList decrypt(LinkedList<String> in, int key) {

		LinkedList<String> out = new LinkedList<String>();
		for (String str : in) {
			// manipulera varje char med angiven nyckel
			char[] chars = str.toCharArray();
			for (int i=0; i < chars.length;i++) {
				int val = (int) chars[i]; // skapa if-sats f�r gr�nsen av ascii v�rden
				val = val - key;
				
				if (val < 33) {
					val = 126 - (val - 33);
				}
				chars[i] = (char) val;
				
				
			}
			String wordOut = new String (chars);
			out.add(wordOut);
			
		}
		return out;
	}
}
