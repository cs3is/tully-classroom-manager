package main;

import java.io.IOException;

import server.Server;
import utils.ServerConfig;
import utils.ServerLog;

public class MainServer {
	
	public static void main(String[] args) {
		
		try {
			
			ServerLog.info("Initializing server");
			
			
			Server s = new Server(ServerConfig.SERVER_PORT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
