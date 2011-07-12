package org.systexpro.jircd.plugins;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.systexpro.jircd.filemanager.ExtensionFilenameFilter;
import org.systexpro.jircd.misc.Misc;

public class PluginLoader {

	public final String PLUGIN_FOLDER = "plugins";

	public void loadPlugin(JarFile jar, ClassLoader loader) {
		Enumeration entries = jar.entries();
		while(entries.hasMoreElements()) {
			JarEntry entry = (JarEntry) entries.nextElement();
			String name = entry.getName();
			if(name.endsWith(".class")) {
				final String className = name.substring(0, name.length()-6).replace('/', '.');
				try {
					Class cls = loader.loadClass(className);
					Class[] classFile = cls.getClasses();
				} catch(Exception ex) {

				}
			} 
		}
	}

	public synchronized void reloadPlugins() {
		File pluginDir = new File("./plugins");
		File[] jarFiles = pluginDir.listFiles(new ExtensionFilenameFilter("jar"));
		if(jarFiles == null) {
			Misc.println("Plugin directory does not exist: "+pluginDir);
		} else {
			Misc.println("Found "+jarFiles.length+" plugins in directory "+pluginDir);
			for(int i=0; i<jarFiles.length; i++) {
				File jarFile = jarFiles[i];
				try {
					// create class loader for plugin
					URLClassLoader loader = URLClassLoader.newInstance(new URL[] {jarFile.toURL()});
					loadPlugin(new JarFile(jarFile), loader);
				} catch(IOException ioe) {
					Misc.println("Could not load plugin "+jarFile, ioe);
				}
			}
		}
	}

	public void readPluginYML(String s) {

	}



}
