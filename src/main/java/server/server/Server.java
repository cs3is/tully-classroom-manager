package server.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import server.listener.ServerListener;
import shared.networking.Task;
import shared.networking.TaskEnum;
import shared.res.ConnectionData;
import shared.res.Question;
import shared.utils.Log;

public class Server implements Runnable {

	// TODO
	// http://www.javacodegeeks.com/2012/10/create-new-message-notification-pop-up.html
	// follow that link in order to create fabulous pop up boxes for alerts!

	private ServerSocket serverSocket;

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
	private static ArrayList<HashMap<Integer, ConnectionData>> connectedClients = new ArrayList<HashMap<Integer, ConnectionData>>();
	/**
	 * An ArrayList that contains a Queue of questions for each classroom.
	 */
	private static ArrayList<LinkedList<Question>> questionList = new ArrayList<LinkedList<Question>>();
	/**
	 * The location of all of the computers that will be able to connect to the
	 * server.
	 */
	private String fileLocation = "src/main/java/server/res/ComputerList.txt";

	int numberOfTeachers = 9;

	private Scanner scan, line;

	public Server(int port) throws IOException {
		loadComputers(fileLocation);
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);

		Log.info("Created the serverSocket");

		Thread t = new Thread(this);
		t.start();
		for (int i = 0; i < numberOfTeachers; i++) {
			connectedClients.add(new HashMap<Integer, ConnectionData>());
		}
	}

	@Override
	public void run() {
		Boolean admin = false;
		Boolean connectionAccepted = false;
		Integer selectedClass = -1;

		Log.info("Started the main thread");

		while (true) {
			try {

				Socket connection = serverSocket.accept();
				Log.debug("Connected to: " + connection.getRemoteSocketAddress());

				ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

				ConnectionData conData = new ConnectionData(connection, selectedClass, connectionAccepted, admin, in,
						out);

				checkConnectionDetails(conData);

				if (!conData.getConnectionAccepted()) {

					refuseConnection(conData);

				} else {

					if (!conData.getAdmin()) {

						acceptClient(conData);

					} else {

						acceptAdmin(conData);
					}

					admin = false;
					connectionAccepted = false;
					selectedClass = -1;

				}
				Thread.sleep(500);
				out.flush();
				out.reset();

			} catch (SocketTimeoutException e) {

			} catch (Exception e) {
				Log.error("Error in the main server thread");
				e.printStackTrace();
			}
		}
	}

	/**
	 * This for loop goes through the list of the clients that are able to
	 * connect to the server, and sees if the client that is trying to connect
	 * should be allowed. Also determines what class the user connecting belongs
	 * to.
	 */
	private void checkConnectionDetails(ConnectionData conData) {
		for (int i = 0; i < 2; i++) {
			if (computerList.get(i).keySet().contains(conData.getConnection().getLocalAddress().getHostName())) {
				conData.setConnectionAccepted(true);
				conData.setSelectedClass(i);
				if (computerList.get(i).get(conData.getConnection().getLocalAddress().getHostName()) == 1000) {
					conData.setAdmin(true);
				}
			}

		}
	}

	private void acceptClient(ConnectionData conData) throws ClassNotFoundException, IOException {
		Thread t = new Thread(new ServerListener(conData));
		t.start();
		Log.debug("sending \"Connection accepted by server\"");
		conData.getOut().writeObject(new Task(TaskEnum.S_SEND_NOTIFICATION, "Connection accepted by server"));

		connectedClients.get(conData.getSelectedClass()).put(computerList.get(conData.getSelectedClass())
				.get(conData.getConnection().getLocalAddress().getHostName()), conData);
	}

	private void acceptAdmin(ConnectionData conData) throws ClassNotFoundException, IOException {
		System.out.println("an admin has been detected");
		// AdminInformation u = new AdminInformation(conData.getSelectedClass(),
		// (String) conData.getIn().readObject(),
		// conData.getConnection().getLocalAddress().getHostName(),
		// conData.getIn(), conData.getOut());
		System.out.println("admininformation has been created");
		Thread t = new Thread(new ServerListener(conData));
		t.start();
		Log.debug("sending \"Connection accepted by server\"");
		conData.getOut().writeObject(new Task(TaskEnum.S_SEND_NOTIFICATION, "Connection accepted by server"));

		connectedClients.get(conData.getSelectedClass()).put(computerList.get(conData.getSelectedClass())
				.get(conData.getConnection().getLocalAddress().getHostName()), conData);

	}

	private void refuseConnection(ConnectionData conData) throws IOException {
		Log.info("Refused Connection from " + conData.getConnection().getLocalAddress().getHostName());
		Log.debug("sending \"Connection refused by server, please contact a system administrator\"");
		conData.getOut().writeObject(new Task(TaskEnum.S_SEND_NOTIFICATION,
				"Connection refused by server, please contact a system administrator"));
		Log.debug("sent \"Connection refused by server, please contact a system administrator\"");
		conData.getConnection().close();
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
		Log.info("Loaded the list of computers");
	}

	public static LinkedList<Question> getQuestionList(int index) {
		return questionList.get(index);
	}

	public static ArrayList<HashMap<Integer, ConnectionData>> getConnectedClients() {
		return connectedClients;
	}

}
