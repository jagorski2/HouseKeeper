import java.util.Calendar;
import java.util.Date;

public class Util {
	
	static String getDayOfWeek() {
		
		int dayOfWeek;
		String ret = "";
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 2:
			ret = "monday";
			break;
		case 3:
			ret = "tuesday";
			break;
		case 4:
			ret = "wednesday";
			break;
		case 5:
			ret = "thursday";
			break;
		case 6:
			ret = "friday";
			break;
		case 7:
			ret = "saturday";
			break;
		case 1:
			ret = "sunday";
			break;
			
		}
		return ret;
		
	}

}
