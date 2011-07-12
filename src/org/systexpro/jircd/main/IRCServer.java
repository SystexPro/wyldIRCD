package org.systexpro.jircd.main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import org.systexpro.jircd.filemanager.PropertiesFile;
import org.systexpro.jircd.misc.Misc;
import org.systexpro.jircd.network.MasterAcceptThread;
import org.systexpro.jircd.pl.User;
import org.systexpro.jircd.plugins.PluginLoader;
import org.systexpro.jircd.services.NickServ;

public class IRCServer {

	static final Logger log = Logger.getLogger("wyldIRCD");
	public Thread masterAcceptThread;
	public NickServ nickServ;
	public List<Server> clients = new ArrayList<Server>();
	public final Map userMap = new ConcurrentHashMap();
	public boolean useServices = true;
	private int port = 6667;
	public String serverName = "wyldIRCD";
	private String serverHostName = "server.website.net";
	public String ukNick = "Unknown";
	public String hostCover = "hidden";
	public long startTime;
	
	public static void main(String[] args) {
		IRCServer irc = new IRCServer();
		irc.loadConfig();
		//PluginLoader pManager = new PluginLoader();
		irc.startServer();
		irc.loadServices();
	
		//pManager.reloadPlugins();
	}

	public void startServer() {
		masterAcceptThread = new MasterAcceptThread(this);
		masterAcceptThread.start();
		startTime = System.currentTimeMillis();
	}

	public void loadServices() {
		nickServ = new NickServ("NickServ", true);
		addUser(nickServ.user, nickServ.username);
	}

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

	public Object getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Date myDate = new Date();
		return sdf.format(myDate);
	}

	public void addUser(User user, String username) {
		userMap.put(username.toLowerCase(), user);
	}

	public void removeUser(String username) {
		userMap.remove(username.toLowerCase());
	}

	public IRCServer getHandler() {
		return this;
	}



}