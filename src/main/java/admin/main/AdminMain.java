package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import utils.AdminConfigManager;
import utils.AdminLog;
import utils.ClientLog;
import graphics.AdminTray;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.Task;
import graphics.AdminGui;

public class AdminMain extends Application {

	private static Socket connection;
	ObjectOutputStream out;


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
					
					
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	});
	
	public void requestQuestionList() throws IOException{
		out.writeObject(new Task(Task.GET_QUESTION_LIST));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
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

}
