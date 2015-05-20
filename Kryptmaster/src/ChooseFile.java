import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class implements JFileChooser enabling the 
 * user to select a textfile via a GUI.
 * @author Jonathan & Rickard
 *
 */
public class ChooseFile {
	File file;
	
	public ChooseFile(){
		initalize();
		
	}
	/**
	 * Display the GUI and take certain actions
	 * depending on user input.
	 */
	private void initalize(){
			
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Textfiler", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		//l�gg till if-sats som kollar att filen �r txt
		if(returnVal == JFileChooser.APPROVE_OPTION) { //l�gg till f�r "avbryt"
			file = chooser.getSelectedFile();
			String name = file.getName();
			if(!name.substring(name.length()-4).equals(".txt")){
				file = null; //skriv error meddelande
				JOptionPane.showMessageDialog(null, "Only .txt files are allowed!");
				return;
			}
		}else if(returnVal == JFileChooser.CANCEL_OPTION){
			return;
		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseFile window = new ChooseFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
