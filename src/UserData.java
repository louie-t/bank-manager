

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class UserData extends JFrame {
	
	Connection dbConnection = null;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldAmount;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserData frame = new UserData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public UserData() {
		dbConnection = SqliteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblUsername = new JLabel("Hi " + Login.getCurrentUsername() +"!");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(0, 11, 434, 60);
		contentPane.add(lblUsername);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose(); 
				Login.main(null);
			}
		});
		btnLogout.setBounds(335, 227, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnGetBalance = new JButton("Get Balance");
		btnGetBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query ="SELECT * FROM UserData WHERE username = (?)";
					PreparedStatement pst = dbConnection.prepareStatement(query);
					pst.setString(1, Login.getCurrentUsername());
	
					ResultSet rs = pst.executeQuery();
					
					String balance = null;
					while (rs.next()) {
				        balance = rs.getString(6);
				    }
					
					JOptionPane.showMessageDialog(null, "Your current balance is $" + balance + ".");
					
					rs.close();
					pst.close();
							
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnGetBalance.setBounds(10, 128, 120, 23);
		contentPane.add(btnGetBalance);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query ="SELECT * FROM UserData WHERE username = (?)";
					PreparedStatement pst = dbConnection.prepareStatement(query);
					pst.setString(1, Login.getCurrentUsername());
	
					ResultSet rs = pst.executeQuery();
					
					String balance = null;
					while (rs.next()) {
				        balance = rs.getString(6);
				    }
					
					rs.close();
					pst.close();
					
					int intAmount = Integer.parseInt(textFieldAmount.getText());
					int intBalance = Integer.parseInt(balance);
					
					if (intBalance < intAmount) {
						intAmount = 0;
						JOptionPane.showMessageDialog(null, "The amount you withdrawn is greater than you current balance. Try again.");
					}
					else {
						intBalance -= intAmount;
						
						balance = Integer.toString(intBalance);
						String amount = Integer.toString(intAmount);
						
						String query2 ="UPDATE UserData SET balance = (?) WHERE username = (?)";
						PreparedStatement pst2 = dbConnection.prepareStatement(query2);
						pst2.setString(1, balance);
						pst2.setString(2, Login.getCurrentUsername());
						pst2.execute();
						
						JOptionPane.showMessageDialog(null, "The amount you withdrawn is $" + amount + ".\nYour new balance is $" + balance + ".");
						pst2.close();
					}		
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnWithdraw.setBounds(10, 60, 120, 23);
		contentPane.add(btnWithdraw);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query ="SELECT * FROM UserData WHERE username = (?)";
					PreparedStatement pst = dbConnection.prepareStatement(query);
					pst.setString(1, Login.getCurrentUsername());
	
					ResultSet rs = pst.executeQuery();
					
					String balance = null;
					while (rs.next()) {
				        balance = rs.getString(6);
				    }
					
					rs.close();
					pst.close();
					
					int intAmount = Integer.parseInt(textFieldAmount.getText());
					int intBalance = Integer.parseInt(balance);
					intBalance += intAmount;
					balance = Integer.toString(intBalance);
					String amount = Integer.toString(intAmount);
					
					String query2 ="UPDATE UserData SET balance = (?) WHERE username = (?)";
					PreparedStatement pst2 = dbConnection.prepareStatement(query2);
					pst2.setString(1, balance);
					pst2.setString(2, Login.getCurrentUsername());
					pst2.execute();
					
					JOptionPane.showMessageDialog(null, "The amount you deposited is $" + amount + ".\nYour new balance is $" + balance + ".");
					pst2.close();
							
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDeposit.setBounds(10, 94, 120, 23);
		contentPane.add(btnDeposit);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(147, 160, 96, 20);
		contentPane.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setBounds(10, 162, 120, 17);
		contentPane.add(lblAmount);
		
		
	}
}
