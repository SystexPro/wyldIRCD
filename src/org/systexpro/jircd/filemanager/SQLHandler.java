package org.systexpro.jircd.filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.*;

import org.systexpro.jircd.misc.Misc;

public class SQLHandler {

	File NICKS = new File("./tmp/nicks.db");
	File CHANNELS = new File("./tmp/channels.db");

	public String read(String file) throws IOException {

		FileInputStream fstream = new FileInputStream(NICKS);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
			System.out.println (strLine);
		}
		in.close();
		return "";
	}


	public void write(String line, String file) throws IOException {
		if(file.equalsIgnoreCase("nicks")) {
			FileWriter fstream = new FileWriter("out.txt");
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


}
