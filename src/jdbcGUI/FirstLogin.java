package jdbcGUI;
import java.sql.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FirstLogin extends JFrame {

	private JPanel contentPane;
	Connection conn=null;
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	
	//private JPasswordField passwordField;
	//private JTextField textFieldUN;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstLogin frame = new FirstLogin();
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
	public FirstLogin() {
		conn=sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("LOGIN");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		//btnNewButton_1.setBackground(new Color(0, 191, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
					 String query="select * from atm where card_id=? and pin=?";
					 PreparedStatement pst = conn.prepareStatement(query);
					 pst.setString(1,textFieldUN.getText());
					 pst.setString(2,passwordField .getText());
					 
					 ResultSet rs=pst.executeQuery();
					 int count=0;
					 while(rs.next()) {
						count=count+1; 
					 }
					 if(count==1) {
						 JOptionPane.showMessageDialog(null, "YOU HAVE LOGGED IN SUCCESSFULLY");
						 dispose();
						 Transaction al=new Transaction();
						 al.setVisible(true);
					 }
					 else if(count>1){
						 JOptionPane.showMessageDialog(null, "Duplicate username and password"); 
					 }
					 else {
						 JOptionPane.showMessageDialog(null, "User name and password is not correct Try Again....");
					 }
					 rs.close();
					 pst.close();
				 }catch(Exception e) {
					 JOptionPane.showMessageDialog(null, e);
					
				 }	
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_1.setBounds(232, 307, 197, 67);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("ENTER CARD NUMBER:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(62, 113, 258, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterPassword = new JLabel("ENTER PASSWORD:\r\n");
		lblEnterPassword.setForeground(new Color(255, 255, 255));
		lblEnterPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterPassword.setBounds(62, 190, 247, 28);
		contentPane.add(lblEnterPassword);
		
		textFieldUN = new JTextField();
		textFieldUN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldUN.setBounds(348, 118, 269, 36);
		contentPane.add(textFieldUN);
		textFieldUN.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(348, 195, 269, 36);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("USER LOGIN");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel_1.setBounds(110, 24, 458, 50);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
