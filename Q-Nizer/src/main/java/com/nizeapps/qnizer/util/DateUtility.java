package com.nizeapps.qnizer.util;

import java.util.Calendar;

public class DateUtility {

	public static  Calendar getBusinessDateTime() {
		return Calendar.getInstance();
	}
	
	public static long getElapsedTimeInMinutes(Calendar startTime) {
		if(startTime == null)
			return 0L;
		
		Calendar currentTime = getBusinessDateTime();
		long startTimeMillis = startTime.getTimeInMillis();
		long currentTimeMillis = currentTime.getTimeInMillis();
		long diff = currentTimeMillis - startTimeMillis;
		float diffMinutes = diff / (60 * 1000) % 60;
		float diffHours = diff / (60 * 60 * 1000) % 24;
		float diffDays = diff / (24 * 60 * 60 * 1000);
		
		return (long)((diffDays*24*60) + (diffHours*60) + diffMinutes); 
		
	}
}
