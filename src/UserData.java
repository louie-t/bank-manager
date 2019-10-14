

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
					String query ="Select * from UserData where username = (?)";
					PreparedStatement pst = dbConnection.prepareStatement(query);
					pst.setString(1, Login.currentUsername);
	
					ResultSet rs = pst.executeQuery();
					
					String balance = null;
					while (rs.next()) {
				        balance = rs.getString(6);
				    }
					
					JOptionPane.showMessageDialog(null, "Your current balance is " + balance);
					
					rs.close();
					pst.close();
							
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnGetBalance.setBounds(10, 128, 100, 23);
		contentPane.add(btnGetBalance);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(10, 60, 100, 23);
		contentPane.add(btnWithdraw);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(10, 94, 100, 23);
		contentPane.add(btnDeposit);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(122, 160, 96, 20);
		contentPane.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(47, 163, 48, 14);
		contentPane.add(lblAmount);
	}
}
