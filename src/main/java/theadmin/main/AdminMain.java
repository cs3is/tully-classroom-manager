package theadmin.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import shared.res.ConnectionData;
import shared.res.Question;
import shared.utils.Log;
import theadmin.graphics.AdminGui;
import theadmin.graphics.AdminTray;
import theadmin.listener.AdminListener;
import theadmin.res.AdminConfigManager;

public class AdminMain {

	private static Socket connection;

	static AdminListener al;
	static AdminButtons ab;
	/**
	 * An ArrayList that contains a Queue of questions for each classroom.
	 */
	public static LinkedList<Question> questionList = new LinkedList<Question>();

	private static String userName;

	private static ConnectionData conData;

	public static void main(String[] args) {

		new AdminConfigManager();

		try {
			Log.info("Attempting to connect to server");

			connection = new Socket(AdminConfigManager.getStr("SERVER_IP"), AdminConfigManager.getInt("SERVER_PORT"));
			Log.info("Connected to - " + AdminConfigManager.getStr("SERVER_IP") + " on port: "
					+ AdminConfigManager.getStr("SERVER_PORT"));

			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			Log.debug("Created output stream 1 from connection");
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

			conData = new ConnectionData(connection, null, null, true, in, out);

			ab = new AdminButtons(conData);

			AdminTray T = new AdminTray();

			userName = System.getProperty("user.name");
			try {
				out.writeObject(userName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			AdminGui.main(args);

			al = new AdminListener(conData);
			System.out.println("after");

		} catch (Exception e) {
			e.printStackTrace();
			Log.error("A fatal error occurred");
			System.exit(0);
		}

	}

	Thread t = new Thread(new Runnable() {

		@Override
		public void run() {

			while (true) {
				try {

					requestQuestionList();
					al = new AdminListener(conData);

					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	});

	public void requestQuestionList() throws Exception {
		// TODO sent the object
		// ad.out.writeObject(new Task(Task.GET_QUESTION_LIST));
		Thread.sleep(500);
	}
	@Deprecated
	public static ConnectionData getAdminData(){
		return conData;
	}
	public static ConnectionData getConnectionData(){
		return conData;
	}
	public static AdminButtons getAdminButtons(){
		return ab;
	}



}
