package NEWServer;

import java.io.IOException;

import oldServer.Server;
import shared.utils.Log;
import utils.ServerConfig;

public class MainServer {
	
	public static void main(String[] args) {
		
		try {
			
			Log.info("Initialized the server");
			
			Server s = new Server(ServerConfig.SERVER_PORT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
