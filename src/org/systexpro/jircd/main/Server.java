
package org.systexpro.jircd.main;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.systexpro.jircd.entity.UnknownEntity;
import org.systexpro.jircd.filemanager.PropertiesFile;
import org.systexpro.jircd.filemanager.TextHandler;
import org.systexpro.jircd.handle.Command;
import org.systexpro.jircd.handle.UserMode;
import org.systexpro.jircd.handle.UserOper;
import org.systexpro.jircd.handle.UserPM;
import org.systexpro.jircd.handle.UserQuit;
import org.systexpro.jircd.misc.ChatColor;
import org.systexpro.jircd.misc.MOTD;
import org.systexpro.jircd.misc.Misc;
import org.systexpro.jircd.network.CheckHost;
import org.systexpro.jircd.network.Network;
import org.systexpro.jircd.pl.Channel;
import org.systexpro.jircd.pl.Constants;
import org.systexpro.jircd.pl.User;


public class Server extends Thread {
	public Socket socket;
	public int ThreadID;
	public String RemoteIP = "";
	public BufferedReader inFromClient;
	public DataOutputStream outToClient;
	public boolean sendAllJson = false;
	public String pid;
	public IRCServer ircServer = new IRCServer().getHandler();
	public int usersOnline = 0;
	public Network network;

	public Server(Socket connectionSocket, int ID, IRCServer instance) {
		socket = connectionSocket;
		ThreadID = ID;
		RemoteIP = connectionSocket.getInetAddress().getHostAddress();
	}

	/**
	 * Send the connection information
	 */
	public void sendLoginInformation() {
		sendText("*** Looking up your hostname");
		sendText("*** Checking Ident");
		if(!checkHost.checkHost(socket)) {
			sendText("*** Bad Host");
			this.closeSocket();
		}
		sendText("*** Found your hostname");
		sendText("*** No Ident response");
		sendText("Welcome to the " + ircServer.getServerName() + " Network ");
		sendText("Your host is " + ircServer.getServerHost() + ", running " + wyldIRCD.name + wyldIRCD.version);
		sendText("There are " + usersOnline + " users and __ invisible on __ servers");
		sendText(oper.OpersOnline + ":operator(s) online");
		sendText("___:channels formed");
		sendText("I have __ clients and __ servers");
		sendText("Current Local Users: ");
		sendText("Current Global Users: ");
		readMotd();
	}

	public void run(){
		Misc.println("[Socket] New client on IP " + RemoteIP + "! Thread ID: " + ThreadID + ".");
		try {
			inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));//Get Input
			outToClient = new DataOutputStream(socket.getOutputStream());//Get Output
			sendLoginInformation();//Send Information
		} catch (IOException e){
			Misc.servere(e.toString());
		}
		network = new Network(this, socket);
		while(socket.isConnected() && !socket.isClosed()){
			try {
				line = this.inFromClient.readLine().trim();
				String[] userInfo = line.split(" ");
				if(userInfo[0].startsWith("USER")) {
					user = new User(userInfo[1], userInfo[2], userInfo[3], ThreadID, this);
					this.ircServer.addUser(user, user.username);
					usersOnline += 1;
					sendText("Logged in as " + user.getHostMask());
				}
				Misc.println("[Socket] Received: \"" + line + "\" in thread " + ThreadID + ".");
			} catch (SocketException e) {
				closeSocket();
				break;
			} catch (IOException e) {
				Misc.servere(e.toString());
				closeSocket();
			} catch (NullPointerException e) {
				closeSocket();
				break;
			}

			new Command(line, this);
		}

		Misc.println("[Socket] User on " + RemoteIP + " disconnected. (Thread ID was " + ThreadID + ")");
		try {
			ircServer.clients.remove(this);
			usersOnline -= 1;
		} catch (IndexOutOfBoundsException e){
			Misc.servere("[Socket][ERROR] Could not remove thread from list.");
		}
	}

	public void sendText(String text){
		try {
			if(this.sendAllJson){
				if(text.length() >= 3 && text.substring(0, 3).equals("ERR")){
					text = "{\"ok\":false,\"message\":\"" + text + "\"}";
				}
				else if(text.equals("Welcome to Bukkit, please authenticate.")){
					text = "{\"ready_for_auth\":true}";
				}
				else if(text.equals("Authenticated!")){
					text = "{\"authed\":true}";
				}
				else if(text.equals("OK")){
					text = "{\"ok\":true}";
				}
				else if(text.substring(0, 1).equals("{") == false){
					text = "{\"message\":\"" + text + "\"}";
				} 
			}
			this.outToClient.writeBytes(text + "\r\n");
			Misc.println("[Socket] Sent: \"" + text + "\" to " + ThreadID + ".");
		} catch (IOException e) {
			Misc.servere(e.toString());
		}
	}

	protected void readMotd() {
		//TextHandler th = new TextHandler(Constants.RPL_MOTDSTART);
		sendText("- " + ircServer.getServerName() + " Message of the Day -");
		//send(th);
		for (int i = 0; i < motd.motd.length; i++) {
			//th = new TextHandler(Constants.RPL_MOTD);
			//th.appendLastParameter("- " + motd.motd[i]);
			//send(th);
			sendText(motd.motd[i]);
		}
		//th = new TextHandler(Constants.RPL_ENDOFMOTD);
		//th.appendLastParameter("End of /MOTD command.");
		//send(th);
		sendText("End of /MOTD command.");
	}

	public void send(TextHandler msg) {
		StringBuffer buf = new StringBuffer();
		buf.append(msg.getText());
		final int paramCount = msg.getParameterCount();
		if(paramCount > 0) {
			final int lastParamIndex = paramCount - 1;
			for(int i=0; i<lastParamIndex; i++)
				buf.append(' ').append(msg.getParameter(i));
			if(msg.hasLastParameter())
				buf.append(" :").append(msg.getParameter(lastParamIndex));
			else
				buf.append(' ').append(msg.getParameter(lastParamIndex));
		}
		sendText(buf.toString());
	}
	
	public void shutdown() {
		System.exit(0);
	}
	
	/**
	 * Close Socket
	 */
	public void closeSocket(){
		try {
			socket.close();
		} catch (IOException e) {
			Misc.servere(e.toString());
		}
	}

	public wyldIRCD wyldIRCD = new wyldIRCD();
	public CheckHost checkHost = new CheckHost(this);
	public User user;
	public MOTD motd = new MOTD("motd.txt");
	public UserOper oper = new UserOper(this);
	public String line = "";
}