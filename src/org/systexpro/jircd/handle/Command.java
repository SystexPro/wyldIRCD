package org.systexpro.jircd.handle;

import org.systexpro.jircd.filemanager.TextHandler;
import org.systexpro.jircd.misc.Misc;
import org.systexpro.jircd.network.Server;

public class Command {

	public String command;
	public Server server;
	public UserOper oper = new UserOper(server);

	public Command(String line, Server serve) {
		command = line;
		server = serve;
		handle(line);
	}

	public void handle(String line) {
		String[] message = line.split(" ");
		String command = message[0].toLowerCase();
		for(int x = 0; x <  message.length; x++) {
			//	Misc.println(message[x]);
		}
		if(command.startsWith("oper")) {
			UserOper oper = new UserOper(server);
			oper.handleCommand(line);
		} else if(command.startsWith("QUIT")) {
			TextHandler th = new TextHandler(null);
			String reason = th.append(message, " ");
			UserQuit userQuit = new UserQuit(server, reason);
		} else if(command.startsWith("join")) {
			oper.sendWallop(message[1], "join");	
		} else if(command.startsWith("privmsg")) {
			UserPM userPm = new UserPM(server);
			userPm.handleCommand(line);
		} else if(command.startsWith("mode")) {
			UserMode uMode = new UserMode(server);
			uMode.handleCommand(line);
		} else if(command.startsWith("user")) {
			
		}
	}

	public String getCommand() {
		return command;
	}
}
