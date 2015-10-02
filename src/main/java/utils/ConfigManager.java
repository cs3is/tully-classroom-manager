package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import utils.Log;

public class ConfigManager {

	private static Scanner scan;
	public static final HashMap<String, Object> settings = new HashMap<String, Object>();
	private static String fileLocation = "config.cfg";

	public ConfigManager() {

		try {

			scan = new Scanner(new File(fileLocation));
			readConfigFile(settings);

		} catch (FileNotFoundException e) {
			Log.oError("Config data not found, Creating now...");

			try {

				File f = new File("config.cfg");
				f.createNewFile();

				// new ConfigManager();

			} catch (IOException e1) {
				Log.oError("failed creating files.");

				try {
					File f = new File("config.cfg");
					f.createNewFile();
				} catch (IOException e2) {
					Log.oError("failed creating files...");
				}

			}
			e.printStackTrace();
		}
	}

	public static void readConfigFile(HashMap<String, Object> h) {

	}

	public static void writeConfigFile(HashMap<String, Object> h) {

	}

	public static Object getCfg(String s) {
		return settings.get(s);
	}

	public static Boolean getCfgBool(String s) {
		return Boolean.parseBoolean((String) settings.get(s));
	}

	public static Integer getCfgInt(String s) {
		return Integer.parseInt((String) settings.get(s));
	}

	public static String getCfgStr(String s) {
		return (String) settings.get(s);
	}

	public void addCfg(String val, String var) {
		settings.put(val, var);
	}

}
