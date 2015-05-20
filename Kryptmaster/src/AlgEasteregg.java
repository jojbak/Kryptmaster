import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Fun easter egg where the text is "encrypted" into 1337-5p34k.
 * @author rickard & jonathan
 * @version 2015-05-15
 *
 */
public class AlgEasteregg{

	HashMap<Character,String> alphabet;
	HashMap<String,Character> reverse;
	
	
	public LinkedList<String> encrypt(LinkedList<String> in) {
		LinkedList<String> output = new LinkedList<>();
		for(String str : in){
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<str.length();i++){
				String converted = alphabet.get(str.charAt(i));
				sb.append(converted);
			}
			output.add(sb.toString());
		}
		return output;
	}
	/**
	 * Generate the proper values for each letter
	 */
	private void generateAlphabet(){
		alphabet = new HashMap<Character,String>();
		alphabet.put('a', "4");
		alphabet.put('b', "13");
		alphabet.put('c', "(");
		alphabet.put('d', "{)");
		alphabet.put('e', "3");
		alphabet.put('f', "|=");
		alphabet.put('g', "6");
		alphabet.put('h', "|-|");
		alphabet.put('i', "|");
		alphabet.put('j', ".]");
		alphabet.put('k', "|<");
		alphabet.put('l', "1");
		alphabet.put('m', "|Y|");
		alphabet.put('n', "/\\/");
		alphabet.put('o', "0");
		alphabet.put('p', "|>");
		alphabet.put('q', "0,");
		alphabet.put('r', "|2");
		alphabet.put('s', "5");
		alphabet.put('t', "7");
		alphabet.put('u', "{_}");
		alphabet.put('v', "\\\\//"); //"\\//"
		alphabet.put('w', "\\\\v//"); //"\\v//"
		alphabet.put('x', "}{");
		alphabet.put('y', "'//");
		alphabet.put('z', "2");
		
	}
	
	/*public static void main(String[] args){
		generateAlphabet();
		LinkedList<String> input = new LinkedList<>();
		input.add("abc");
		input.add("def");
		input.add("ghi");
		input.add("jkl");
		input.add("mno");
		input.add("pqr");
		input.add("stu");
		input.add("vwx");
		input.add("yz");
		
		LinkedList<String> output = encrypt(input);
		
		for(String str : output){
			System.out.println(str);
		}
	}*/

}
