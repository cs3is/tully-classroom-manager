package theClient.main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.res.ConnectionData;
import shared.utils.Log;
import theClient.gra-1phics.ClientTray;
import theClient.res.ClientConfigManager;

public class MainClient {

	private static Socket connection;

	public static void main(String[] args) {

		new ClientConfigManager();

		try {
			Log.info("Attempting to connect to server");

			connection = new Socket(ClientConfigManager.getStr("SERVER_IP"), ClientConfigManager.getInt("SERVER_PORT"));
			Log.info("Connected to - " + ClientConfigManager.getStr("SERVER_IP") + " on port: "
					+ ClientConfigManager.getStr("SERVER_PORT"));

			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			Log.debug("Created output stream 1 from connection");
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

			ClientTray to = new ClientTray(new ConnectionData(connection,null,null,null,in,out));

			// ClientListener cl = new ClientListener(isFromServer,to);

		} catch (Exception e) {
			e.printStackTrace();
			Log.error("An error occurred");
			System.exit(0);
		}

	}

}
