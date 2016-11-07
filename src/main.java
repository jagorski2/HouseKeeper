import java.sql.SQLException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		Database myDB = new Database("jim", "housekeeper", "127.0.0.1");

		boolean run = true;
		boolean notLoggedIn = true;
		boolean houseSelected = false;
		String selectedHouse = "";
		int currentChore = 0;
	    String loggedInUser = "";
		Scanner scanner = new Scanner(System.in);
		Chore[] mychore = new Chore[10];
		String username = "";
		String password = "";
		String input;

		for (int cc = 0; cc < mychore.length; cc++) {
			mychore[cc] = new Chore();
		}
		System.out.println("House Keeper");
		while (run) {

			/* User Creation / Login Menu */
			while (notLoggedIn) {
				System.out.println(Menu.MainMenu);
				input = scanner.next();
				switch (input) {

				case "1":
					System.out.println("Enter user name");
					username = scanner.next();
					System.out.println("Enter password");
					password = scanner.next();
					try {
						if (Crypto.authUser(password.trim(), myDB.getPassAndSalt(username)[0],
								myDB.getPassAndSalt(username)[1])) {
							System.out.println("Logged in as User:" + username);
							loggedInUser =  username;
							notLoggedIn = false;
						} else {
							System.out.println("Invalid username/password");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case "2":
					System.out.println("Enter desired user name");
					username = scanner.next();
					System.out.println("Enter password");
					password = scanner.next();
					try {
						if (myDB.addUser(username, password.trim())) {
							System.out.println("Creating account for  " + username + " was successful! ");
						} else {
							System.out.println(username + " already exists!");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				case "q":
				case "exit":
					run = false;
					break;

				}
			}

			/* House viewing and creation */
			while (houseSelected == false) {
				System.out.println(Menu.MainMenu2);
				String houseName = "";
				String houseIndex = "";
				input = scanner.next();
				String[] houses;

				switch (input) {
				case "1":
					try {
						houses = myDB.getHouses("123123");
						for (int ii = 0; ii < houses.length; ii++)
						{
							System.out.println(Integer.toString(ii + 1) + ". " + houses[ii]);
						}
						houseIndex = scanner.next();
						selectedHouse = houses[Integer.parseInt(houseIndex) - 1];
						System.out.println("You selected house: " + selectedHouse);
						houseSelected = true;
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "2":
					System.out.println("Enter House Name");
					houseName = scanner.next();
					try {
						myDB.addHouse(loggedInUser, houseName);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
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

			System.out.println(Menu.MainMenu3);
			input = scanner.next();

			switch (input) {
			case "1":
				for (int ii = 0; ii <= 8; ii++) {
					System.out.println(Menu.addChore(ii));
					mychore[currentChore].updateChore(ii, new Scanner(System.in).nextLine());
					

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
		scanner.close();
	}

}
