import java.sql.*;

import javax.swing.*;

public class SqliteConnection {
	
	Connection dbConnection = null;
	
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			String path = "userdata.db";
			Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:" + path);
			// JOptionPane.showMessageDialog(null,  "Connection Successful");
			return dbConnection;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
