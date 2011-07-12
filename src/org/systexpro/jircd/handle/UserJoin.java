package org.systexpro.jircd.handle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.systexpro.jircd.network.Server;

public class UserJoin implements CommandHandler {
	
	public Server client;
	private final Map channels = new ConcurrentHashMap();
	
	public UserJoin(Server c) {
		client = c;
	}
	
	public void handleCommand(String line) {
		String[] message = line.split(" ");
		String channel = message[1];
		
	}
	
	public void joinChannel(String channel_) {
		String channel = channel_;
		if(channel.startsWith("#", 0)) {
			channel = "#" + channel;
		}
	
			
	}

}
