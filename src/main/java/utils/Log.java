package utils;

@SuppressWarnings(value = { "all" })
public class Log {

	/**
	 * Shortcut for doing System.out.println()
	 * 
	 * @param text
	 *            The text that will be printed
	 */
	public static void pl(String text) {
		System.out.println(text);
	}

	/**
	 * Shortcut for doing System.out.print()
	 * 
	 * @param text
	 *            The text that will be printed
	 */
	public static void p(String text) {
		System.out.print(text);
	}

	/**
	 * Will print out information from the program so long as the logging feature is enabled. Formatted to look nice in the console.
	 * 
	 * @param logLevel
	 *            This is the type of message that would be displayed
	 * @param text
	 */
	public static void log(String logLevel, String text) {
		if (ConfigManager.getBool("LOGGING") == true || logLevel == "[ERROR]" || logLevel == "[FATAL]"
				|| logLevel == "[WARN]")
			System.out.println(ConfigManager.getStr("NAME") + " " + logLevel + " " + text);
	}

	public static void warn(String text) {
		log("[WARN]", text);
	}

	public static void debug(String text) {
		if (ConfigManager.getBool("DEBUG_MODE") == true)
			log("[DEBUG]", text);
	}

	public static void error(String text) {
		log("[ERROR]", text);
	}

	public static void info(String text) {
		log("[INFO]", text);
	}

	public static void fatal(String text) {
		log("[FATAL]", text);
	}

	public static void oError(String text) {
		System.out.println("[ERROR]" + " " + text);
	}
}
