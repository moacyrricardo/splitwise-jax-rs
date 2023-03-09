package com.iskeru.splitwise.utils;

public class SplitWiseConstants {
	public static final String API_BASE = "api/v3.0";
	public static final String API_URL = "https://secure.splitwise.com";
	
	public static final Long NO_GROUP = 0l;
	
	public static final String DATE_PATTERN = "yyyy-MM-dd'T'hh:mm:ss'Z'";

	private SplitWiseConstants() {
		throw new IllegalArgumentException("No instanciatable");
	}
}
