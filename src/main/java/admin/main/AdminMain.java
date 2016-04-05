package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import Admin.AdminData;
import Admin.AdminListener;
import Questions.Question;
import utils.AdminConfigManager;
import utils.AdminLog;
import utils.ClientLog;
import graphics.AdminButtons;
import graphics.AdminTray;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import util.Task;

public class AdminMain extends Application {

	private static Socket connection;

	static AdminListener al;
	static AdminData ad;
	static AdminButtons ab;
	/**
	 * An ArrayList that contains a Queue of questions for each classroom.
	 */
	public static LinkedList<Question> questionList = new LinkedList<Question>();

	private static String userName;

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


			ad = new AdminData(in,out);
			ab = new AdminButtons(ad);

			AdminTray T = new AdminTray();

			userName = System.getProperty("user.name");
			try {
				out.writeObject(userName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			launch(args);


		} catch (Exception e) {
			e.printStackTrace();
			ClientLog.fatal("An error occurred");
			System.exit(0);
		}

	}

	Thread t = new Thread(new Runnable() {

		@Override
		public void run() {

			while (true) {
				try {

					requestQuestionList();
					al = new AdminListener(ad);

					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	});

	public void requestQuestionList() throws Exception {
		// System.out.println("calling request questionlist from the server");
		ad.out.writeObject(new Task(Task.GET_QUESTION_LIST));
		Thread.sleep(500);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/gui.fxml")));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Load the name from the config");
			primaryStage.show();

			t.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	Button potato;
	Button QUESTION_CLEAR;
	Button QUESTION_CLEAR_ALL;
	ScrollPane QUESTION_SCROLLPANE;

	@FXML
	public void QUESTION_CLEAR() {
		System.out.println("clearing the next item on the list");
	}

	@FXML
	public void QUESTION_CLEAR_ALL() {
		System.out.println("clearing EVERYTHING");
	}

	@FXML
	public void asdf() {
		AdminLog.info("yooooo");
		ab.removeQuestion(ad.out); // TODO FIX THIS ITE REMOVIGN THINGS THAT
									// DONT EXIST INT THE FIRST PLAC AND NOT
									// ERRRING.
	}
}
