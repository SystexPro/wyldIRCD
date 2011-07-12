package org.systexpro.jircd.pl;

public enum Modes {

	TOPIC_LOCK("t", Catagory.CHANNEL),
	VOICED_USER("v", Catagory.CHANNEL),
	HALFOP_USER("h", Catagory.CHANNEL),
	OP_USER("o", Catagory.CHANNEL),
	ADMIN_USER("a", Catagory.CHANNEL),
	OWNER_USER("q", Catagory.CHANNEL),
	BANNED_USER("b", Catagory.CHANNEL),
	MUTED_CHANNEL("m", Catagory.CHANNEL),
	INVITE_CHANNEL("i", Catagory.CHANNEL),
	BOT_USER("b", Catagory.NICK);

	private final String modeName;
	private final Modes.Catagory type;
	
	Modes(String ms, Modes.Catagory cat) {
		modeName = ms;
		type = cat;
	}
	
	public void setMode() {
		
	}

	public enum Catagory {
		CHANNEL,
		NICK,
		SERVER;
	}
}
