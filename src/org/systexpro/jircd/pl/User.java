package org.systexpro.jircd.pl;

import java.math.BigInteger;

import org.systexpro.jircd.entity.RegisteredEntity;
import org.systexpro.jircd.filemanager.TextHandler;
import org.systexpro.jircd.main.Server;
import org.systexpro.jircd.misc.Misc;
import org.systexpro.jircd.services.ServicesBot;

public class User {
	
	public RegisteredEntity rEntity;
	public Server server;
	public int usersOnline = 0;
	public int clientId;
	public String username;
	public String userident;
	public String userhost;
	public String userrealname;
	public String fullHostMask;
	
	/**
	 * Construct a new User
	 * @param nickname
	 * @param ident
	 * @param hostname
	 */
	public User(String nickname, String ident, String hostname, int id, Server serve) {
		username = nickname;
		userident = ident;
		userhost = hostname;
		clientId = id;
		server = serve;
		fullHostMask = userident + "@" + this.server.ircServer.getHostCover() + "-" + maskHost(this.userhost);
		
	}

	public String getHostMask() {
		return fullHostMask;
	}

	public User(ServicesBot s) {

	}

	public User(RegisteredEntity entity) {
		super();
		rEntity = entity;
	}

	public User getUser(String s) {
		return (User) this.server.ircServer.userMap.get(s.toLowerCase());
	}

	public User getHandler() {
		return this;
	}
	
	public String ranString(int id)
	{
		return new BigInteger(id , Misc.RANDOM).toString(32).toUpperCase().trim();
	}

	/**
	 * Host Mask a IP
	 * @param ip
	 * @return
	 */
	public String maskHost(String ip) {
		String[] ipParts = Misc.split(ip, '.');
		CharSequence ip1 = ipParts[0];
		CharSequence ip2 = ipParts[1];
		CharSequence ip3 = ipParts[2];
		CharSequence ip4 = ipParts[3];
		for(int x = 0; x < ipParts.length; x++) {
			ip1 = new String(ranString(25));
			ip2 = new String(ranString(23));
			ip3 = new String(ranString(25));
			ip4 = new String(ranString(21));
		}
		return ip1 + "." + ip2 + "." + ip3 + "." + ip4;
	}

	public void send(TextHandler message) {
		this.server.send(message);

	}
}
