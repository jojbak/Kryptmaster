import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;

import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import javax.swing.JTextArea;

/**
 * GUI for the Kryptmaster program
 * @author Rickard & Jonathan
 * @version 2015-05-12
 *
 */
public class GUI {

	private JFrame frame;
	private JTextField txtKey;
	Parser par = new Parser();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		 * Inneh�ller den progressbaren som eventuellt kommer att implementars
		 * Denna bar ska representera hur l�ng tid det tar att kryptera st�rre
		 * textfiler
		 */
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(280, 297, 208, 23);
		frame.getContentPane().add(progressBar);

		/*
		 * Textf�ltet som inneh�ller den nyckel som anv�ndare sj�lv matar in.
		 * Denna nyckel kan variera beroende p� vilken algoritm det �r och
		 * representerar ungef�r ett l�senord som g�r att krypteringen blir
		 * s�krare och personlig.
		 */
		txtKey = new JTextField();
		txtKey.setText("Nyckel");
		txtKey.setBounds(402, 206, 86, 20);
		frame.getContentPane().add(txtKey);
		txtKey.setColumns(10);

		/*
		 * F�ljande kodblock representerar den dropdown meny d�r anv�ndare ska
		 * v�lja vilken krypteringsalgoritm som ska v�ljas. Ska typ
		 * implementeras s� att den bara kallar p� en metod som returnerar n�n
		 * typ av lista d�r alla algortmer �r representerade.
		 */
		// alternativ p� algoritmer
		String[] algorithms = { "Caesar", "Playfair", "Enigma" };
		final JComboBox algorithmsDropdown = new JComboBox(algorithms);
		algorithmsDropdown.setToolTipText("V\u00E4lj Algoritm");
		algorithmsDropdown.setBounds(280, 206, 86, 20);
		frame.getContentPane().add(algorithmsDropdown);
		// JComboBox.getSelectedItem() kollar vilket alternativ som �r valt
		// returnerar ett object

		/*
		 * F�ljande kodblock inneh�ller information om textrutan(v�nster) som
		 * det �r t�nkt att man ska skriva in text som man vill f� krypterad
		 */
		final JTextArea txtrTextAttKryptera = new JTextArea();
		txtrTextAttKryptera.setText("Text att kryptera");
		txtrTextAttKryptera.setBounds(70, 77, 200, 50);
		frame.getContentPane().add(txtrTextAttKryptera);

		// JTextArea.getText() h�mtar texten i rutan, returnerar en string

		/*
		 * F�ljande kodblock inneh�ller information om textrutan(h�ger) som
		 * anv�ndaren skriver in text som ska bli dekrypterad
		 */
		final JTextArea txtrTextAttDekryptera = new JTextArea();
		txtrTextAttDekryptera.setText("Text att dekryptera");
		txtrTextAttDekryptera.setBounds(498, 77, 225, 55);
		frame.getContentPane().add(txtrTextAttDekryptera);

		/*
		 * F�ljande kodblock inneh�ller information om knappen "Kryptera", den
		 * ska anv�ndaren trycka p� n�r den vill kryptera den text som den
		 * skrivit in i textrutan till v�nster. Den krypterade texten kommer att
		 * visas i den andra textrutan(h�ger) ala google translate. Finns det
		 * ingen text i textrutan, ingen algoritm �r vald eller ingen nyckel �r
		 * vald, ska anv�ndaren uppm�rksammas p� detta.
		 */
		JButton btnButt = new JButton("Kryptera");
		btnButt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				boolean values = quickCryptionCheck(
						txtrTextAttKryptera.getText(), txtKey.getText(),
						algorithmsDropdown.getSelectedItem());
				System.out.println(values);
				

				if (values != false) {
					displayInTextArea(txtrTextAttDekryptera,
							par.parseEncrypt(txtrTextAttKryptera.getText(), txtKey.getText()));
				}
			}
		});
		btnButt.setBounds(280, 107, 89, 23);
		frame.getContentPane().add(btnButt);

		/*
		 * Efterf�ljande kodblock inneh�ller information om knappen
		 * "dekryptera", den skall tryckas n�r anv�ndaren vill dekryptera text
		 * som denna skrivit in i den h�gra textrutan. Den dekrypterade texten
		 * kommer att visas i den v�nstra textrutan likt google translate. Finns
		 * det ingen text i textrutan, ingen algoritm �r vald eller ingen nyckel
		 * �r vald, ska anv�ndaren uppm�rksammas p� detta.
		 */
		JButton btnButt_1 = new JButton("Dekryptera");
		btnButt_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				boolean values = quickCryptionCheck(
						txtrTextAttDekryptera.getText(), txtKey.getText(),
						algorithmsDropdown.getSelectedItem());

				if (values != false) {
					// TODO
					// kalla p� en metod som kirrar den dekrypterade texten
					displayInTextArea(txtrTextAttKryptera,
							par.parseDecrypt(txtrTextAttDekryptera.getText(), txtKey.getText()));
				}

			}
		});
		btnButt_1.setBounds(399, 107, 89, 23);
		frame.getContentPane().add(btnButt_1);

		/*
		 * F�ljande kodblock inneh�ller information om knappen
		 * "kryptera textfil". N�r man trycker ska den f�rst kolla om en
		 * algoritm samt en nyckel �r vald, sedan skall den skicka vidare till
		 * andra metoder
		 */
		JButton btnButt_2 = new JButton("Kryptera textfil");
		btnButt_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean values = fileCryptionCheck(txtKey.getText(),
						algorithmsDropdown.getSelectedItem());
				System.out.println(values);

			}
		});
		btnButt_2.setBounds(70, 297, 200, 23);
		frame.getContentPane().add(btnButt_2);

		/*
		 * Innh�ller information om knappen "dekryptera textfil". N�r den trycks
		 * skall det kolla om det finns en algoritm samt en nyckel vald, sedan
		 * skicka vidare till andra delar av programmet.
		 */
		JButton btnButt_3 = new JButton("Dekryptera textfil");
		btnButt_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean values = fileCryptionCheck(txtKey.getText(),
						algorithmsDropdown.getSelectedItem());

			}
		});
		btnButt_3.setBounds(498, 297, 225, 23);
		frame.getContentPane().add(btnButt_3);

	}

	/**
	 * Displays an error message in the form of a JOptionPane in the chooses
	 * JFrame frame
	 * 
	 * @param frame
	 *            JFrame to be displayed in
	 * @param error
	 *            Message to be broadcasted
	 */
	public void displayErrorMessage(JFrame frame, String error) {
		JOptionPane.showMessageDialog(frame, error);
	}

	/**
	 * Checks if the necessary parameters is provided to apply an cryption
	 * action
	 * 
	 * @param textBox
	 *            Text to be encrypted
	 * @param key
	 *            Key to be used in the encryption
	 * @param algorithm
	 *            Algorithm to be used
	 * @return false if fails to meet all conditions
	 */

	public boolean quickCryptionCheck(String textBox, String key,
			Object algorithm) {
		if (textBox.trim().length() == 0) {
			displayErrorMessage(frame,
					"You need to insert text to be decrypted");
			return false;
		}

		if (key.trim().length() == 0) {
			displayErrorMessage(frame, "You need to provide a key");
			return false;
		}

		if (algorithm == null) {
			displayErrorMessage(frame, "You need to choose an algorithm");
			return false;
		}
		return true;
	}

	/**
	 * Checks if the necessary parameters is provided to encrypt/decrypt a file
	 * 
	 * @param key
	 *            The algorithms key
	 * @param algorithm
	 *            The desired algorithms
	 * @return false if fails to meet the conditions
	 */

	public boolean fileCryptionCheck(String key, Object algorithm) {
		if (key.trim().length() == 0) {
			displayErrorMessage(frame, "You need to provide a key");
			return false;
		}

		if (algorithm == null) {
			displayErrorMessage(frame, "You need to choose an algorithm");
			return false;
		}
		return true;
	}

	/**
	 * Displays text in a specified textarea
	 * 
	 * @param area
	 *            TextArea to display the text in
	 * @param text
	 *            Text to be displayed
	 */
	public void displayInTextArea(JTextArea area, String text) {
		area.setText(text);
	}
}
