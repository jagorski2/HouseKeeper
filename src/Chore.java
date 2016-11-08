
public class Chore {

    private int duration;
    private boolean complete;
    private String description;
    private String assignedUser;
    private String house;
	private int Monday;
    private int Tuesday;
    private int Wednesday;
    private int Thursday;
    private int Friday;
    private int Saturday;
	private int Sunday;
    
    public Chore () {
    	this.Monday = 0;
    	this.Tuesday = 0;
    	this.Wednesday = 0;
    	this.Thursday = 0;
    	this.Friday = 0;
    	this.Saturday = 0;
    	this.Sunday = 0;
    	this.assignedUser = null;
    	this.description = null;
    	this.duration = 0;
    	this.complete = false;
    }
    
    public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}
    
    public int isMonday() {
		return Monday;
	}

	public void setMonday(int monday) {
		Monday = monday;
	}

	public int isTuesday() {
		return Tuesday;
	}

	public void setTuesday(int tuesday) {
		Tuesday = tuesday;
	}

	public int isWednesday() {
		return Wednesday;
	}

	public void setWednesday(int wednesday) {
		Wednesday = wednesday;
	}

	public int isThursday() {
		return Thursday;
	}

	public void setThursday(int thursday) {
		Thursday = thursday;
	}

	public int isFriday() {
		return Friday;
	}

	public void setFriday(int friday) {
		Friday = friday;
	}

	public int isSaturday() {
		return Saturday;
	}

	public void setSaturday(int saturday) {
		Saturday = saturday;
	}

	public int isSunday() {
		return Sunday;
	}

	public void setSunday(int sunday) {
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
					this.Monday = 1;
				}
				break;
			case 3:
				if (arg.equals("y"))
				{
					this.Tuesday = 1;
				}
				break;
			case 4:
				if (arg.equals("y"))
				{
					this.Wednesday = 1;
				}
				break;
			case 5:
				if (arg.equals("y"))
				{
					this.Thursday = 1;
				}
				break;
			case 6:
				if (arg.equals("y"))
				{
					this.Friday = 1;
				}
				break;
			case 7:
				if (arg.equals("y"))
				{
					this.Saturday = 1;
				}
				break;
			case 8:
				if (arg.equals("y"))
				{
					this.Sunday = 1;
				}
				break;
				
		}
		
	}
	public String getDaysOfWeek() {
		String ret = null;
		if (this.Monday == 1) {
			ret = "Monday ";
		}
		if (this.Tuesday == 1) {
			ret = ret + "Tuesday ";
		}
		if (this.Wednesday == 1) {
			ret = ret + "Wednesday ";
		}
		if (this.Thursday == 1) {
			ret = ret + "Thursday ";
		}
		if (this.Friday == 1) {
			ret = ret + "Friday ";
		}
		if (this.Saturday == 1) {
			ret = ret + "Saturday ";
		}
		if (this.Sunday == 1) {
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
