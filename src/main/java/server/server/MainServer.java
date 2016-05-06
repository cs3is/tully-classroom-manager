package server.server;

import java.io.IOException;

import shared.utils.Log;

public class MainServer {
	
	public static void main(String[] args) {
		
		try {
			
			Log.info("Initializing server");
			
			
			Server s = new Server(ServerConfig.SERVER_PORT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
