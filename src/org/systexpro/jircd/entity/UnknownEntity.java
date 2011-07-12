package org.systexpro.jircd.entity;

import java.util.Random;

import org.systexpro.jircd.network.Server;
import org.systexpro.jircd.main.wyldConfig;
public class UnknownEntity implements Entity {

	public Server server;
	public String handling;
	public wyldConfig wyldConfig = new wyldConfig();
	
	public UnknownEntity(Server s, String usr) {
		server = s;
		handling = usr;
		s.sendText(setToUnknownNick(handling));
	}
	
	public String setToUnknownNick(String s) {
		Random r = new Random();
		int ranUser = r.nextInt(9999);
		String newNickForUnknown = wyldConfig.getUnknownNickname().concat(Integer.toString(ranUser));
		return newNickForUnknown;
	}

	public void sendMessage(String s) {
		// TODO Auto-generated method stub
		
	}
}
