package jdbcGUI;
import java.sql.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Withdrawl extends JFrame {

	private JPanel contentPane;
	private JTextField textField_num;
	private JPasswordField textField_cardid;
	private JTextField textField_Ans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdrawl frame = new Withdrawl();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JButton btn_Withdraw_1;
	private JButton btn_Withdraw_2;
	/**
	 * Create the frame.
	 */
	public Withdrawl() {
		conn=sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ENTER AMOUNT :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(78, 59, 255, 46);
		contentPane.add(lblNewLabel);
		
		textField_num = new JTextField();
		textField_num.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_num.setBounds(459, 59, 220, 39);
		contentPane.add(textField_num);
		textField_num.setColumns(10);
		
		JButton btn_Withdraw = new JButton("WITHDRAWL");
		btn_Withdraw.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(null,"Sorry,Account not found!!! Try Again");
					}
					int k=0;
					do {
					num2=Integer.parseInt(textField_num.getText());
					
					if (num2 > bal) {
						JOptionPane.showMessageDialog(null,"Sorry,no sufficient balance in your account!!TRY Again..");
						k=1;
						break;
					} else if (bal- num2 < 500) {
						JOptionPane.showMessageDialog(null,"Sorry,minimum balance limit exceeded!!! TRY Again...");
						k=1;
						break;}
					else {
						k=0;
					ans=bal-num2;
					textField_Ans.setText(Integer.toString(ans));
					
					String query1="Update atm set balance='"+ans+"' where pin='"+textField_cardid.getText()+"'";
					 PreparedStatement pst = conn.prepareStatement(query1);
					
					
					 pst.execute();
					 JOptionPane.showMessageDialog(null,"Money Debited Successfully");
					}
					}while(k==1);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"please Enter valid number");
				}
			
			}
		});
		btn_Withdraw.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_Withdraw.setBounds(62, 310, 285, 68);
		contentPane.add(btn_Withdraw);
		
		JLabel lblPassword = new JLabel("PASSWORD:\r\n");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPassword.setBounds(78, 135, 162, 46);
		contentPane.add(lblPassword);
		
		textField_cardid = new JPasswordField();
		textField_cardid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_cardid.setBounds(459, 135, 220, 37);
		contentPane.add(textField_cardid);
		
		JLabel lblFinalBalance = new JLabel("YOUR BALANCE:");
		lblFinalBalance.setForeground(new Color(255, 255, 255));
		lblFinalBalance.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblFinalBalance.setBounds(78, 215, 255, 46);
		contentPane.add(lblFinalBalance);
		
		textField_Ans = new JTextField();
		textField_Ans.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_Ans.setColumns(10);
		textField_Ans.setBounds(459, 215, 220, 39);
		contentPane.add(textField_Ans);
		
		btn_Withdraw_1 = new JButton("EXIT");
		btn_Withdraw_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Transaction t=new Transaction();
				t.setVisible(true);
			}
		});
		btn_Withdraw_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_Withdraw_1.setBounds(592, 310, 177, 68);
		contentPane.add(btn_Withdraw_1);
		
		btn_Withdraw_2 = new JButton("CLEAR");
		btn_Withdraw_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_num.setText("");
				 textField_cardid.setText("");
				 textField_Ans.setText("");

			}
		});
		btn_Withdraw_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_Withdraw_2.setBounds(397, 310, 177, 68);
		contentPane.add(btn_Withdraw_2);
	}

}
