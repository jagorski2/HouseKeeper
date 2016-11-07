import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Database {
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
	private String salt;
	private String pw;

	public Database(String username, String password, String server) {
		dataSource = new MysqlDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setServerName(server);
		dataSource.setDatabaseName("housekeeper");

	}

	public void queryDB(String query) throws SQLException {
		ResultSet resret;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery(query);

		resret.next();

		this.salt = resret.getString("salt");
		this.pw = resret.getString("pass");
		resret.close();
		stmt.close();
		conn.close();

	}

	public boolean addUser(String uname, String pass) throws SQLException {
		boolean returnValue;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		String[] saltPW = new String[2];
		ResultSet qresult = stmt.executeQuery("SELECT * from `users` WHERE userName like '"+uname+"'");
		
		if (qresult.first())
		{

			returnValue = false;
		}
		else
		{
			
			saltPW = Crypto.hashPW(pass);
			String q = "INSERT INTO `users` (`userID`, `userName`, `pass`, `salt`) VALUES (NULL, '" + uname + "', '"
					+ saltPW[1] + "', '" + saltPW[0] + "');";
			System.out.println(q);
			stmt.executeUpdate(q);
			returnValue = true;
		}

		stmt.close();
		conn.close();
		return returnValue;

	}

	public String[] getPassAndSalt(String Username) throws SQLException {
		String[] ret = new String[2];
		String q = "SELECT pass,salt FROM `users` WHERE userName LIKE '" + Username + "'";
		ResultSet resret;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery(q);
		resret.next();
		ret[0] = resret.getString("salt");
		ret[1] = resret.getString("pass");
		resret.close();
		stmt.close();
		conn.close();
		return ret;
	}

}
