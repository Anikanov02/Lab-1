package classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarController {

	Calendar calendar;
	
	public CalendarController() {
		calendar = new GregorianCalendar();
		calendar.setLenient(false);
	}
	
	public Calendar getDifference(Calendar c) {
		Calendar res;
		if(calendar.compareTo(c)>0) {
			Calendar temp = calendar;
			calendar = c;
			c = temp;
		}
		res = new GregorianCalendar(
				c.get(Calendar.YEAR)-calendar.get(Calendar.YEAR),
				c.get(Calendar.MONTH)-calendar.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR_OF_DAY)-calendar.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE)-calendar.get(Calendar.MINUTE),
				c.get(Calendar.SECOND)-calendar.get(Calendar.SECOND));
		res.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND)-calendar.get(Calendar.MILLISECOND));
		calendar = c;
		return res;
	}
	
	public void timeTravel(ExtendedTimeUnit timeUnit, int amount) {
		switch(timeUnit) {
		case YEARS:
			calendar.add(Calendar.YEAR, amount);
		case MONTHS:
			calendar.add(Calendar.MONTH, amount);
		case DAYS:
			calendar.add(Calendar.DAY_OF_YEAR, amount);
		case HOURS:
			calendar.add(Calendar.HOUR_OF_DAY, amount);
		case MINUTES:
			calendar.add(Calendar.MINUTE, amount);
		case SECONDS:
			calendar.add(Calendar.SECOND, amount);
		case MILLISECONDS:
			calendar.add(Calendar.MILLISECOND, amount);
		}
	}
	
	public int getWeekDay() {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public int getWeekOfMonth() {
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	public int getWeekOfYear() {
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

}
