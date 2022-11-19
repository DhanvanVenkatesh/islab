import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(UIManager.getBorder("Button.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToConvert = new JLabel("Welcome to RSA");
		lblWelcomeToConvert.setForeground(Color.WHITE);
		lblWelcomeToConvert.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToConvert.setFont(new Font("Candara", Font.BOLD, 30));
		lblWelcomeToConvert.setBounds(12, 13, 441, 79);
		contentPane.add(lblWelcomeToConvert);
		
		JButton btnT = new JButton("Key Generator");
		btnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyGenerator temp = new KeyGenerator();
				temp.setVisible(true);
				dispose();
			}
		});
		btnT.setBackground(Color.BLUE);
		btnT.setForeground(Color.WHITE);
		btnT.setIcon(null);
		btnT.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 20));
		btnT.setBounds(100, 100, 250, 50);
		contentPane.add(btnT);
		
		JButton btnT1 = new JButton("Encryption");
		btnT1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Encryption temp = new Encryption();
				temp.setVisible(true);
				dispose();
			}
		});
		btnT1.setBackground(Color.BLUE);
		btnT1.setForeground(Color.WHITE);
		btnT1.setIcon(null);
		btnT1.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 20));
		btnT1.setBounds(100, 200, 250, 50);
		contentPane.add(btnT1);
		
		JButton btnT2 = new JButton("Decryption");
		btnT2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decryption temp = new Decryption();
				temp.setVisible(true);
				dispose();
			}
		});
		
		btnT2.setBackground(Color.BLUE);
		btnT2.setForeground(Color.WHITE);
		btnT2.setIcon(null);
		btnT2.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 20));
		btnT2.setBounds(100, 300, 250, 50);
		contentPane.add(btnT2);
		
		
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBackground(Color.RED);
		button.setBounds(318,507, 135, 33);
		contentPane.add(button);
		
	}
}