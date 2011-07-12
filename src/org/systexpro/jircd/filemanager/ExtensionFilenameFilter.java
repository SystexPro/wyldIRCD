package org.systexpro.jircd.filemanager;

import java.io.File;
import java.io.FilenameFilter;

public class ExtensionFilenameFilter implements FilenameFilter {
	private final String extension;
	public ExtensionFilenameFilter(String ext) {
		extension = "."+ext;
	}
	public boolean accept(File dir, String name) {
		return name.endsWith(extension);
	}
}