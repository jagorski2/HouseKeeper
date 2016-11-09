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

	public String[] getMyHouses(String user) throws SQLException {
		ResultSet resret;
		String[] houses;
		int count = 0;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT count(*) AS count FROM `housemembers` WHERE `member` LIKE '" + user + "'");
		
		resret.first();
		houses = new String[Integer.parseInt(resret.getString("count"))];
		resret = stmt.executeQuery("SELECT `house` FROM `housemembers` WHERE `member` LIKE '" + user + "'");

		while (resret.next()) {
			houses[count++] = resret.getString("house");
		}

		resret.close();
		stmt.close();
		conn.close();
		return houses;

	}
	
	public String[] getAllHouses() throws SQLException {
		ResultSet resret;
		String[] houses;
		int count = 0;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT count(houseID) AS count FROM `" + "houses");
		resret.first();
		houses = new String[Integer.parseInt(resret.getString("count"))];
		resret = stmt.executeQuery("SELECT `houseName` FROM `houses`");

		while (resret.next()) {
			houses[count++] = resret.getString("houseName");
		}

		resret.close();
		stmt.close();
		conn.close();
		return houses;

	}
	
	public int getNumberOfAllChores(String selectedHouse) throws SQLException {
		ResultSet resret;
		int numOfChores = 0;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT count(choreID) AS count FROM `chores` WHERE house='" + selectedHouse+"'");
		resret.first();
		numOfChores = resret.getInt("count");

		resret.close();
		stmt.close();
		conn.close();
		return numOfChores;

	}
	
	public int getNumberOfMyChores(String selectedHouse, String userName) throws SQLException {
		ResultSet resret;
		int numOfChores = 0;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT count(choreID) AS count FROM `chores` WHERE house like '" + selectedHouse+"' AND assignedUser='"+userName+"'");
		resret.first();
		numOfChores = resret.getInt("count");

		resret.close();
		stmt.close();
		conn.close();
		return numOfChores;

	}
	
	public int getNumberOfMyChoresForToday(String selectedHouse, String userName, String today) throws SQLException {
		ResultSet resret;
		int numOfChores = 0;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT count(choreID) AS count FROM `chores` WHERE house like '" + selectedHouse+"' AND assignedUser='"+userName+"' AND "+today+"=1");
		resret.first();
		numOfChores = resret.getInt("count");

		resret.close();
		stmt.close();
		conn.close();
		return numOfChores;

	}
	
	
	public Chore[] getAllChores(String selectedHouse) throws SQLException {
		ResultSet resret;
		int numChores = getNumberOfAllChores(selectedHouse);
		int currentChore = 0;
		Chore[] chores = new Chore[numChores];
		for (int i = 0; i < numChores; i++)
		{
			chores[i] = new Chore();
		}
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT *  FROM `chores` WHERE `house` like '" + selectedHouse+"'");
		
		while (resret.next()) {
			chores[currentChore].setDuration(resret.getInt("duration"));
			chores[currentChore].setComplete(resret.getInt("complete"));
			chores[currentChore].setDescription(resret.getString("description"));
			chores[currentChore].setAssignedUser(resret.getString("assignedUser"));
			chores[currentChore].setHouse(resret.getString("house"));
			chores[currentChore].setMonday(resret.getInt("monday"));
			chores[currentChore].setTuesday(resret.getInt("tuesday"));
			chores[currentChore].setWednesday(resret.getInt("wednesday"));
			chores[currentChore].setThursday(resret.getInt("thursday"));
			chores[currentChore].setFriday(resret.getInt("friday"));
			chores[currentChore].setSaturday(resret.getInt("saturday"));
			chores[currentChore].setSunday(resret.getInt("sunday"));
			currentChore++;
		}

		resret.close();
		stmt.close();
		conn.close();
		return chores;

	}
	
	public Chore[] getMyChores(String selectedHouse, String userName) throws SQLException {
		ResultSet resret;
		int numChores = getNumberOfMyChores(selectedHouse, userName);
		int currentChore = 0;
		Chore[] chores = new Chore[numChores];
		for (int i = 0; i < numChores; i++)
		{
			chores[i] = new Chore();
		}
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT *  FROM `chores` WHERE house='" + selectedHouse+"' AND assignedUser='"+userName+"'");
		
		while (resret.next()) {
			chores[currentChore].setDuration(resret.getInt("duration"));
			chores[currentChore].setComplete(resret.getInt("complete"));
			chores[currentChore].setDescription(resret.getString("description"));
			chores[currentChore].setAssignedUser(resret.getString("assignedUser"));
			chores[currentChore].setHouse(resret.getString("house"));
			chores[currentChore].setMonday(resret.getInt("monday"));
			chores[currentChore].setTuesday(resret.getInt("tuesday"));
			chores[currentChore].setWednesday(resret.getInt("wednesday"));
			chores[currentChore].setThursday(resret.getInt("thursday"));
			chores[currentChore].setFriday(resret.getInt("friday"));
			chores[currentChore].setSaturday(resret.getInt("saturday"));
			chores[currentChore].setSunday(resret.getInt("sunday"));
			currentChore++;
		}

		resret.close();
		stmt.close();
		conn.close();
		return chores;

	}

	public boolean addUser(String uname, String pass) throws SQLException {
		boolean returnValue;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		String[] saltPW = new String[2];
		ResultSet qresult = stmt.executeQuery("SELECT * from `users` WHERE userName like '" + uname + "'");

		if (qresult.first()) {

			returnValue = false;
		} else {

			saltPW = Crypto.hashPW(pass);
			String q = "INSERT INTO `users` (`userID`, `userName`, `pass`, `salt`) VALUES (NULL, '" + uname + "', '"
					+ saltPW[1] + "', '" + saltPW[0] + "');";
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

		String q = "INSERT INTO `houses` (`houseID`, `houseName`, `houseOwner`) VALUES (NULL, '" + houseName.trim()
				+ "', '" + uname + "');";
		stmt.executeUpdate(q);
		q = "INSERT INTO `housemembers` (`member`, `house`) VALUES ('"+uname+"', '" + houseName.trim()
		+ "');";
		stmt.executeUpdate(q);
		returnValue = true;

		stmt.close();
		conn.close();
		return returnValue;

	}
	
	public boolean joinHouse(String uname, String houseName) throws SQLException {
		boolean returnValue;
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();

		String q = "INSERT INTO `housemembers` (`member`,`house`) VALUES ('"+uname+"','"+houseName +"')";
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
		if (resret.next()) {
			ret[0] = resret.getString("salt");
			ret[1] = resret.getString("pass");
		}
		resret.close();
		stmt.close();
		conn.close();
		return ret;
	}

	public void addChore(Chore mychore) throws SQLException {
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();

		String q = "INSERT INTO `chores` (`duration`, `complete`, `description`, `assignedUser`, `house`, `monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`) VALUES ('0', '0', '"+mychore.getDescription()+"', '"+mychore.getAssignedUser()+"', '"+mychore.getHouse()+"', '"+mychore.isMonday()+"', '"+mychore.isTuesday()+"', '"+mychore.isWednesday()+"', '"+mychore.isThursday()+"', '"+mychore.isFriday()+"', '"+mychore.isSaturday()+"', '"+mychore.isSunday()+"')";
		stmt.executeUpdate(q);
		stmt.close();
		conn.close();
	}

	public Chore[] getMyChoresForToday(String selectedHouse, String loggedInUser, String today) throws SQLException {
		ResultSet resret;
		int numChores = getNumberOfMyChoresForToday(selectedHouse, loggedInUser, today);
		int currentChore = 0;
		Chore[] chores = new Chore[numChores];
		for (int i = 0; i < numChores; i++)
		{
			chores[i] = new Chore();
		}
		conn = (Connection) dataSource.getConnection();
		stmt = (Statement) conn.createStatement();
		resret = stmt.executeQuery("SELECT *  FROM `chores` WHERE house='" + selectedHouse+"' AND assignedUser='"+loggedInUser+"' AND "+today+"=1");
		
		while (resret.next()) {
			chores[currentChore].setDuration(resret.getInt("duration"));
			chores[currentChore].setComplete(resret.getInt("complete"));
			chores[currentChore].setDescription(resret.getString("description"));
			chores[currentChore].setAssignedUser(resret.getString("assignedUser"));
			chores[currentChore].setHouse(resret.getString("house"));
			chores[currentChore].setMonday(resret.getInt("monday"));
			chores[currentChore].setTuesday(resret.getInt("tuesday"));
			chores[currentChore].setWednesday(resret.getInt("wednesday"));
			chores[currentChore].setThursday(resret.getInt("thursday"));
			chores[currentChore].setFriday(resret.getInt("friday"));
			chores[currentChore].setSaturday(resret.getInt("saturday"));
			chores[currentChore].setSunday(resret.getInt("sunday"));
			currentChore++;
		}

		resret.close();
		stmt.close();
		conn.close();
		return chores;
	}

}
