
public class TempPlayfair {
	char[][] matrix;

	private String[] format(String input) {
		char[] chars = input.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == 'j')
				chars[i] = 'i';
		}

		String text = new String(chars);

		int len = text.length();
		for (int i = 0; i < len; i = i + 2) {
			if (text.charAt(i) == text.charAt(i + 1)) {
				text = text.substring(0, i + 1) + 'X' + text.substring(i + 1); // add X to separate two of the same char in a row
			}																	
		}																
				
		if (text.length() % 2 != 0)
			text = text + "X"; // add X if the last char is "alone"

		return partition(text);
	}

	private String[] partition(String input) { // kolla datastruktur
		int len = input.length() / 2;
		String[] pairs = new String[len];
		char[] chars = input.toCharArray();

		for (int i = 0; i < chars.length; i = i + 2) {
			String s1 = Character.toString(chars[i]);
			String s2 = Character.toString(chars[i + 1]);
			int j = i - 1;
			if (i == 0)
				j = 0;
			pairs[j] = s1 + s2;
		}

		return pairs;
	}

	/**
	 * This class describes a point in the cipher matrix
	 * 
	 * @author Jonathan
	 *
	 */
	private class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}

	public Point getCoord(char ch) {
		Point pt = new Point(0, 0);
		if (ch == 'j')
			ch = 'i';
		for (int i = 0; i < 5; i++) {
			for (int j = 0; i < 5; j++) {
				if (matrix[i][j] == ch) {
					pt = new Point(i, j);
				}
			}
		}
		return pt;
	}

	public String encrypt(String input) {
		String[] pairs = format(input);
		int length = pairs.length;
		char[] output = new char[length*2];
		for (int i = 0; i < length; i++) {
			char one = pairs[i].charAt(0);
			char two = pairs[i].charAt(1);
			int col1 = getCoord(one).getX();
			int row1 = getCoord(one).getY();
			int col2 = getCoord(two).getX();
			int row2 = getCoord(two).getY();

			if (col1 == col2) {
				if (row1 != 4)
					row1++;
				else
					row1 = 0;

				if (row2 != 4)
					row2++;
				else
					row2 = 0;
			} else if (row1 == row2) {
				if (col1 != 4)
					col1++;
				else
					col1 = 0;

				if (col2 != 4)
					col2++;
				else
					col2 = 0;
			}else{
				int temp = col1;
				col1 = col2;
				col2 = temp;
			}
			
			output[i] = matrix[col1][row1];
			output[i+1] = matrix[col2][row2];
		}
		String outString = new String(output);
		return outString;
	}

}
