package theClient.main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.ClientData;
import client.ClientListener;
import theClient.graphics.ClientTray;
import theClient.res.ClientConfigManager;
import util.Task;
import utils.ClientLog;
import utils.ServerLog;

public class MainClient {

	private static Socket connection;

	public static void main(String[] args) {

		new ClientConfigManager();

		try {
			ClientLog.info("Attempting to connect to server");

			connection = new Socket(ClientConfigManager.getStr("SERVER_IP"), ClientConfigManager.getInt("SERVER_PORT"));
			ClientLog.info("Connected to - " + ClientConfigManager.getStr("SERVER_IP") + " on port: "
					+ ClientConfigManager.getStr("SERVER_PORT"));

			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			ClientLog.debug("Created output stream 1 from connection");
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

			ClientTray to = new ClientTray(new ClientData(in, out));

			// ClientListener cl = new ClientListener(isFromServer,to);

		} catch (Exception e) {
			e.printStackTrace();
			ClientLog.fatal("An error occurred");
			System.exit(0);
		}

	}

}