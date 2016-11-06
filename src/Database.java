import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Database {
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
	
	public Database(String username, String password, String server)
	{
		dataSource = new MysqlDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setServerName(server);
	
	}
	
	public void queryDB(String query) throws SQLException {
		ResultSet resret;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT * FROM housekeeper.users");
		resret.next();
		
		System.out.println(resret.getString("userName"));
		resret.close();
		stmt.close();
		conn.close();
		
		
	}

}
