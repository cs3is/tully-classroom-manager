package main;

import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import utils.AdminConfigManager;
import utils.AdminLog;
import utils.ClientLog;
import graphics.AdminTray;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import graphics.AdminGui;

public class AdminMain extends Application{

	private static Socket connection;
	

	

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/gui.fxml")));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Load the name from the config");
			primaryStage.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
