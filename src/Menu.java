
public class Menu {
	static String MainMenu = "Enter an option below\n 1. Login\n 2. Create Account";
	static String MainMenu2 = "Enter an opton below\n 1. Select House\n 2. Create House\n 3. Join House";
	static String MainMenu3 = "Enter an option below\n 1: Add Chore\n 2: View Chores";
	
	static String addChore(int line)
	{
		switch (line)
		{
		case 0:
			return "Enter Chore Description:";
		case 1:
			return "Enter who is assigned the chore";
		case 2:
			return "To Be Done On Monday?(y/n)";
		case 3:
			return "To Be Done On Tuesday(y/n)?";
		case 4:
			return "To Be Done On Wednesday(y/n)?";
		case 5:
			return "To Be Done On Thursday(y/n)?";
		case 6:
			return "To Be Done On Friday(y/n)?";
		case 7:
			return "To Be Done On Saturday(y/n)?";
		case 8:
			return "To Be Done On Sunday(y/n)?";
			
		}
		return null;
		
		
	}

}
