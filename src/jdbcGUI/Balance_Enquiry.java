package jdbcGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class Balance_Enquiry extends JFrame {

	private JPanel contentPane;
	private JTextField textField_CN;
	private JTextField textField_bal;
	private JPasswordField passwordField_pin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Balance_Enquiry frame = new Balance_Enquiry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 Connection conn =null;
 private JButton btnClear;
 private JButton btnExit;
	/**
	 * Create the frame.
	 */
	public Balance_Enquiry() {
		conn=sqlconnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ENTER CARD NUMBER :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(40, 46, 306, 58);
		contentPane.add(lblNewLabel);
		
		textField_CN = new JTextField();
		textField_CN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_CN.setBounds(449, 57, 179, 46);
		contentPane.add(textField_CN);
		textField_CN.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER PIN:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(40, 160, 155, 38);
		contentPane.add(lblNewLabel_1);
		
		textField_bal = new JTextField();
		textField_bal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_bal.setHorizontalAlignment(SwingConstants.CENTER);
		textField_bal.setBounds(230, 246, 249, 43);
		contentPane.add(textField_bal);
		textField_bal.setColumns(10);
		
		JButton btnNewButton = new JButton("CHECK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String str = "select * from atm where card_id= '"+textField_CN.getText()+"'" ;
					
					
					Statement stmt =conn.createStatement();
					ResultSet result=stmt.executeQuery(str);
					if(result.next()) {
						int p =result.getInt("pin");
						if(p==Integer.parseInt(passwordField_pin.getText())) {
							
							int bal =result.getInt("balance");
							
							textField_bal.setText(Integer.toString(bal));
						}
						else {
							JOptionPane.showMessageDialog(null,"Wrong Password Try Again..");
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Account not found");
					}
					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"please Enter valid number");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(51, 324, 155, 43);
		contentPane.add(btnNewButton);
		
		passwordField_pin = new JPasswordField();
		passwordField_pin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField_pin.setBounds(449, 152, 191, 46);
		contentPane.add(passwordField_pin);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField_CN.setText("");
				 textField_bal.setText("");
				passwordField_pin.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnClear.setBounds(292, 324, 155, 43);
		contentPane.add(btnClear);
		
		btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Transaction t=new Transaction();
				t.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnExit.setBounds(514, 324, 155, 43);
		contentPane.add(btnExit);
	}
}
