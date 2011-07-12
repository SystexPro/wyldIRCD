package org.systexpro.jircd.misc;

public enum Messages {

	PORT_IN_USE("Error: Port is in use.\nPlease change the port"),

	UNKNOWN_NICK("Changing Nick");	

	private final String desc;

	Messages(String s) {
		desc = s;
	}

	public String getDesc() {
		return desc;
	}

	
}
