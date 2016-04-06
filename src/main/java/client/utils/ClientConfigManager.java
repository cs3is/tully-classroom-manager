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

import javax.swing.JOptionPane;

import utils.ServerLog;

public class ClientConfigManager {

	private static Scanner scan;
	public static final HashMap<String, Object> settings = new HashMap<String, Object>();
	private static String fileLocation = "src/main/java/client/ClientConfig.cfg";
	private static int xResolution;
	private static int yResolution;

	/**
	 * Manages everything to do with loading, saving, and accessing the settings
	 * that have been set in this program. It saves and loads all of the
	 * settings into a text file that is both readable and editable without the
	 * use of any external programs. When the program is running, all of the
	 * settings are stored within a hashmap, and are accessed by using the key
	 * associated with the the setting wanted, and giving it to the proper
	 * accessor, each of which cast the Object that is saved in the hashmap into
	 * the desired Object type.
	 */
	public ClientConfigManager() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		xResolution = gd.getDisplayMode().getWidth();
		yResolution = gd.getDisplayMode().getHeight();
		try {

			scan = new Scanner(new File(fileLocation));
			readFile(settings);

		} catch (FileNotFoundException e) {
			ServerLog.oError("Config data not found, Creating now...");

			try {

				File f = new File(fileLocation);
				f.createNewFile();

			} catch (IOException e1) {
				ServerLog.oError("failed creating files.");

			}
			e.printStackTrace();
		}

	}

	/**
	 * Writes all of the data from a hashmap into the file location specified.
	 * 
	 * @param h
	 *            The hashmap that will have all of its data printed
	 * @param location
	 *            The location of the file that will have all of the information
	 *            printed to it.
	 */
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

	/**
	 * Used whenever the hashmap needs to be written into a text file, such as
	 * when the settings need to be saved, or when the program is getting
	 * closed. It will write them in the format Key=Value withing the file
	 * specified.
	 * 
	 * @param location
	 *            The location of the file which the hashmap will be saved to.
	 */
	public static void writeFile(String location) {

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

	/**
	 * This is the helper method for the other writeFile(), it is used to write
	 * text into the file specified, with the specified text.
	 * 
	 * @param key
	 *            This is the unique identifier for the option given
	 * @param value
	 *            This is the value that is associated with the given key
	 * @param location
	 *            This is the location of the file which the settings will be
	 *            written to
	 */
	private static void writeFile(String key, String value, String location) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(location, true), "utf-8"));
			writer.write(key + "=" + value + "\n");
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}

	}

	/**
	 * This method will read a file that's in the format key=value, and add all
	 * of the lines into a hashmap
	 * 
	 * @param h
	 *            The hashmap which the text from the file will be added to.
	 */
	public static void readFile(HashMap<String, Object> h) {
		while (scan.hasNextLine()) {
			String nextLine = scan.nextLine();
			Scanner scanner = new Scanner(nextLine);
			Scanner s = scanner.useDelimiter("=");
			h.put(s.next(), s.next());
			s.close();
		}
	}

	/**
	 * This is the method to call whenever the settings need to be updated- it
	 * will reload all of the settings from the config file.
	 */
	public static void update() {

		try {
			settings.clear();
			ServerLog.info("Reading config data...");
			scan = new Scanner(new File("config.cfg"));
			readFile(settings);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Some files were detected as missing from the program, and have been restored, press OK to restart.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			try {
				File f = new File("config.cfg");
				f.createNewFile();
			} catch (IOException e1) {
				ServerLog.error("failed creating files...");
			}
			System.exit(0);
		}
	}

	/**
	 * A method to access any of the program's settings from the hashmap
	 * 
	 * @return Returns a value from the hashmap in the form of an Object
	 * @param s
	 *            The key of the value which will be returned
	 */
	public static Object getCfg(String s) {
		return settings.get(s);
	}

	/**
	 * A method to access any of the program's settings from the hashmap
	 * 
	 * @return Returns a value from the hashmap in the form of an Boolean
	 * @param s
	 *            The key of the value which will be returned
	 */
	public static Boolean getBool(String s) {
		return Boolean.parseBoolean((String) settings.get(s));
	}

	/**
	 * A method to access any of the program's settings from the hashmap
	 * 
	 * @return Returns a value from the hashmap in the form of an Integer
	 * @param s
	 *            The key of the value which will be returned
	 */
	public static Integer getInt(String s) {
		return Integer.parseInt((String) settings.get(s));
	}

	/**
	 * A method to access any of the program's settings from the hashmap
	 * 
	 * @return Returns a value from the hashmap in the form of an String
	 * @param s
	 *            The key of the value which will be returned
	 */
	public static String getStr(String s) {
		return (String) settings.get(s);
	}

	/**
	 * This is the method to use when something needs to be added into the
	 * hashmap where all of the settings are stored.
	 * 
	 * @param key
	 *            This will be the key for the option to add to the settings
	 *            hashmap
	 * @param value
	 *            This will be the text associated with the given key. It will
	 *            be converted into an object upon entering the hashmap.
	 */
	public void addSetting(String key, String value) {
		settings.put(key, value);
	}

	/**
	 * 
	 * @return The number of pixels on the monitor in the X field
	 */
	public static int getXResolution() {
		return xResolution;
	}

	/**
	 * 
	 * @return The number of pixels on the monitor height in the Y field
	 */
	public static int getYResolution() {
		return yResolution;
	}

}
