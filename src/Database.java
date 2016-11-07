import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Database {
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
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

		resret.getString("salt");
		resret.getString("pass");
		resret.close();
		stmt.close();
		conn.close();

	}
	
	public String[] getHouses(String user) throws SQLException {
		ResultSet resret;
		String[] houses;
		int count = 0;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT count(*) AS count FROM `"+user+"_HOUSES");
		resret.first();
		houses = new String[Integer.parseInt(resret.getString("count"))];
		resret = stmt.executeQuery("SELECT * FROM `"+user+"_HOUSES");


		while (resret.next())
		{	
			houses[count++] = resret.getString("myHouses");
		}
		
		
		resret.close();
		stmt.close();
		conn.close();
		return houses;

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
			stmt.executeUpdate(q);
			q = "CREATE TABLE `housekeeper`.`"+ uname.trim() + "_HOUSES` ( `myHouses` VARCHAR(100) NOT NULL ) ENGINE = InnoDB";
			stmt.executeUpdate(q);
			
			returnValue = true;
		}

		stmt.close();
		conn.close();
		return returnValue;

	}
	
	public boolean addHouse(String uname, String houseName) throws SQLException {
		boolean returnValue;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		
		
		String q = "INSERT INTO `houses` (`houseID`, `houseName`, `houseOwner`) VALUES (NULL, '"+houseName.trim() + "_HOUSE', '"+uname+"');";
			stmt.executeUpdate(q);
	   q = "INSERT INTO `"+uname+"_houses` (`myHouses`) VALUES ('"+houseName+"')";
	   stmt.executeUpdate(q);
			returnValue = true;

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
		if(resret.next()) {
		ret[0] = resret.getString("salt");
		ret[1] = resret.getString("pass");
		}
		resret.close();
		stmt.close();
		conn.close();
		return ret;
	}
	
	

}
