
public class main {

	public static void main(String[] args) {
		Chore firstChore = new Chore("Jim", "Clean Litter Box", 5);
		System.out.println("Created a chore that is assigned to "+ firstChore.getAssignedUser() + " and it was to " + firstChore.getDescription() + " and it is estimated to take " + firstChore.getDuration() + " minutes");

	}

}
