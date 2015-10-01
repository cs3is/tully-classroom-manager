package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigManager {

	private static Scanner scan;
	public static final HashMap<String, Object> settings = new HashMap<String, Object>();

	public ConfigManager() {
		try {
			scan = new Scanner(new File(""));
			readConfigFile(settings);
		} catch (FileNotFoundException e) {
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
