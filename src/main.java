import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		Database myDB = new Database("andy", "andypassword123", "maximumfps.com");

		boolean run = true;
		int menuLevel = 1;
		String selectedHouse = "";
		String loggedInUser = "";
		Scanner scanner = new Scanner(System.in);
		Chore mychore = new Chore();
		String username = "";
		String password = "";
		String input;
		String today = Util.getDayOfWeek();


		


		System.out.println("House Keeper, today is " + today);
		while (run) {
			
			switch (menuLevel) {
			case 1:
				/* User Creation / Login Menu */
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
							loggedInUser = username;
							menuLevel = 2;

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
					break;

				case "q":
				case "exit":
					run = false;
					break;

				}
				break;
				
			case 2:
				System.out.println(Menu.MainMenu2);
				String houseName = "";
				String houseIndex = "";
				input = scanner.next();
				String[] houses;

				switch (input) {
				case "1":
					try {
						houses = myDB.getMyHouses(loggedInUser);
						myDB.getAllHouses();
						if (houses.length == 0) {
							System.out.println("Not a member of any houses, create or join one");
						} else {
							for (int ii = 0; ii < houses.length; ii++) {
								System.out.println(Integer.toString(ii + 1) + ". " + houses[ii]);
							}
							houseIndex = scanner.next();
							if (Integer.parseInt(houseIndex) > 0 && Integer.parseInt(houseIndex) <= houses.length) {
							selectedHouse = houses[Integer.parseInt(houseIndex) - 1];
							System.out.println("You selected house: " + selectedHouse);
							menuLevel = 3;
							}
							else
							{
								System.out.println("Invalid Option");
							}
							
						}

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
				case "3":
					try {

						houses = myDB.getAllJoinableHouses(loggedInUser);

						if (houses.length == 0) {
							System.out.println("No houses to join");
						} else {
							System.out.println("Available houses to join:");
							for (int ii = 0; ii < houses.length; ii++) {
								System.out.println(Integer.toString(ii + 1) + ". " + houses[ii]);
							}
							houseIndex = scanner.next();
							if (Integer.parseInt(houseIndex) > 0 && Integer.parseInt(houseIndex) <= houses.length) {
								selectedHouse = houses[Integer.parseInt(houseIndex) - 1];
								myDB.joinHouse(loggedInUser, selectedHouse);
								System.out.println("You selected house: " + selectedHouse + " to join!");
								menuLevel = 3;
							}
							else
							{
								System.out.println("Invalid Option");
							}

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "4":
					loggedInUser = "";
					menuLevel = 1;
					break;
				case "5":
				case "q":
				case "exit":
					run = false;
					break;
				default:
					System.out.println("Invalid Option");
					break;

				}
				
				break;
			case 3:

				/* Adding and Viewing Chores */
				System.out.println(Menu.MainMenu3);
				input = scanner.next();
				mychore.setHouse(selectedHouse);
				switch (input) {
				case "1":
					for (int ii = 0; ii <= 8; ii++) {
						System.out.println(Menu.addChore(ii));
						mychore.updateChore(ii, new Scanner(System.in).nextLine());

					}
					try {
						myDB.addChore(mychore);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case "2":
					try {
						Chore.printChores(myDB.getAllChores(selectedHouse));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "3":
					try {
						Chore.printChores(myDB.getMyChores(selectedHouse, loggedInUser));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "4":
					try {
						Chore.printChores(myDB.getMyChoresForToday(selectedHouse, loggedInUser, today));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "5":
					selectedHouse = "";
					menuLevel = 2;
					break;
				case "6":
					selectedHouse = "";
					loggedInUser = "";
					menuLevel = 1;
					break;
				case "7":
				case "q":
				case "exit":
					run = false;
					break;
				default:
					System.out.println("Invalid Option");
					break;

				}
				break;
			}

		}
		scanner.close();
	}

}
