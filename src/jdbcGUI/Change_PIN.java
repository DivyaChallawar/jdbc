package jdbcGUI;
import java.sql.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Change_PIN extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Change_PIN frame = new Change_PIN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JPasswordField passwordField_current;
	private JPasswordField passwordField_new;
	private JButton btnChange_pin;
	private JLabel lblEnterCardNumber;
	private JTextField textField_cardid;
	private JButton btnChange_pin_1;
	private JButton btnChange_pin_2;
	/**
	 * Create the frame.
	 */
	public Change_PIN() {
		conn=sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 929, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ENTER CURRENT PASSWORD :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(35, 140, 382, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterNewPassword = new JLabel("ENTER NEW PASSWORD :");
		lblEnterNewPassword.setForeground(new Color(255, 255, 255));
		lblEnterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblEnterNewPassword.setBounds(35, 222, 333, 40);
		contentPane.add(lblEnterNewPassword);
		
		passwordField_current = new JPasswordField();
		passwordField_current.setFont(new Font("Tahoma", Font.PLAIN, 25));
		passwordField_current.setBounds(569, 143, 243, 40);
		contentPane.add(passwordField_current);
		
		passwordField_new = new JPasswordField();
		passwordField_new.setFont(new Font("Tahoma", Font.BOLD, 30));
		passwordField_new.setEchoChar('*');
		passwordField_new.setBounds(569, 225, 243, 40);
		contentPane.add(passwordField_new);
		
		btnChange_pin = new JButton("CHANGE PIN");
		btnChange_pin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String str = "select * from atm where card_id= '"+textField_cardid.getText()+"'" ;
					
					int new_pin;
					Statement stmt =conn.createStatement();
					ResultSet result=stmt.executeQuery(str);
					if(result.next()) {
						int p =result.getInt("pin");
						if(p==Integer.parseInt(passwordField_current.getText())) {
							new_pin=Integer.parseInt(passwordField_new.getText());
							String query = "update atm set pin=? where card_id= '"+textField_cardid.getText()+"'";
							PreparedStatement prep_stmt = conn.prepareStatement(query);
							prep_stmt.setInt(1, new_pin);
							int rows = prep_stmt.executeUpdate();
							if (rows > 0) {
								JOptionPane.showMessageDialog(null,"pin changed successfully!");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Password is wrong !!! Try Again....");
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Account not found !!! Try Again....");
					}
					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"please Enter valid number");
				}
			}
		});
		btnChange_pin.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnChange_pin.setBounds(61, 342, 258, 46);
		contentPane.add(btnChange_pin);
		
		lblEnterCardNumber = new JLabel("ENTER CARD NUMBER :");
		lblEnterCardNumber.setForeground(new Color(255, 255, 255));
		lblEnterCardNumber.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblEnterCardNumber.setBounds(35, 58, 366, 40);
		contentPane.add(lblEnterCardNumber);
		
		textField_cardid = new JTextField();
		textField_cardid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_cardid.setBounds(569, 53, 243, 34);
		contentPane.add(textField_cardid);
		textField_cardid.setColumns(10);
		
		btnChange_pin_1 = new JButton("CLEAR\r\n");
		btnChange_pin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_cardid.setText("");
				passwordField_current.setText("");
			    passwordField_new.setText("");
			}
		});
		btnChange_pin_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnChange_pin_1.setBounds(424, 342, 143, 46);
		contentPane.add(btnChange_pin_1);
		
		btnChange_pin_2 = new JButton("EXIT");
		btnChange_pin_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Transaction t=new Transaction();
				t.setVisible(true);
			}
		});
		btnChange_pin_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnChange_pin_2.setBounds(669, 342, 143, 46);
		contentPane.add(btnChange_pin_2);
	}

}
