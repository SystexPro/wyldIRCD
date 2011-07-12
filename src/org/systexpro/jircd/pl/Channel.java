package org.systexpro.jircd.pl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.systexpro.jircd.filemanager.TextHandler;
import org.systexpro.jircd.misc.Misc;
import org.systexpro.jircd.network.Server;

public class Channel {
	
	public String topic = "";
	public String name;
	public Server server;
	public final Map<String, Channel> channelMap = new ConcurrentHashMap();
	
	public Channel(String s, Server sev) {
		name = s;
		server = sev;
		addChannel(s);
	}
	
	public String getName() {
        return name;
    }
	
	public void addChannel(String s) {
		channelMap.put(s.toLowerCase(), this);
		Misc.println("Adding Channel: " + s);
	}
	
	public void removeChannel(String s) {
		if(channelMap.containsKey(s.toLowerCase())) {
			channelMap.remove(s.toLowerCase());
		}
	}
	
	public Channel getChannel(String s) {
		return (Channel) channelMap.get(s.toLowerCase());
	}
	
	public Channel getHandler() {
		return this;
	}
	
	public void setMode(Modes mode, Channel channel) {
		
	}
}
