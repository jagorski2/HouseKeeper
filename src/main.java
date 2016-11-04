import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		boolean run = true;
		Scanner scanner = new Scanner(System.in);
		Chore[] mychore = new Chore[10];
		for (int cc = 0; cc < mychore.length; cc++)
		{
			mychore[cc] = new Chore();
		}
		int currentChore = 0;
		while (run)
		{
			
			System.out.println(Menu.MainMenu);
			String name = scanner.next();

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
