package jdbcGUI;
import java.sql.*;
import javax.swing.*;
public class sqlconnection {
	static final String DB_URL = "jdbc:mysql://localhost:3306/atm1?autoReconnect=true&useSSL=false";

	static final String USER = "root";
	static final String PASS = "divya@123";

	Connection conn=null;
 public static Connection dbConnector() {
	 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 //JOptionPane.showMessageDialog(null, "database connected successfully");
		Connection	conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 //JOptionPane.showMessageDialog(null, "database connected successfully");
		
		return conn;
	 }catch(Exception e) {
		 JOptionPane.showMessageDialog(null, e);
		 return null;
	 }
	//return conn;
 }
}
