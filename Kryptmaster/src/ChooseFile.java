import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseFile {
	private JFrame frame;
	File filename;
	
	public ChooseFile(){
		initalize();
		
	}
	
	private void initalize(){
		//frame = new JFrame();
		//frame.setBounds(100, 100, 800, 600);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Textfiler", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null/*parent*/);
		//lägg till if-sats som kollar att filen är txt
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		  filename = chooser.getCurrentDirectory();
		  //return filename;
		}
		//return null;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseFile window = new ChooseFile();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
