package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {

	private ServerSocket serverSocket;

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}

	@Override
	public void run() {
		while (true) {
			try {
				
			} catch (Exception e) {

			}
		}
	}
}
