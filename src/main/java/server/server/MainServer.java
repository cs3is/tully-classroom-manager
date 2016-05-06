package server.server;

import java.io.IOException;

import shared.utils.Log;

public class MainServer {
	
	public static void main(String[] args) {
		
		try {
			
			Log.info("Initializing server");
			
			
			Server s = new Server(25565);//TODO implement config

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
