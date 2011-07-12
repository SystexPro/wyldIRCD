package org.systexpro.jircd.services;

public interface ServicesBot {
	public void onMessage(String sender, String message, String hasMode);
}
