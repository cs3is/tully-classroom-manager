package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import graphics.ClientTray;
import utils.ConfigManager;

public class MainClient {
	// public static MainFrame f;

	public static void main(String[] args) {

		new ConfigManager();

		// f = new MainFrame();
		try {
			System.out.println("Attempting to connect to server");

			Socket connection = new Socket(ConfigManager.getStr("IP"), ConfigManager.getInt("PortNumber"));

			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(connection.getInputStream());

			ClientTray to = new ClientTray(output, input);

			// ClientListener cl = new ClientListener(isFromServer,to);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

}
