package org.systexpro.jircd.network;

import java.net.Socket;

import org.systexpro.jircd.main.Server;

public class Network {

	public Server server;
	public Socket socket;
	
	public Network(Server server_, Socket socket_) {
		server = server_;
		socket = socket_;
	}
	
	public void stopNetwork() {
		
	}
	
} 
