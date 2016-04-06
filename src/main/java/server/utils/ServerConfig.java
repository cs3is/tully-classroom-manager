package utils;

public class ServerConfig {

	public static final String SQL_URL = ServerConfigManager.getStr("SQL_URL");
	public static final String SQL_PASSWORD = ServerConfigManager.getStr("SQL_PASSWORD");
	public static final String SQL_TABLE_NAME = ServerConfigManager.getStr("SQL_TABLE_NAME");
	public static final String SQL_DROP_TABLE_ON_RESET = ServerConfigManager.getStr("SQL_DROP_TABLE_ON_RESET");
	public static final String SQL_MAX_CHARACTERS = ServerConfigManager.getStr("SQL_MAX_CHARACTERS");
	public static final String SQL_USER = ServerConfigManager.getStr("SQL_USER");
	public static final String SQL_DATABASE = ServerConfigManager.getStr("SQL_DATABASE");

	public static final int SERVER_PORT = ServerConfigManager.getInt("SERVER_PORT");

	public static final boolean DEBUG_MODE = ServerConfigManager.getBool("DEBUG_MODE");
	public static final boolean LOGGING = ServerConfigManager.getBool("LOGGING");

	public static final String NAME = ServerConfigManager.getStr("NAME");
	public static final int TIME_BETWEEN_QUESTIONS = ServerConfigManager.getInt("TIME_BETWEEN_QUESTIONS");

	public static final String SERVER_PROFILE_1 = ServerConfigManager.getStr("SERVER_PROFILE_1");
	public static final String SERVER_PROFILE_2 = ServerConfigManager.getStr("SERVER_PROFILE_2");
	public static final String SERVER_PROFILE_3 = ServerConfigManager.getStr("SERVER_PROFILE_3");
	public static final String SERVER_PROFILE_4 = ServerConfigManager.getStr("SERVER_PROFILE_4");
	public static final String SERVER_PROFILE_5 = ServerConfigManager.getStr("SERVER_PROFILE_5");
	public static final String SERVER_PROFILE_6 = ServerConfigManager.getStr("SERVER_PROFILE_6");
	public static final String SERVER_PROFILE_7 = ServerConfigManager.getStr("SERVER_PROFILE_7");
	public static final String SERVER_PROFILE_8 = ServerConfigManager.getStr("SERVER_PROFILE_8");
	public static final String SERVER_PROFILE_9 = ServerConfigManager.getStr("SERVER_PROFILE_9");

}
