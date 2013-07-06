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
		return (currentTimeMillis - startTimeMillis) / (60 * 1000) % 60;
		
	}
}
