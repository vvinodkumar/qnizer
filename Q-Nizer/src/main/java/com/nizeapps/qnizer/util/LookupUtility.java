package com.nizeapps.qnizer.util;

public class LookupUtility {

	public static String lookup(String group, String key) {
		//Hardcoding for now..
		if(group.equalsIgnoreCase("country")) {
			if (key.equalsIgnoreCase("SG"))
				return "65";
		}
		return "Unknown";
	}
}
