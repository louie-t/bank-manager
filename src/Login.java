import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {
	
	Connection dbConnection = null;

	private JFrame frame;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	
	private static String currentUsername;
	
	public static String getCurrentUsername() {
		return Login.currentUsername;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
		initialize();
		dbConnection =  SqliteConnection.dbConnector();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(108, 84, 64, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(108, 146, 64, 14);
		frame.getContentPane().add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(194, 81, 96, 20);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(194, 143, 96, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				    String query = "SELECT * FROM UserData WHERE username=? AND password=?";
				    PreparedStatement pst =  dbConnection.prepareStatement(query);
				    currentUsername = textFieldUsername.getText();
				    pst.setString(1, currentUsername);
				    pst.setString(2, String.valueOf(passwordField.getPassword()));
				    
				    ResultSet rs=pst.executeQuery();
				    int count = 0;
				    while(rs.next()){
				        count = count + 1;
				    }
				    if(count == 1){ 	
				        JOptionPane.showMessageDialog(null, "Username and password is correct.");
				        frame.dispose();
				        UserData userData = new UserData();
				        userData.setVisible(true);
				    }
				    else if (count > 1){
				        JOptionPane.showMessageDialog(null, "Duplicate Username and password.");
				    }
				    else{
				        JOptionPane.showMessageDialog(null, "Username or password is incorrect. Try Again...");
				    }
				    
				    rs.close();
				    pst.close();
				}catch(Exception e){
				    JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnLogin.setBounds(194, 191, 96, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblBanking = new JLabel("Banking");
		lblBanking.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanking.setBounds(108, 29, 182, 29);
		frame.getContentPane().add(lblBanking);
	}
}
