package org.systexpro.jircd.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;

public class Misc {
	
	public static final SecureRandom RANDOM = new SecureRandom();
	
	public static void println(Object s) {
		System.out.println(s);
	}

	public static void servere(Object s) {
		System.out.println("[Servere] " + s);
	}

	public static void println(String string, IOException ioe) {
		// TODO Auto-generated method stub

	}

	public static String[] loadTextFile(String filename, int maxLines) throws IOException {
		String[] tmpLines = new String[maxLines];
		BufferedReader file = new BufferedReader(new FileReader(filename));
		int n;
		try {
			String line = file.readLine();
			for (n=0; line != null && n < tmpLines.length; n++) {
				tmpLines[n] = line;
				line = file.readLine();
			}
		} finally {
			file.close();
		}

		String[] lines = new String[n];
		System.arraycopy(tmpLines, 0, lines, 0, n);
		return lines;
	}

	
	
	public static String[] split(String str, char separator) {
        int startPos = 0;
        int endPos = str.indexOf(separator);
        if(endPos == -1) {
            return new String[] {str};
        } else {
            String[] splitList = new String[10];
            int count = 0;
            while(endPos != -1) {
                if(count+1 == splitList.length) { // count+1 to leave room for the last substring
                    String[] old = splitList;
                    splitList = new String[2*old.length];
                    System.arraycopy(old, 0, splitList, 0, count);
                }
                splitList[count++] = str.substring(startPos, endPos);
                startPos = endPos+1;
                endPos = str.indexOf(separator, startPos);
            }
            splitList[count++] = str.substring(startPos);
            // trim array
            String[] old = splitList;
            splitList = new String[count];
            System.arraycopy(old, 0, splitList, 0, count);
            return splitList;
        }
    }
}
