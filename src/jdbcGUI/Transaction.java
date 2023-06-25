package jdbcGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Transaction extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaction frame = new Transaction();
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
	public Transaction() {
		conn=sqlconnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 946, 494);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_Deposit = new JButton("DEPOSIT");
		btn_Deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Deposit d = new Deposit();
				d.setVisible(true);
			}
		});
		btn_Deposit.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_Deposit.setBounds(69, 145, 322, 50);
		contentPane.add(btn_Deposit);
		
		JButton btn_withdraw = new JButton("WITHDRAWL");
		btn_withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Transaction t=new Transaction();
				dispose();
				Withdrawl d = new Withdrawl();
				d.setVisible(true);
			}
		});
		btn_withdraw.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_withdraw.setBounds(537, 145, 252, 50);
		contentPane.add(btn_withdraw);
		
		JButton btnTransfer = new JButton("TRANSFER");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Transfer_balance d = new Transfer_balance();
				d.setVisible(true);
			}
		});
		btnTransfer.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnTransfer.setBounds(79, 248, 312, 50);
		contentPane.add(btnTransfer);
		
		JButton btnChangePiin = new JButton("CHANGE PIN");
		dispose();
		btnChangePiin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Change_PIN d = new Change_PIN();
				d.setVisible(true);
			}
		});
		btnChangePiin.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnChangePiin.setBounds(537, 248, 252, 50);
		contentPane.add(btnChangePiin);
		
		JButton btnBalanceEnquiry = new JButton("BALANCE ENQUIRY");
		btnBalanceEnquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Balance_Enquiry d = new Balance_Enquiry ();
				d.setVisible(true);
			}
		});
		btnBalanceEnquiry.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnBalanceEnquiry.setBounds(79, 329, 312, 50);
		contentPane.add(btnBalanceEnquiry);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnExit.setBounds(537, 349, 252, 50);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("PLEASE SELECT YOUR TRANSACTION");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(154, 33, 583, 38);
		contentPane.add(lblNewLabel);
	}
}
