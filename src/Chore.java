
public class Chore {
	enum weekdays {
		
	}

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
    
    public Chore (String user, String desc, int duration) {
    	this.Monday = false;
    	this.Tuesday = false;
    	this.Wednesday = false;
    	this.Thursday = false;
    	this.Friday = false;
    	this.Saturday = false;
    	this.Sunday = false;
    	this.assignedUser = user;
    	this.description = desc;
    	this.duration = duration;
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
    
	

}
