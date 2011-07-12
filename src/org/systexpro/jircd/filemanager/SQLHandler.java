package org.systexpro.jircd.filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.*;

import org.systexpro.jircd.misc.Misc;

public class SQLHandler {

	public File nicks = new File("./tmp/nicks.db");
	public File CHANNELS = new File("./tmp/channels.db");

	public void write(String line, String file) throws IOException {
		if(file.equalsIgnoreCase("nicks")) {
			FileWriter fstream = new FileWriter(nicks);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(line + "\n");
			out.close();
		} else if(file.equalsIgnoreCase("channels")) {
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(line + "\n");
			out.close();
		} else {
			Misc.println("Unknown File");
		}
	}

	public void getNickIdent(String username) throws IOException {
		String user = username.toLowerCase();
		BufferedReader in = new BufferedReader(new FileReader(nicks));
		String str = "";
		while ((str = in.readLine()) != null) {
			if(str.startsWith(user)) {
				Misc.println("Found Username");
			} else {
				Misc.println("Username not found");
			}
		}
		in.close();

	}


}
