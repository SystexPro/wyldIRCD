package org.systexpro.jircd.misc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MOTD {

	public String file;
	public File fileFile;
	public String[] motd;

	public MOTD(String file_) {
		file = file_;
		fileFile = new File(file);
		try {
			motd = Misc.loadTextFile("motd.txt", 500);
		} catch(IOException ioe) {
			motd = new String[0];
		}
	}

	public File getFile() {
		return fileFile;
	}
}
