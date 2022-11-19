import java.awt.BorderLayout;
import java.math.BigInteger;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Decryption extends JFrame {

	private JPanel contentPane;
	private JTextField c;
	private JTextField f;
	private JTextField k;
	private JTextField tfOut;
	static BigInteger r = new BigInteger("1");
    static BigInteger s = new BigInteger("0");
    static BigInteger q = new BigInteger("2");

	/**
	 * Launch the application.
	 */
    static BigInteger expo(BigInteger m,BigInteger e, BigInteger n){
        if(e.equals(s))
            return r;
        if(e.equals(r))
            return m.mod(n);
        BigInteger t = expo(m,e.divide(q),n);
        t = (t.multiply(t)).mod(n);
        if(e.mod(q).equals(s))
            return t;
        else
            return ((m.mod(n)).multiply(t)).mod(n);
    }

	static BigInteger extEuclid(BigInteger e, BigInteger pi, int val){
        BigInteger ePi = pi;
        BigInteger b = s;
        BigInteger c = r;
        BigInteger [][] arr = new BigInteger[10000][7];
        int i=0;
        while(true){
            arr[i][0] = pi.divide(e);
            arr[i][1] = pi;
            arr[i][2] = e;
            arr[i][3] = pi.mod(e);
            arr[i][4] = b;
            arr[i][5] = c;
            arr[i][6] = arr[i][4].subtract(arr[i][0].multiply(arr[i][5]));
            e = arr[i][3];
            pi = arr[i][2];
            b = arr[i][5];
            c = arr[i][6];
            if(arr[i][3].equals(s))
                break;
            i++;
        }
        if(val == 0){
            return b.mod(ePi); // Inverse
        }
        else{
            return pi; // GCD
        }
    }
	
	public Decryption() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Main Menu");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage home = new MainPage();
				home.setVisible(true);
				dispose();
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBackground(Color.BLUE);
		button.setBounds(12, 499, 176, 41);
		contentPane.add(button);
		
		JLabel lblTemperatureConversion = new JLabel("Decryption");
		lblTemperatureConversion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureConversion.setForeground(Color.WHITE);
		lblTemperatureConversion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTemperatureConversion.setBounds(12, 13, 458, 54);
		contentPane.add(lblTemperatureConversion);
		
		JButton button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_1.setBackground(Color.RED);
		button_1.setBounds(341, 499, 129, 41);
		contentPane.add(button_1);
		
		c = new JTextField();
		c.setForeground(new Color(25, 25, 112));
		c.setFont(new Font("Tahoma", Font.BOLD, 20));
		c.setHorizontalAlignment(SwingConstants.CENTER);
		c.setBounds(152, 94, 200, 50);
		contentPane.add(c);
		c.setColumns(10);
		
		JLabel lblc = new JLabel("e");
		lblc.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblc.setForeground(Color.WHITE);
		lblc.setBounds(110, 94, 59, 46);
		contentPane.add(lblc);
		
		f = new JTextField();
		f.setHorizontalAlignment(SwingConstants.CENTER);
		f.setForeground(new Color(25, 25, 112));
		f.setFont(new Font("Tahoma", Font.BOLD, 20));
		f.setColumns(10);
		f.setBounds(152, 170, 200, 50);
		contentPane.add(f);
		
		JLabel lblf = new JLabel("n");
		lblf.setForeground(Color.WHITE);
		lblf.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblf.setBounds(110, 170, 59, 46);
		contentPane.add(lblf);
		
		
		k = new JTextField();
		k.setHorizontalAlignment(SwingConstants.CENTER);
		k.setForeground(new Color(25, 25, 112));
		k.setFont(new Font("Tahoma", Font.BOLD, 20));
		k.setColumns(10);
		k.setBounds(152, 246, 200, 50);
		contentPane.add(k);
		
		JLabel lblk = new JLabel("CT");
		lblk.setForeground(Color.WHITE);
		lblk.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblk.setBounds(110, 246, 59, 46);
		contentPane.add(lblk);
		
		
		
		
		JButton btnC = new JButton("Convert");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				if(c.getText().isEmpty())
				{
					tfOut.setText("Please  Check your input");
				}
				
				else 
				{
					tfOut.setText(null);
					BigInteger d,n;
					String ct, ans="";
			        d = new BigInteger(c.getText());
			        n = new BigInteger(f.getText());
			        ct = k.getText();
			        int len = ct.length();
			        BigInteger arr[] = new BigInteger[len];
			        for(int i=0; i<ct.length(); i++){
			            BigInteger a = BigInteger.valueOf(ct.charAt(i));
			            arr[i] = a;
			        }
			        for(int i=0; i<len; i++){
			            ans += String.valueOf(Character.toChars(expo(arr[i],d,n).intValue()));
			        }
					tfOut.setText(ans);
				}
				
						
				
			}
		});
		btnC.setForeground(new Color(255, 255, 0));
		btnC.setBackground(new Color(25, 25, 112));
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnC.setBounds(190, 320, 100, 50);
		contentPane.add(btnC);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setText(null);
				k.setText(null);
				f.setText(null);
				tfOut.setText(null);
			}
		});
		clear.setForeground(Color.WHITE);
		clear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clear.setBackground(new Color(0, 100, 0));
		clear.setBounds(200, 499, 129, 41);
		contentPane.add(clear);
		
		tfOut = new JTextField();
		tfOut.setForeground(Color.RED);
		tfOut.setBackground(Color.BLACK);
		tfOut.setFont(new Font("Tahoma", Font.PLAIN, 19));
		tfOut.setBounds(48, 410, 370, 60);
		contentPane.add(tfOut);
		tfOut.setColumns(10);
	}
}