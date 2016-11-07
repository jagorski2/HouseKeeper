import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
		
		Database myDB = new Database("jim", "housekeeper", "127.0.0.1");

		boolean run = true;
		Scanner scanner = new Scanner(System.in);
		Chore[] mychore = new Chore[10];
		String username = "";
		String password = "";
		String vpassword = "";
		String name;
		for (int cc = 0; cc < mychore.length; cc++)
		{
			mychore[cc] = new Chore();
		}
		int currentChore = 0;
		while (run)
		{
			boolean notLoggedIn = true;
			while (notLoggedIn )
			{
				System.out.println(Menu.MainMenu);
				name = scanner.next();
				switch (name)
				{

				case "1":
					System.out.println("Enter user name");
					username = scanner.next();
					System.out.println("Enter password");
					password = scanner.next();
					System.out.println(password);
					if (Crypto.authUser(password.trim(), myDB.getPassAndSalt(username)[0], myDB.getPassAndSalt(username)[1])) {
						System.out.println("Logging in with username "+ username+ " and password " +password );
					}
					else
					{
						System.out.println("Username = "+ username + " Salt = "+myDB.getPassAndSalt(username)[0] + " EncryptedPW = "+ myDB.getPassAndSalt(username)[1]);
					}
					
					
					notLoggedIn = false;
					break;
				case "2":
				System.out.println("Enter desired user name");
				username = scanner.next();
				System.out.println("Enter password");
				password = scanner.next();
				if (myDB.addUser(username, password.trim())) {
					System.out.println("Creating account for  "+ username+ " password" + password+" was successful! " );
				}
				else
				{
					System.out.println(username+ " already exists!" );
				}
				
				case "q":
				case "exit":
					run = false;
					break;

				}
			}
			
			
			
			System.out.println(Menu.MainMenu2);
			name = scanner.next();

			switch (name)
			{
			case "1":
				for (int ii = 0; ii <= 8; ii++)
				{
					System.out.println(Menu.addChore(ii));
					mychore[currentChore].updateChore(ii , new Scanner(System.in).nextLine());

				}
				currentChore++;
				break;


			case "2":
				Chore.printChores(mychore);
				break;
			case "q":
			case "exit":
				run = false;
				break;
			default:
				System.out.println("Invalid Option");
				break;

			}

		}
	}

}
