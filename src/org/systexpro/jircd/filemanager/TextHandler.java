package org.systexpro.jircd.filemanager;

import org.systexpro.jircd.main.wyldSettings;
import org.systexpro.jircd.network.Server;
import org.systexpro.jircd.pl.Channel;
import org.systexpro.jircd.pl.User;

public final class TextHandler {

	protected String text;
	protected User from;
	protected boolean hasLast = false;
	protected String[] params = new String[3];
	protected int paramCount = 0;

	public TextHandler(String message){
		super();
		text = message;
	}

	public TextHandler(User from, String command) {
		this.from = from;
		this.text = command;
	}

	public TextHandler(User from, String command, Channel target) {
		this(from, command);
		appendParameter(target.getName());
	}

	public TextHandler() {

	}

	public String getText() {
		return text;
	}
	public String getParameter(int n) {
		return params[n];
	}
	public int getParameterCount() {
		return paramCount;
	}

	public boolean hasLastParameter() {
		return hasLast;
	}

	public TextHandler appendParameter(String param) {
		if(hasLast)
			throw new IllegalStateException("The last parameter has already been appended");
		addParam(param);
		return this;
	}

	private void addParam(String param) {
		if(paramCount == params.length) {
			int newLength = 2*(paramCount+1);
			if(newLength > wyldSettings.MAX_MESSAGE_PARAMETERS)
				newLength = wyldSettings.MAX_MESSAGE_PARAMETERS;
			String[] old = params;
			params = new String[newLength];
			System.arraycopy(old, 0, params, 0, paramCount);
		}
		params[paramCount++] = param;
	}

	public TextHandler appendLastParameter(String param) {
		if(hasLast)
			throw new IllegalStateException("The last parameter has already been appended");
		addParam(param);
		hasLast = true;
		return this;
	}

	//changed to not have the firt param
	public String append(String[] parts, String delimeter)
	{
		StringBuilder builder;
		int i;
		if (parts.length == 0)
			return null;

		builder = new StringBuilder();
		for (i = 1; i < parts.length - 1; i++)
		{
			builder.append(parts[i]);
			builder.append(delimeter);
		}
		builder.append(parts[i]);
		return builder.toString();
	}
	
	public String toString() {
		return text;
	}

}
