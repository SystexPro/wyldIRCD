package org.systexpro.jircd.services;

import org.systexpro.jircd.main.IRCServer;
import org.systexpro.jircd.pl.*;

public class NickServ extends IRCServer implements ServicesBot {
	
	public NickServ(String name, boolean isOper) {
		username = name;
		isOper = isOp;
		user = new User(this);
	}


	public void onMessage(String sender, String message, String hasMode) {

		
	}

	public User user;
	public String username;
	public boolean isOp;

}
