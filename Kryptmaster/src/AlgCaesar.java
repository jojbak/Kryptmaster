
import java.util.LinkedList;
public class AlgCaesar /*implements Algorithm*/ {
    
	public String encrypt(LinkedList<String> in, int key){
		
		for(String str : in){
			//manipulera varje char med angiven nyckel
			char[] chars = str.toCharArray();
			for(char ch : chars){
				int val = (int) ch; // skapa if-sats f�r gr�nsen av ascii v�rden
				val = val + key;
				ch = (char) val;
			}
		}
		return "";
	}
	
	public String decrypt(LinkedList<String> in, int key){
		
		return "";
	}
}
