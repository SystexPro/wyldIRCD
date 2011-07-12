
package org.systexpro.jircd.network;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.systexpro.jircd.main.Server;
import org.systexpro.jircd.main.IRCServer;
import org.systexpro.jircd.misc.Messages;
import org.systexpro.jircd.misc.Misc;

public class MasterAcceptThread extends Thread {
	
	private IRCServer irc;
	public int i = 0;
	
	public MasterAcceptThread(IRCServer instance) {
		irc = instance;
	}

	public void run(){
		ServerSocket mainSocket = null;
		try {
			mainSocket = new ServerSocket(irc.getPort());
		} catch (IOException e) {
			Misc.println(Messages.PORT_IN_USE.getDesc());
		}
		Misc.println("[Socket] Listening on port " + irc.getPort() + " for incoming connections.");
		while(true){
			Socket connectionSocket = null;
			try {
				connectionSocket = mainSocket.accept();
			} catch (IOException e) {
				Misc.servere(e.toString());
			}
			try {
				Server thread = new Server(connectionSocket, i, irc);
				thread.start();
				irc.clients.add(thread);
			} catch (java.lang.NullPointerException e){
				e.printStackTrace();
			}
			i++;
		}
	}
	
}
