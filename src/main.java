import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class main {

	public static void main(String[] args) {
		Chore firstChore = new Chore("Jim", "Clean Litter Box", 5);
		JLabel myLabel = new JLabel();
		JPanel myPanel = new JPanel();
		
		myPanel.setBounds(0, 0, 500, 500);
		
		myLabel.setText("House Keeper");
		myPanel.add(myLabel);
		myPanel.setPreferredSize(new Dimension(500,500));
		myPanel.setVisible(true);
		//1. Create the frame.
		JFrame frame = new JFrame("House Keeper");
		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		//...create emptyLabel...
		frame.getContentPane().add(myPanel);

		//4. Size the frame.
		frame.pack();

		//5. Show it.
		frame.setVisible(true);
		System.out.println("Created a chore that is assigned to "+ firstChore.getAssignedUser() + " and it was to " + firstChore.getDescription() + " and it is estimated to take " + firstChore.getDuration() + " minutes");

	}

}
