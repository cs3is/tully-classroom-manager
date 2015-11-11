package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Questions.Question;
import util.Task;
import utils.ClientConfig;
import utils.ClientConfigManager;
import utils.Log;
import utils.ServerConfigManager;
import utils.ServerLog;

public class Server implements Runnable {

	private ServerSocket serverSocket;
//	private ServerSocket serverSocket2;
	private HashMap<String, Integer> computerList = new HashMap<String, Integer>();
	private HashMap<Integer, UserInformation> connectedClients = new HashMap<Integer, UserInformation>();
	private static Queue<Question> questionList = new LinkedList<Question>();
	private String fileLocation = "src/main/java/server/ComputerList.txt";
	private Scanner scan, line;

	public Server(int port) throws IOException {
		loadComputers(fileLocation);
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				// ServerLog.debug("Waiting for client... (port: " + ServerConfigManager.getStr("SERVER_PORT") + ")"); 
				//TODO uncomment this when the debug mode is turned off
				Socket connection = serverSocket.accept();
//				Socket connection2 = serverSocket.accept();
				ServerLog.debug("Connected to: " + connection.getRemoteSocketAddress());

				ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

//				ObjectOutputStream out2 = new ObjectOutputStream(connection2.getOutputStream());
//				ObjectInputStream in2 = new ObjectInputStream(connection2.getInputStream());

				
				if (!computerList.keySet().contains(connection.getLocalAddress().getHostName())) {
					ServerLog.info("Refused Connection from " + connection.getLocalAddress().getHostName());
					out.writeObject(new Task(Task.SEND_NOTIFICATION,
							"Connection refused by server, please contact a system administrator"));
					connection.close();
				} else {
					UserInformation u = new UserInformation((String) in.readObject(), connection.getLocalAddress()
							.getHostName(), in, out);
					Thread t = new Thread(new UserListener(u, connection));
					t.start();
					out.writeObject(new Task(Task.SEND_NOTIFICATION, "Connection accepted by server"));
					connectedClients.put(computerList.get(connection.getLocalAddress().getHostName()), u);
				}
				Thread.sleep(500);
				out.reset();
				// System.out.println(connection.getLocalAddress().getHostName());

				/*
				 * if not on list connection.close(); else { new user cl =new ClientListener(user); Thread t = new Thread(cl); t.start(); }
				 */
			} catch (Exception e) {

			}
		}
	}

	private void loadComputers(String fileLocation) {
		String key;
		Integer value;
		try {
			scan = new Scanner(new File(fileLocation));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {
			line = new Scanner(scan.nextLine()).useDelimiter(",");
			value = Integer.parseInt(line.next());
			key = line.next();
			computerList.put(key, value);
		}
		ServerLog.debug("Loaded the list of computers");
	}

	public HashMap<Integer, UserInformation> getConnectedClients() {
		return connectedClients;
	}

	public static Queue<Question> getQuestionList() {
		return questionList;
	}

}
