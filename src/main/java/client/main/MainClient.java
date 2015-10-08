package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import graphics.ClientTray;
import utils.ConfigManager;
import utils.Log;

public class MainClient {
	// public static MainFrame f;

	public static void main(String[] args) {

		new ConfigManager();

		// f = new MainFrame();
		try {
			System.out.println("Attempting to connect to server");

			Socket connection = new Socket(ConfigManager.getStr("SERVER_IP"), ConfigManager.getInt("SERVER_PORT"));

			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

			ClientTray to = new ClientTray(out, in);

			// ClientListener cl = new ClientListener(isFromServer,to);
		} catch (Exception e) {
			Log.fatal("Could not connect to the server");
			System.exit(0);
		}

	}

}
