package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import utils.AdminConfigManager;
import utils.AdminLog;
import utils.ClientLog;
import graphics.AdminTray;
import graphics.AdminGui;

public class AdminMain{

	private static Socket connection;

	public static void main(String[] args) {

		new AdminConfigManager();

		try {
			System.out.println("Attempting to connect to server");

			connection = new Socket(AdminConfigManager.getStr("SERVER_IP"), AdminConfigManager.getInt("SERVER_PORT"));
			AdminLog.info("Connected to - " + AdminConfigManager.getStr("SERVER_IP") + " on port: "
					+ AdminConfigManager.getStr("SERVER_PORT"));

			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			AdminLog.debug("Created output stream 1 from connection");
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

			AdminTray T = new AdminTray();

			new AdminGui(args);


		} catch (Exception e) {
			e.printStackTrace();
			ClientLog.fatal("An error occurred");
			System.exit(0);
		}

	}

}
