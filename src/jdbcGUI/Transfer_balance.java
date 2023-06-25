package jdbcGUI;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JPasswordField;
import java.awt.Color;
public class Transfer_balance extends JFrame {

	private JPanel contentPane;
	private JTextField textField_UC;
	private JTextField textField_RC;
	private JTextField textField_Amu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Transfer_balance frame = new Transfer_balance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection conn=null;
private JButton btnClear;
private JButton btnExit;
private JPasswordField textField_pin;
	/**
	 * Create the frame.
	 */
	public Transfer_balance() {
		conn=sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 534);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ENTER YOUR CARD NUMBER:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(55, 87, 346, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterReceiverCard = new JLabel("ENTER RECEIVER  CARD NUMBER:");
		lblEnterReceiverCard.setForeground(new Color(255, 255, 255));
		lblEnterReceiverCard.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEnterReceiverCard.setBounds(55, 173, 346, 53);
		contentPane.add(lblEnterReceiverCard);
		
		JLabel lblEnterAmount = new JLabel("ENTER AMOUNT");
		lblEnterAmount.setForeground(new Color(255, 255, 255));
		lblEnterAmount.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEnterAmount.setBounds(55, 236, 346, 53);
		contentPane.add(lblEnterAmount);
		
		JLabel lblReenterPassword = new JLabel("RE-ENTER PASSWORD:");
		lblReenterPassword.setForeground(new Color(255, 255, 255));
		lblReenterPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReenterPassword.setBounds(55, 309, 346, 53);
		contentPane.add(lblReenterPassword);
		
		textField_UC = new JTextField();
		textField_UC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_UC.setBounds(502, 87, 210, 40);
		contentPane.add(textField_UC);
		textField_UC.setColumns(10);
		
		textField_RC = new JTextField();
		textField_RC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_RC.setColumns(10);
		textField_RC.setBounds(502, 184, 210, 40);
		contentPane.add(textField_RC);
		
		textField_Amu = new JTextField();
		textField_Amu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_Amu.setColumns(10);
		textField_Amu.setBounds(502, 257, 210, 40);
		contentPane.add(textField_Amu);
		
		JButton btnNewButton = new JButton("TRANSFER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String str = "select * from atm where card_id= '"+ textField_UC.getText()+"'" ;
					
					
					Statement stmt =conn.createStatement();
					ResultSet result=stmt.executeQuery(str);
					if(result.next()) {
						int bal1 =result.getInt("balance");
						int p =result.getInt("pin");
						int bal2=0;
						if(p==Integer.parseInt(textField_pin.getText())) {
							String str1 = "select * from atm where card_id= '"+ textField_RC.getText()+"'";
							stmt =conn.createStatement();
							result=stmt.executeQuery(str1);
							if(result.next()) {
								bal2 =result.getInt("balance");
							}
							int amt;
						 amt=Integer.parseInt(textField_Amu.getText());
							int k=0;
						    do {
						    	if(amt>bal1) {
						    		JOptionPane.showMessageDialog(null,"no sufficient balance in your account!");
						    		k=1;
						    		break;
						    	}
						    	else if(bal1-amt<500) {
						    		JOptionPane.showMessageDialog(null,"minimum balance limit exceeded");
						    		k=1;
						    		break;
						    	}
						    	else if(amt==0) {
						    		JOptionPane.showMessageDialog(null,"Enter Valid Amount");
						    	}
						    	else {
						    		bal2=bal2+amt;
						    		bal1=bal1-amt;
						    		k=0;
						    		
						    		String query = "update atm set balance=? where card_id= '"+ textField_UC.getText()+"'"  ;
									PreparedStatement prep_stmt = conn.prepareStatement(query);
									prep_stmt.setInt(1, bal1);
									int rows = prep_stmt.executeUpdate();

									String query1 = "update atm set balance=? where card_id= '"+ textField_RC.getText()+"'" ;
									prep_stmt = conn.prepareStatement(query1);
									prep_stmt.setInt(1, bal2);
									int rows1 = prep_stmt.executeUpdate();

									if (rows > 0 && rows1 > 0) {
										JOptionPane.showMessageDialog(null,"Amount "+""+ textField_Amu.getText() + " "+"transferred successfully!");
									}
						    	}
						    	amt=Integer.parseInt(textField_Amu.getText());
						    }while(k==1);
								
							
						}
						else {
						JOptionPane.showMessageDialog(null,"INCORRECT PASSWORD..TRY AGAIN");
					}
					}
					else {
						JOptionPane.showMessageDialog(null,"Account not found");
					}
					
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"please Enter valid number");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(115, 395, 200, 53);
		contentPane.add(btnNewButton);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 textField_UC.setText("");
				 textField_RC.setText("");
					 textField_Amu.setText("");

				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnClear.setBounds(385, 395, 156, 53);
		contentPane.add(btnClear);
		
		btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_UC.setText("");
				 textField_RC.setText("");
				 textField_Amu.setText("");
				 textField_pin.setText("");
				dispose();
				Transaction t=new Transaction();
				t.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnExit.setBounds(601, 395, 167, 53);
		contentPane.add(btnExit);
		
		textField_pin = new JPasswordField();
		textField_pin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_pin.setBounds(502, 319, 210, 40);
		contentPane.add(textField_pin);
	}
}
