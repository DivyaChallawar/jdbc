package jdbcGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;//for rsxml2 jar file

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
public class AfterLogin extends JFrame {

	private JPanel contentPane;
	
	private JTable table;
	/**
	 * Launch the application.  
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfterLogin frame = new AfterLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTextField textFieldid;
	private JTextField textField_name;
	private JTextField textField_cardid;
	private JTextField textField_pin;
	private JTextField textField_bal;
	/**
	 * Create the frame.
	 */
	public AfterLogin() {
		conn=sqlconnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 683);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane =   new JScrollPane();
		scrollPane.setBounds(622, 145, 502, 342);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton Btnloaddata = new JButton("DISPLAY");
		Btnloaddata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 String query="select * from atm";
					 PreparedStatement pst = conn.prepareStatement(query);
					 ResultSet rs=pst.executeQuery();
					 table.setModel(DbUtils.resultSetToTableModel(rs));
					 
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
		Btnloaddata.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		Btnloaddata.setBounds(530, 552, 171, 56);
		contentPane.add(Btnloaddata);
		
		JLabel lblNewLabel = new JLabel("ENTER CUSTOMER NAME :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(42, 214, 293, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER  CARD NUMBER:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(42, 282, 250, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ENTER PIN NUMBER:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(44, 339, 259, 45);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ENTER THE BALANCE :");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(42, 394, 250, 56);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ENTER CUSTOMER ID:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(42, 145, 223, 53);
		contentPane.add(lblNewLabel_4);
		
		textFieldid = new JTextField();
		textFieldid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldid.setBounds(418, 156, 143, 31);
		contentPane.add(textFieldid);
		textFieldid.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_name.setBounds(418, 219, 143, 37);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_cardid = new JTextField();
		textField_cardid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_cardid.setBounds(418, 287, 143, 37);
		contentPane.add(textField_cardid);
		textField_cardid.setColumns(10);
		
		textField_pin = new JTextField();
		textField_pin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_pin.setBounds(418, 343, 143, 37);
		contentPane.add(textField_pin);
		textField_pin.setColumns(10);
		
		textField_bal = new JTextField();
		textField_bal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_bal.setBounds(416, 407, 145, 31);
		contentPane.add(textField_bal);
		textField_bal.setColumns(10);
		
		JButton btnNewButton_Save = new JButton("SAVE");
		//btnNewButton_Save.setBackground(Color.WHITE);
		btnNewButton_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 String query="insert into atm(id,name,card_id,pin,balance) values (?,?,?,?,?)";
					 PreparedStatement pst = conn.prepareStatement(query);
					
					pst.setString(1,textFieldid.getText() );
					pst.setString(2,textField_name.getText() );
					pst.setString(3,textField_cardid.getText() );
					pst.setString(4,textField_pin.getText() );
					pst.setString(5,textField_bal.getText() );
					 
					pst.execute();
					 JOptionPane.showMessageDialog(null,"CUSTOMER DETAILS  SAVED");
					pst.close();
					//rs.close();
					 
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"you have entered duplicate record...Enter another");
					e.printStackTrace();
				} 
			}
		});
		btnNewButton_Save.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_Save.setBounds(317, 552, 161, 56);
		contentPane.add(btnNewButton_Save);
		
		JLabel lblNewLabel_5 = new JLabel("ADD CUSTOMER DETAILS");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_5.setBounds(317, 21, 441, 67);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(963, 552, 161, 56);
		contentPane.add(btnNewButton);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldid.setText("");
			 textField_name.setText("");
				 textField_cardid.setText("");
				textField_pin.setText("");
				textField_bal.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnClear.setBounds(749, 552, 171, 56);
		contentPane.add(btnClear);
	}
}
