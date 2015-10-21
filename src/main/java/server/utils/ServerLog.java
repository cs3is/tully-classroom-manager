package utils;

public class ServerLog {

	/**
	 * Shortcut for doing System.out.println().
	 * 
	 * @param text
	 *            The text that will be printed.
	 */
	public static void pl(String text) {
		System.out.println(text);
	}

	/**
	 * Shortcut for doing System.out.print().
	 * 
	 * @param text
	 *            The text that will be printed.
	 */
	public static void p(String text) {
		System.out.print(text);
	}

	/**
	 * Will print out information from the program so long as the logging feature is enabled. It will add the name and a tag to the information as it
	 * is being printed.
	 * 
	 * @param logLevel
	 *            This is the type of message that would be displayed, it can either be "[ERROR]", "[FATAL]", or "[WARN]".
	 * @param text
	 *            This is the message that will be displayed in the console.
	 */
	private static void log(String logLevel, String text) {
		if (ServerConfigManager.getBool("LOGGING") == true || logLevel == "[ERROR]" || logLevel == "[FATAL]"
				|| logLevel == "[WARN]")
			System.out.println(ServerConfigManager.getStr("NAME") + " " + logLevel + " " + text);
	}

	/**
	 * Invokes log() with the logLevel of "[WARN]".
	 * 
	 * @param text
	 *            This is the message that will be displayed in the console.
	 */
	public static void warn(String text) {
		log("[WARN]", text);
	}

	/**
	 * Invokes log() with the logLevel of "[DEBUG]".
	 * 
	 * @param text
	 *            This is the message that will be displayed in the console.
	 */
	public static void debug(String text) {
		if (ServerConfigManager.getBool("DEBUG_MODE") == true)
			log("[DEBUG]", text);
	}

	/**
	 * Invokes log() with the logLevel of "[ERROR]".
	 * 
	 * @param text
	 *            This is the message that will be displayed in the console.
	 */
	public static void error(String text) {
		log("[ERROR]", text);
	}

	/**
	 * Invokes log() with the logLevel of "[INFO]".
	 * 
	 * @param text
	 *            This is the message that will be displayed in the console.
	 */
	public static void info(String text) {
		log("[INFO]", text);
	}

	/**
	 * Invokes log() with the logLevel of "[FATAL]".
	 * 
	 * @param text
	 *            This is the message that will be displayed in the console.
	 */
	public static void fatal(String text) {
		log("[FATAL]", text);
	}

	/**
	 * This is the method to use if the error() method doesn't work for any reason, such as before the hashmap in ConfigManager is initialized and the
	 * log() method is not able to be invoked.
	 * 
	 * @param text
	 *            This is the message that will be displayed in the console.
	 */
	public static void oError(String text) {
		System.out.println("[ERROR]" + " " + text);
	}
}
