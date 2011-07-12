package org.systexpro.jircd.handle;

import org.systexpro.jircd.filemanager.TextHandler;
import org.systexpro.jircd.main.Server;
import org.systexpro.jircd.misc.Misc;

public class UserPM implements CommandHandler{

	public Server client;

	public UserPM(Server c) {
		client = c;
	}

	public void handleCommand(String line) {
		String[] message = line.split(" ");
		String command = message[1];
		String[] mess_ = line.split(" :");
		String mess = mess_[0];
		String pmnick = message[1].toLowerCase();
		if(command.equalsIgnoreCase("NickServ")) {
			for(int x = 0; x < mess_.length; x++) {
				client.sendText(mess_[x]);
			}
			TextHandler th = new TextHandler("PRIVMSG");
			th = th.appendLastParameter("safs");
			client.send(th);
		}
	}

	public String betterTrim(String[] str) {
		for(int x = 0; x < str.length; x++) {
			str[x].replaceAll(":", " ");
		}
		return str.toString();
	}

}
