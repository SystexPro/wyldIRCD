package org.systexpro.jircd.handle;

import org.systexpro.jircd.network.Server;

public interface CommandHandler {
	
	public void handleCommand(String line);
}
