package org.systexpro.jircd.network;

import java.io.IOException;
import java.net.Socket;



public class Network {

	public Server server;
	public Socket socket;
	
	public Network(Server server_, Socket socket_) {
		server = server_;
		socket = socket_;
	}
	
	public void stopNetwork() {
		System.exit(0);
	}
	
	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
} 
