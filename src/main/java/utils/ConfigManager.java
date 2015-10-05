package utils;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

import utils.Log;

public class ConfigManager {

	private static Scanner scan;
	public static final HashMap<String, Object> settings = new HashMap<String, Object>();
	private static String fileLocation = "config.cfg";
	private int xResolution, yResolution;

	public ConfigManager() {

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		xResolution = gd.getDisplayMode().getWidth();
		yResolution = gd.getDisplayMode().getHeight();

		try {

			scan = new Scanner(new File(fileLocation));
			readFile(settings, fileLocation);

		} catch (FileNotFoundException e) {
			Log.oError("Config data not found, Creating now...");

			try {

				File f = new File(fileLocation);
				f.createNewFile();

			} catch (IOException e1) {
				Log.oError("failed creating files.");

			}
			e.printStackTrace();
		}
		System.out.println(settings.toString());

	}

	public static void readFile(HashMap<String, Object> h, String location) {
		while (scan.hasNextLine()) {
			String nextLine = scan.nextLine();
			Scanner scanner = new Scanner(nextLine);
			Scanner s = scanner.useDelimiter("=");
			h.put(s.next(), s.next());
			s.close();
			scanner.close();
		}
	}

	public static void writeFile(HashMap<String, Object> h, String location) {

		PrintWriter write;
		try {
			write = new PrintWriter(location);
			write.print("");
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		for (String key : settings.keySet()) {
			writeFile(key, (String) settings.get(key), location);
		}

	}

	private static void writeFile(String conf, String val, String location) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("config.cfg", true), "utf-8"));
			writer.write(conf + "=" + val + "\n");
		} catch (IOException ex) {

		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}

	}

	public static Object getCfg(String s) {
		return settings.get(s);
	}

	public static Boolean getBool(String s) {
		return Boolean.parseBoolean((String) settings.get(s));
	}

	public static Integer getInt(String s) {
		return Integer.parseInt((String) settings.get(s));
	}

	public static String getStr(String s) {
		return (String) settings.get(s);
	}

	public void addCfg(String val, String var) {
		settings.put(val, var);
	}

	public int getXResolution() {
		return xResolution;
	}

	public int getYResolution() {
		return yResolution;
	}

}
