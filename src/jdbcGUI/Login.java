package jdbcGUI;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class Login {

	private JFrame frame;

	Connection conn=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
		conn=sqlconnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.setBounds(100, 100, 720, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("ADMIN");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setBackground(SystemColor.textHighlight);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frame.dispose();
				 AdminLogin al=new AdminLogin();
				 al.setVisible(true);
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 35));
		rdbtnNewRadioButton.setBounds(196, 166, 281, 74);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnUser = new JRadioButton("USER");
		rdbtnUser.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnUser.setForeground(Color.WHITE);
		rdbtnUser.setBackground(SystemColor.textHighlight);
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 frame.dispose();
				// Transaction al=new Transaction();
				 FirstLogin al=new FirstLogin();
				 al.setVisible(true);
				
			}
		});
		rdbtnUser.setFont(new Font("Tahoma", Font.BOLD, 35));
		rdbtnUser.setBounds(196, 291, 281, 74);
		frame.getContentPane().add(rdbtnUser);
		
		JLabel lblNewLabel = new JLabel("!!! WELCOME   TO   ATM !!!");
		lblNewLabel.setBackground(new Color(255, 239, 213));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(44, 31, 615, 63);
		frame.getContentPane().add(lblNewLabel);
	}
}
