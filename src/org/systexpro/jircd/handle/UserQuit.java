package org.systexpro.jircd.handle;

import org.systexpro.jircd.filemanager.TextHandler;
import org.systexpro.jircd.main.Server;
import org.systexpro.jircd.misc.Misc;

public class UserQuit implements CommandHandler {

	public Server client;

	public UserQuit(Server c, String reason) {
		client = c;
		quitServer(reason);
	}
	
	private void quitServer(String reason) {
		TextHandler quit = new TextHandler("QUIT").appendLastParameter(reason);
		client.send(quit);
	}


	public void handleCommand(String line) {

		
	}
}
