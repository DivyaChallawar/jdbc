package jdbcGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.awt.Color;
public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_UN;
	private JLabel lblLogin;
	private JButton btnNewButton;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection conn=null;
	/**
	 * Create the frame.
	 */
	public AdminLogin() {
		conn=sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 442);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_UN = new JTextField();
		textField_UN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_UN.setBounds(335, 98, 206, 56);
		contentPane.add(textField_UN);
		textField_UN.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ENTER ADMIN ID:");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(67, 108, 200, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterPassword = new JLabel("ENTER  PASSWORD:");
		lblEnterPassword.setForeground(new Color(240, 248, 255));
		lblEnterPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEnterPassword.setBounds(67, 205, 226, 46);
		contentPane.add(lblEnterPassword);
		
		lblLogin = new JLabel("ADMIN LOGIN");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblLogin.setBounds(193, 10, 226, 46);
		contentPane.add(lblLogin);
		
		btnNewButton = new JButton("LOGIN");
		//btnNewButton.setBackground(new Color(30, 144, 255));
		//btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
					 String query="select * from admin where id=? and password=?";
					 PreparedStatement pst = conn.prepareStatement(query);
					 pst.setString(1,textField_UN.getText());
					 pst.setString(2,passwordField .getText());
					 
					 ResultSet rs=pst.executeQuery();
					 int count=0;
					 while(rs.next()) {
						count=count+1; 
					 }
					 if(count==1) {
						 JOptionPane.showMessageDialog(null, "YOU HAVE LOGGED IN SUCCESSFULLY");
						 
						AfterLogin al=new AfterLogin();
						 al.setVisible(true);
					 }
					 else if(count>1){
						 JOptionPane.showMessageDialog(null, "Duplicate Password and Card ID"); 
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(244, 314, 129, 46);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(335, 205, 206, 46);
		contentPane.add(passwordField);
	}
}
