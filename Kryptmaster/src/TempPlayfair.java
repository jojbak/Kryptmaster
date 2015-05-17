import java.util.ArrayList;
public class TempPlayfair {
	
	public String cryptPair(char[][] matrix, String input){
		return "";
	}
	
	public void format(String input){
		char[] chars = input.toCharArray();
		for(int i=0; i<chars.length;i++){
			if(chars[i] == 'j')
				chars[i] = 'i';
		}
		
		String text = new String(chars);
		
		int len = text.length();
		for(int i=0; i<len;i=i+2){
			if(text.charAt(i) == text.charAt(i+1)){
				text = text.substring(0, i+1) + 'X' + text.substring(i+1);
			}
		}
		if(text.length() % 2 != 0)
			text = text + "X";
		
		partition(text);
	}
	
	public ArrayList partition(String input){
		ArrayList<String> list = new ArrayList<>();
		char[] chars = input.toCharArray();
		
		for(int i=0; i<chars.length;i=i+2){
			String s1 = Character.toString(chars[i]);
			String s2 = Character.toString(chars[i+1]);
			list.add(s1+s2);
		}
		
		return list;
	}

}
