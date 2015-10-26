package main;

import java.io.IOException;

import server.Server;
import utils.ClientConfig;
import utils.ClientConfigManager;
import utils.ServerConfigManager;
import utils.ServerLog;


public class MainServer {
	public static void main(String[] args) {
		try {
			new ServerConfigManager();
			ServerLog.debug("Starting server...");
			Server s = new Server(25565); //TODO change port into a variable

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
