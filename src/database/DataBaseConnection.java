
package database;

import java.sql.*;
import java.util.ArrayList;


public class DataBaseConnection {

	static Connection connection = null;
	static Statement statement; 
	static PreparedStatement preparedStatement;
	static String url;
	static String databaseName;
	static String username;
	static String password;
	static int port;
	static String host_IP;
	static String host_DB;
  
  public static void initialiser() {
	  DataBaseConnection.host_IP = ParameterDatabase.HOST_IP;    
	  DataBaseConnection.host_DB = ParameterDatabase.HOST_DB;
	  DataBaseConnection.port = ParameterDatabase.PORT_DB;	
	  DataBaseConnection.databaseName = ParameterDatabase.DATABASE_NAME;
	  DataBaseConnection.username = ParameterDatabase.USERNAME_DB;
	  DataBaseConnection.password = ParameterDatabase.PASSWORD_DB;	  
    }

 public static  Connection connecter() throws ClassNotFoundException, SQLException{      
	
	  	initialiser();
	 	Class.forName("com.mysql.jdbc.Driver");  
	  	url = "jdbc:" + host_DB + "://" + host_IP + ":" + port + "/" + databaseName;
	  	connection = DriverManager.getConnection(url, username, password);
	  	return connection;
 }
 
   public static Connection deconnecter() throws SQLException {	        
        connection.close(); 
        return connection;
    }

    public static ResultSet executionQuery(String sql) throws SQLException {
    	try {
			connecter();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ResultSet resultSet = null;        
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public static int executionUpdate(String sql) throws SQLException {             
        try {
			connection=connecter();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }
    
}

