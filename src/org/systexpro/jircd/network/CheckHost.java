package org.systexpro.jircd.network;

import java.net.Socket;



public class CheckHost {

	public Server client;
	
	public CheckHost(Server c) {
		client = c;
	}
	
	public void checkHost() {
		
	}

	public boolean checkHost(Socket socket) {
		boolean host;
		if(socket.equals("127.0.0.1")) {
			host = false;
		} else {
			host = true;
		}
		return host;
	}
}
