package main;

import java.io.IOException;

import server.Server;
import utils.ClientConfig;
import utils.ClientConfigManager;
import utils.ServerLog;


public class MainServer {
	public static void main(String[] args) {
		try {

			new ClientConfigManager();
			ServerLog.debug("Starting server...");

			Server s = new Server(ClientConfig.SERVER_PORT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
