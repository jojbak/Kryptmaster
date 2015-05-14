import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class implements JFileChooser enabling the 
 * user to select a textfile via a GUI.
 * @author Mr.Hax
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
		//lï¿½gg till if-sats som kollar att filen ï¿½r txt
		if(returnVal == JFileChooser.APPROVE_OPTION) { //lägg till för "avbryt"
		  file = chooser.getSelectedFile();
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
