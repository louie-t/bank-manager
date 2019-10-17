import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CreateAccount extends JFrame {

	private static final long serialVersionUID = 6778610575414893137L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldInitialBalance;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblInitialBalance;
	Connection dbConnection = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreateAccount() {
		dbConnection = SqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose(); 
				Login.main(null);
			}
		});
		btnGoBack.setBounds(220, 212, 100, 23);
		contentPane.add(btnGoBack);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				    String username = textFieldUsername.getText();
				    String password = String.valueOf(passwordField.getPassword());
				    String confirmPassword = String.valueOf(confirmPasswordField.getPassword());
				    System.out.println(password);
				    System.out.println(confirmPassword);
				    
				    String name = textFieldName.getText();
				    String surname = textFieldSurname.getText();
				    int integerInitialBalance = Integer.parseInt(textFieldInitialBalance.getText());
				    String initialBalance = Integer.toString(integerInitialBalance);
				    
				    
				    if (!confirmPassword.contentEquals(password)) {
				    	JOptionPane.showMessageDialog(null, "Passwords must be equal, try again.");
				    }
				    else if (username.contentEquals("") || password.contentEquals("") || confirmPassword.contentEquals("") || name.contentEquals("") 
				    		|| surname.contentEquals("") || initialBalance.contentEquals("")){
				    	JOptionPane.showMessageDialog(null, "Some fields are missing, try again.");	
				    }
				    else {
				    	String query = "INSERT INTO userdata (username, password, name, surname, balance)  \r\n" + 
				    			"VALUES (?, ?, ?, ?, ?);";
					    PreparedStatement pst =  dbConnection.prepareStatement(query);
					    pst.setString(1, username);
					    pst.setString(2, password);
					    pst.setString(3, name);
					    pst.setString(4, surname);
					    pst.setString(5, initialBalance);
					    
					    pst.execute();
					    pst.close();
					    JOptionPane.showMessageDialog(null, "Username created!");
				    }
				    
				}catch(Exception e){
				    JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnCreate.setBounds(90, 212, 89, 23);
		contentPane.add(btnCreate);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(220, 10, 100, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 40, 100, 20);
		contentPane.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(220, 70, 100, 20);
		contentPane.add(confirmPasswordField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(90, 15, 150, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(90, 45, 150, 14);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(90, 75, 150, 14);
		contentPane.add(lblConfirmPassword);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(220, 100, 100, 20);
		contentPane.add(textFieldName);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(220, 130, 100, 20);
		contentPane.add(textFieldSurname);
		
		textFieldInitialBalance = new JTextField();
		textFieldInitialBalance.setColumns(10);
		textFieldInitialBalance.setBounds(220, 160, 100, 20);
		contentPane.add(textFieldInitialBalance);
		
		lblName = new JLabel("Name");
		lblName.setBounds(90, 105, 150, 14);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("Surname");
		lblSurname.setBounds(90, 135, 150, 14);
		contentPane.add(lblSurname);
		
		lblInitialBalance = new JLabel("Initial Balance");
		lblInitialBalance.setBounds(90, 165, 150, 14);
		contentPane.add(lblInitialBalance);
	}
}
