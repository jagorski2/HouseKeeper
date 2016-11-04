
public class Chore {

    private int duration;
    private boolean complete;
    private String description;
    private String assignedUser;
    private boolean Monday;
    private boolean Tuesday;
    private boolean Wednesday;
    private boolean Thursday;
    private boolean Friday;
    private boolean Saturday;
	private boolean Sunday;
    
    public Chore () {
    	this.Monday = false;
    	this.Tuesday = false;
    	this.Wednesday = false;
    	this.Thursday = false;
    	this.Friday = false;
    	this.Saturday = false;
    	this.Sunday = false;
    	this.assignedUser = null;
    	this.description = null;
    	this.duration = 0;
    	this.complete = false;
    }
    
    public boolean isMonday() {
		return Monday;
	}

	public void setMonday(boolean monday) {
		Monday = monday;
	}

	public boolean isTuesday() {
		return Tuesday;
	}

	public void setTuesday(boolean tuesday) {
		Tuesday = tuesday;
	}

	public boolean isWednesday() {
		return Wednesday;
	}

	public void setWednesday(boolean wednesday) {
		Wednesday = wednesday;
	}

	public boolean isThursday() {
		return Thursday;
	}

	public void setThursday(boolean thursday) {
		Thursday = thursday;
	}

	public boolean isFriday() {
		return Friday;
	}

	public void setFriday(boolean friday) {
		Friday = friday;
	}

	public boolean isSaturday() {
		return Saturday;
	}

	public void setSaturday(boolean saturday) {
		Saturday = saturday;
	}

	public boolean isSunday() {
		return Sunday;
	}

	public void setSunday(boolean sunday) {
		Sunday = sunday;
	}
    
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}

	public  void updateChore(int ii, String arg) {
		switch (ii)
		{
			case 0:
				this.description = arg;
				break;
			case 1:
				this.assignedUser = arg;
				break;
			case 2:
				if (arg.equals("y"))
				{
					this.Monday = true;
				}
				break;
			case 3:
				if (arg.equals("y"))
				{
					this.Tuesday = true;
				}
				break;
			case 4:
				if (arg.equals("y"))
				{
					this.Wednesday = true;
				}
				break;
			case 5:
				if (arg.equals("y"))
				{
					this.Thursday = true;
				}
				break;
			case 6:
				if (arg.equals("y"))
				{
					this.Friday = true;
				}
				break;
			case 7:
				if (arg.equals("y"))
				{
					this.Saturday = true;
				}
				break;
			case 8:
				if (arg.equals("y"))
				{
					this.Sunday = true;
				}
				break;
				
		}
		
	}
	public String getDaysOfWeek() {
		String ret = null;
		if (this.Monday) {
			ret = "Monday ";
		}
		if (this.Tuesday) {
			ret = ret + "Tuesday ";
		}
		if (this.Wednesday) {
			ret = ret + "Wednesday ";
		}
		if (this.Thursday) {
			ret = ret + "Thursday ";
		}
		if (this.Friday) {
			ret = ret + "Friday ";
		}
		if (this.Saturday) {
			ret = ret + "Saturday ";
		}
		if (this.Sunday) {
			ret = ret + "Sunday";
		}
		
		return ret;
	}
	public static String printChores(Chore[] me){
		System.out.println("|No |     Description     |     Assigned To     | M | T | W | Th| F | Sa| Su|");
		for (int cc = 0; cc < me.length; cc++)
		{
			System.out.println(formatNo(cc) + formatDesc("asd"));
		}
		return "";
		
	}
    
	private static String formatDesc(String de){
		String ret = "|                     ";
		ret = "| " + de;
		for (int ii = ret.length(); ii < 22; ii++)
		{
			ret = ret + " ";
		}
		
		return ret + "|";
	}
	private static String formatNo(int no){
		String ret = "|";
		if (no < 9)
		{
			ret = "| " + Integer.toString(no + 1) + " ";
		}
		else if (no < 99)
		{
			ret = "| " + Integer.toString(no + 1);
		}
		
		return ret;
	}
	

}
