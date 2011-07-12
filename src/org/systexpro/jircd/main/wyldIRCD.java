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
import org.systexpro.jircd.network.Server;
import org.systexpro.jircd.pl.User;
import org.systexpro.jircd.plugins.PluginLoader;
import org.systexpro.jircd.services.NickServ;

public class wyldIRCD {

	static final Logger log = Logger.getLogger("wyldIRCD");
	public Thread masterAcceptThread;
	public NickServ nickServ;
	public List<Server> clients = new ArrayList<Server>();
	public final Map userMap = new ConcurrentHashMap();
	public long startTime;
	
	public static void main(String[] args) {
		wyldIRCD irc = new wyldIRCD();
		irc.startServer();
		irc.loadServices();
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

	public wyldIRCD getHandler() {
		return this;
	}



}