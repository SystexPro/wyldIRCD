package org.systexpro.jircd.handle;

import org.systexpro.jircd.filemanager.TextHandler;
import org.systexpro.jircd.main.Server;

public class UserOper implements CommandHandler {

	public Server client;

	public UserOper(Server c) {
		client = c;
	}

	public void handleCommand(String line) {
		String[] message = line.split(" ");
		username = message[1];
		password = message[2];
		if(username.equalsIgnoreCase("Christian") && password.equalsIgnoreCase("chris1996")) {
			client.sendText("Opered Up");
			sendWallop(username, "oper on");
			OpersOnline += 1;
		} else {
			client.closeSocket();
		}
	}

	public void sendWallop(String s, String typed) {
		if(isOper) {
			String type = typed.toLowerCase();
			TextHandler msg = new TextHandler("SNOTICE");
			msg.appendParameter(client.ircServer.getServerHost() + " " + (String) client.ircServer.getDate());
			if(type.equalsIgnoreCase("client")) {
				msg.appendLastParameter("-- Client exiting: " + s);
			} else if(type.equalsIgnoreCase("join")) {
				msg.appendLastParameter("-- Channel Join: " + s);
			} else if(type.equalsIgnoreCase("oper on")) {
				msg.appendLastParameter("-- Opered Logged In: " + s + " " + client.user.getHostMask());
			}
			client.send(msg);
		}
	}

	public boolean isOper = false;
	public int OpersOnline = 0;
	public String username = "";
	public String password = "";

}
