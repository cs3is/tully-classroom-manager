package main;

import java.io.IOException;

import server.Server;

public class MainServer {
	public static void main(String[] args){
		try {
			Server s = new Server(25565);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
