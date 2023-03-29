package db;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionManager {
	private static final String URL = "jdbc:mysql://localhost:3306/pizzeria2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "12345";

	private static Connection connection;

	public static Connection getConnection() {

		try {
			if (connection == null || connection.isClosed()) {
				Context ctx = null;
				ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
				connection = ds.getConnection();
//				Class.forName(DRIVER);
//				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

}
