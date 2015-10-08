package main;

import java.io.IOException;

import server.Server;
import utils.Config;
import utils.ConfigManager;
import utils.Log;


public class MainServer {
	public static void main(String[] args) {
		try {

			new ConfigManager();
			Log.debug("Starting server...");

			Server s = new Server(Config.SERVER_PORT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
