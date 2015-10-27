package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.ClientListener;
import graphics.ClientTray;
import utils.ClientConfigManager;
import utils.ServerLog;

public class MainClient {
	// public static MainFrame f;

	public static void main(String[] args) {

		new ClientConfigManager();

		// f = new MainFrame();
		try {
			System.out.println("Attempting to connect to server");

			Socket connection = new Socket(ClientConfigManager.getStr("SERVER_IP"), ClientConfigManager.getInt("SERVER_PORT"));

			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

			ClientTray to = new ClientTray(out, in);
			ClientListener cl = new ClientListener(out, in);
			// ClientListener cl = new ClientListener(isFromServer,to);
			
		} catch (Exception e) {
			e.printStackTrace();
			ServerLog.fatal("An error occurred");
			System.exit(0);
		}

	}

}
