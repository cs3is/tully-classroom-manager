package utils;


@SuppressWarnings(value = { "all" })
public class Log {

	private static String s = ""; // TODO make into an arraylsit so that the program name can be added later

	public static void pl(String text) {
		System.out.println(text);
	}

	public static void p(String text) {
		System.out.print(text);
	}

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
