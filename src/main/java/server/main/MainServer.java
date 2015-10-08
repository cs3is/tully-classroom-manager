package main;

import java.io.IOException;

import server.Server;
import utils.ConfigManager;

public class MainServer {
	public static void main(String[] args) {
		try {
			Server s = new Server(ConfigManager.getInt("SERVER_PORT"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
