package jdbcGUI;
import java.sql.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class Deposit extends JFrame {
	private JTextField textField_num;
	private JTextField textField_Ans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposit frame = new Deposit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JPasswordField textField_cardid;
	private JLabel lblPassword;
	private JButton btn_Deposit_1;
	private JButton btn_Deposit_2;
	
	/**
	 * Create the frame.
	 */
	public Deposit() {
		getContentPane().setBackground(new Color(25, 25, 112));
		conn=sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 500);
		getContentPane().setLayout(null);
		
		textField_num = new JTextField();
		textField_num.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_num.setBounds(384, 81, 259, 42);
		getContentPane().add(textField_num);
		textField_num.setColumns(10);
		
		JLabel enter = new JLabel("ENTER AMOUNT:");
		enter.setForeground(new Color(255, 255, 255));
		enter.setFont(new Font("Tahoma", Font.BOLD, 25));
		enter.setBounds(50, 69, 247, 64);
		getContentPane().add(enter);
		
		JButton btn_Deposit = new JButton("DEPOSIT");
		btn_Deposit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int num;
				int ans;
				int num2,bal=0;
				try {
					String num1="select balance from atm where pin= '"+textField_cardid.getText()+"'"; 
					Statement stmt =conn.createStatement();
					ResultSet result=stmt.executeQuery(num1);
					if(result.next()) {
						bal=result.getInt("balance");
					}
					else {
						JOptionPane.showMessageDialog(null,"Account not found !!! Try Again...");
					}
					
					num2=Integer.parseInt(textField_num.getText());
					
					ans=bal+num2;
					textField_Ans.setText(Integer.toString(ans));
					
					String query1="Update atm set balance='"+ans+"' where pin='"+textField_cardid.getText()+"'";
					 PreparedStatement pst = conn.prepareStatement(query1);
					
					
					 pst.execute();
					 JOptionPane.showMessageDialog(null,"MONEY CREDITED SUCCESSFULLY");
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"please Enter valid number");
				}
			}
		});
		btn_Deposit.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_Deposit.setBounds(72, 367, 205, 49);
		getContentPane().add(btn_Deposit);
		
		textField_Ans = new JTextField();
		textField_Ans.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_Ans.setColumns(10);
		textField_Ans.setBounds(384, 229, 259, 42);
		getContentPane().add(textField_Ans);
		
		JLabel lblFinalBalance = new JLabel("YOUR BALANCE:");
		lblFinalBalance.setForeground(new Color(255, 255, 255));
		lblFinalBalance.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblFinalBalance.setBounds(50, 217, 247, 64);
		getContentPane().add(lblFinalBalance);
		
		textField_cardid = new JPasswordField();
		textField_cardid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_cardid.setBounds(384, 155, 259, 42);
		getContentPane().add(textField_cardid);
		
		lblPassword = new JLabel("RE-TYPE PIN:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPassword.setBounds(50, 143, 247, 64);
		getContentPane().add(lblPassword);
		
		btn_Deposit_1 = new JButton("CLEAR");
		btn_Deposit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_cardid.setText("");
				textField_num.setText("");
			 textField_Ans.setText("");

				
			}
		});
		btn_Deposit_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_Deposit_1.setBounds(316, 367, 205, 49);
		getContentPane().add(btn_Deposit_1);
		
		btn_Deposit_2 = new JButton("EXIT");
	//	btn_Deposit_2.setBackground(SystemColor.textHighlight);
		btn_Deposit_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Transaction t=new Transaction();
				t.setVisible(true);
			}
		});
		btn_Deposit_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_Deposit_2.setBounds(555, 367, 205, 49);
		getContentPane().add(btn_Deposit_2);
		
	}
}
