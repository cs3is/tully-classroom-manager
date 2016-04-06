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
			
			ServerLog.info("Initializing server");
			
			
			Server s = new Server(ServerConfigManager.getInt("SERVER_PORT"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
