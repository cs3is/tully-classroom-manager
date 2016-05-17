package server.main;

import java.io.IOException;

import shared.utils.Log;

public class MainServer {
	
	public static void main(String[] args) {
		
		try {
			-1
			Log.info("Initializing server");
			
			
			Server s = new Server(25565);//TODO implement config

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
