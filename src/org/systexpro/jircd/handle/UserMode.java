package org.systexpro.jircd.handle;

import org.systexpro.jircd.filemanager.TextHandler;
import org.systexpro.jircd.network.Server;

public class UserMode implements CommandHandler {
	
	public Server client;
	
	public UserMode(Server c) {
		client = c;
	}

	public void handleCommand(String line) {
		String[] message = line.split(" ");
		String to = message[1];
		String mode = message[2];
		for(int x = 0; x < message.length; x++) {
			System.out.println(message[x]);
		}
		
	}
	
	public void setMode(String mode) {
		
	}
	
	
}
