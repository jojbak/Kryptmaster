import java.util.ArrayList;
public class TempPlayfair {
	
	public String cryptPair(char[][] matrix, String input){
		return "";
	}
	
	public ArrayList partition(String input){
		ArrayList<String> list = new ArrayList<>();
		char[] chars = input.toCharArray();
		int i = 0;
		boolean isEven = false;
		while(i<chars.length){
			if(i == chars.length-1 && i == chars.length-2){
				
			}
			if(i != chars.length-1){
				String s = Character.toString(chars[i]);
				String s2 = Character.toString(chars[i+1]);
				if(s.equals(s2)){
					//String temp = s2;
					s2 = "X";
					s = s + s2;
					list.add(s);
					i++;
				}else{
					s = s + s2;
					list.add(s);
					i = i+2;
				}
				
			}
		}
		return list;
	}

}
