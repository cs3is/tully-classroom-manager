package theClient.res;

public class ClientConfig {

	public static final String NAME = ClientConfigManager.getStr("NAME");

	public static final String SERVER_IP = ClientConfigManager.getStr("SERVER_IP");
	public static final int SERVER_PORT = ClientConfigManager.getInt("SERVER_PORT");

	public static final String SQL_URL = ClientConfigManager.getStr("SQL_URL");
	public static final String SQL_DATABASE = ClientConfigManager.getStr("SQL_DATABASE");
	public static final String SQL_TABLE_NAME = ClientConfigManager.getStr("SQL_TABLE_NAME");
	public static final String SQL_USER = ClientConfigManager.getStr("SQL_USER");
	public static final String SQL_PASSWORD = ClientConfigManager.getStr("SQL_PASSWORD");
	public static final String SQL_MAX_CHARACTERS = ClientConfigManager.getStr("SQL_MAX_CHARACTERS");
	public static final String SQL_DROP_TABLE_ON_RESET = ClientConfigManager.getStr("SQL_DROP_TABLE_ON_RESET");

	public static final Boolean FRAME_ENABLED = ClientConfigManager.getBool("FRAME_ENABLED");
	public static final Boolean DEBUG_MODE = ClientConfigManager.getBool("DEBUG_MODE");
	public static final Boolean LOGGING = ClientConfigManager.getBool("LOGGING");

}
