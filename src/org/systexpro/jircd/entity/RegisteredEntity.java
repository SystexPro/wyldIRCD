package org.systexpro.jircd.entity;

import org.systexpro.jircd.pl.User;

public class RegisteredEntity implements Entity {

	public User user;

	public RegisteredEntity(String s) {
		user = new User(this).getHandler();	
	}

	
	public void sendMessage(String s) {
		// TODO Auto-generated method stub

	}

}
