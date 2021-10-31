package jdbc;

import java.sql.*;

public class ConnectionProvider {
		private static Connection connection;
		
		public static Connection getConnection() throws SQLException {
			if (connection == null) {
				connection = DriverManager.getConnection("jdbc:sqlite:TierraMediaSQLite.db");
			}
			
			return connection;
		}

}
