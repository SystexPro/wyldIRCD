package org.systexpro.jircd.handle;

import org.systexpro.jircd.main.Server;

public interface CommandHandler {
	
	public void handleCommand(String line);
}
