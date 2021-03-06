package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import Questions.Question;
import util.Task;
import utils.ServerLog;

public class Server implements Runnable {

	// TODO
	// http://www.javacodegeeks.com/2012/10/create-new-message-notification-pop-up.html
	// follow that link in order to create fabulous pop up boxes for alerts!

	private ServerSocket serverSocket;
	// private ServerSocket serverSocket2;

	/**
	 * An ArrayList that contains a HashMap of clients that will be able to
	 * connect to each computer science classroom. For every classroom, there
	 * will be an index in the array, enabling the program to better keep track
	 * of all of the clients. The number of indexes will be determined by the
	 * number of dashes in the ComputerList.txt file, as each dash shall
	 * represent the end of a class.
	 */
	ArrayList<HashMap<String, Integer>> computerList = new ArrayList<HashMap<String, Integer>>();

	/**
	 * An ArrayList that contains a HashMap of all of the clients that are
	 * connected to the server from each classroom. Each classroom will have
	 * it's own index in the array.
	 */
	private static ArrayList<HashMap<Integer, Info>> connectedClients = new ArrayList<HashMap<Integer, Info>>();
	/**
	 * An ArrayList that contains a Queue of questions for each classroom.
	 */
	private static ArrayList<LinkedList<Question>> questionList = new ArrayList<LinkedList<Question>>();
	/**
	 * The location of all of the computers that will be able to connect to the
	 * server.
	 */
	private String fileLocation = "src/main/java/server/ComputerList.txt";

	int numberOfTeachers = 0;

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
		boolean admin = false;
		boolean connectionAccepted = false;
		int selectedClass = -1;
		while (true) {
			try {
				// ServerLog.debug("Waiting for client... (port: " +
				// ServerConfigManager.getStr("SERVER_PORT") + ")");
				// TODO uncomment this when the debug mode is turned off
				Socket connection = serverSocket.accept();
				ServerLog.debug("Connected to: " + connection.getRemoteSocketAddress());

				ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

				/**
				 * This for loop goes through the list of the clients that are
				 * able to connect to the server, and sees if the client that is
				 * trying to connect should be allowed. Also determines what
				 * class the user connecting belongs to.
				 */
				for (int i = 0; i < 2; i++) {
					if (computerList.get(i).keySet().contains(connection.getLocalAddress().getHostName())) {
						connectionAccepted = true;
						selectedClass = i;
						if (computerList.get(i).get(connection.getLocalAddress().getHostName()) == 1000) {
							admin = true;
						}
					}

				}

				if (!connectionAccepted) {
					ServerLog.info("Refused Connection from " + connection.getLocalAddress().getHostName());
					ServerLog.debug("sending \"Connection refused by server, please contact a system administrator\"");
					out.writeObject(new Task(Task.SEND_NOTIFICATION,
							"Connection refused by server, please contact a system administrator"));
					ServerLog.debug("sent \"Connection refused by server, please contact a system administrator\"");
					connection.close();
				} else {
					if (!admin) {
						UserInformation u = new UserInformation(selectedClass, (String) in.readObject(),
								connection.getLocalAddress().getHostName(), in, out);
						Thread t = new Thread(new UserListener(u, connection));
						t.start();
						ServerLog.debug("sending \"Connection accepted by server\"");
						out.writeObject(new Task(Task.SEND_NOTIFICATION, "Connection accepted by server"));

						connectedClients.get(selectedClass).put(
								computerList.get(selectedClass).get(connection.getLocalAddress().getHostName()), u);
						admin = false;
						connectionAccepted = false;
					} else {
						System.out.println("an admin has been detected");
						AdminInformation u = new AdminInformation(selectedClass, (String) in.readObject(),
								connection.getLocalAddress().getHostName(), in, out);
						System.out.println("admininformation has been created");
						Thread t = new Thread(new UserListener(u, connection));
						t.start();
						ServerLog.debug("sending \"Connection accepted by server\"");
						out.writeObject(new Task(Task.SEND_NOTIFICATION, "Connection accepted by server"));

						connectedClients.get(selectedClass).put(
								computerList.get(selectedClass).get(connection.getLocalAddress().getHostName()), u);
						admin = false;
						connectionAccepted = false;
					}

				}
				Thread.sleep(500);
				out.reset();
				// System.out.println(connection.getLocalAddress().getHostName());

				/*
				 * if not on list connection.close(); else { new user cl =new
				 * ClientListener(user); Thread t = new Thread(cl); t.start(); }
				 */
			} catch (Exception e) {

			}
		}
	}

	@SuppressWarnings("resource")
	/**
	 * The method that is called in order to read the ComputerList.txt file and
	 * store it in the program in order to manage connections and organize all
	 * of the connected computers. It will read the file, which is arranged with
	 * the value(computer number) first in each line, followed by the key (host
	 * name of the computer), separated by a comma. When all of the computers
	 * from a classroom are listed on the list, a single dash in the next line
	 * will signify the end of that class, and potentially the start of a new
	 * one.
	 * 
	 * @param fileLocation
	 *            The location of all of the computers that will be able to
	 *            connect to the server.
	 */
	private void loadComputers(String fileLocation) {
		String key;
		Integer value;
		int selectedClass = 0;

		try {
			scan = new Scanner(new File(fileLocation));
			computerList.add(new HashMap<String, Integer>());
			questionList.add(new LinkedList<Question>());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {

			line = new Scanner(scan.nextLine()).useDelimiter(",");
			String next = line.next().trim();
			if (next.equals("-")) {
				computerList.add(new HashMap<String, Integer>());
				questionList.add(new LinkedList<Question>());
				selectedClass++;
			} else {
				value = Integer.parseInt(next);
				key = line.next();
				computerList.get(selectedClass).put(key, value);
			}
		}
		numberOfTeachers = selectedClass;
		ServerLog.debug("Loaded the list of computers");
	}

	public static LinkedList<Question> getQuestionList(int index) {
		return questionList.get(index);
	}

	public static ArrayList<HashMap<Integer, Info>> getConnectedClients() {
		return connectedClients;
	}

	/*
	 * public static Queue<Question> getQuestionList() { return questionList; }
	 */

}
