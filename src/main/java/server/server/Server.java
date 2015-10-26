package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import utils.ClientConfig;
import utils.ClientConfigManager;
import utils.Log;
import utils.ServerLog;

public class Server implements Runnable {

	private ServerSocket serverSocket;

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				ServerLog.debug("Waiting for client... (port: "+25565+")"); //TODO change port to a variable
				Socket connection = serverSocket.accept();
				ServerLog.debug("Connected to: "+connection.getRemoteSocketAddress());
				ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
				Log.info(in.readObject()+"");
//				Object o = in.readObject();
//				System.out.println();
				
				connection.close();
			} catch (Exception e) {

			}
		}
	}
}
