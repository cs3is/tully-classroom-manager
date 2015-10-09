package utils;

public class Config {

	public static final String NAME = ConfigManager.getStr("NAME");

	public static final String SERVER_IP = ConfigManager.getStr("SERVER_IP");
	public static final int SERVER_PORT = ConfigManager.getInt("SERVER_PORT");

	public static final String SQL_URL = ConfigManager.getStr("SQL_URL");
	public static final String SQL_DATABASE = ConfigManager.getStr("SQL_DATABASE");
	public static final String SQL_TABLE_NAME = ConfigManager.getStr("SQL_TABLE_NAME");
	public static final String SQL_USER = ConfigManager.getStr("SQL_USER");
	public static final String SQL_PASSWORD = ConfigManager.getStr("SQL_PASSWORD");
	public static final String SQL_MAX_CHARACTERS = ConfigManager.getStr("SQL_MAX_CHARACTERS");
	public static final String SQL_DROP_TABLE_ON_RESET = ConfigManager.getStr("SQL_DROP_TABLE_ON_RESET");

	public static final Boolean FRAME_ENABLED = ConfigManager.getBool("FRAME_ENABLED");
	public static final Boolean DEBUG_MODE = ConfigManager.getBool("DEBUG_MODE");
	public static final Boolean LOGGING = ConfigManager.getBool("LOGGING");

}