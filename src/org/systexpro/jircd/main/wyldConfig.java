package org.systexpro.jircd.main;

import java.io.IOException;

import org.systexpro.jircd.filemanager.PropertiesFile;

public class wyldConfig {

	public boolean useServices = true;
	private int port = 6667;
	public String serverName = "wyldIRCD";
	private String serverHostName = "server.website.net";
	public String ukNick = "Unknown";
	public String hostCover = "hidden";

	public void loadConfig() {
		PropertiesFile config = new PropertiesFile("ircd.config");
		try {
			config.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.getString("Server Name", serverName);
		config.getString("Server Host Name", serverHostName);
		config.getInt("Server Port", port);
		config.getBoolean("Use Custom NickServs", useServices);
		config.getString("Unknown Nick Name", ukNick);
		config.getString("Host Cover", this.hostCover);
		config.save();
	}
	public String getHostCover() {
		return hostCover;
	}

	public String getServerHost() {
		return serverHostName;
	}

	public int getPort() {
		return port;
	}

	public String getServerName() {
		return serverName;
	}

	public String getUnknownNickname() {
		return ukNick;
	}
}

