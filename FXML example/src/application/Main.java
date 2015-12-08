package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	
	@FXML private Button btn;
	
	@Override
	public void start(Stage primaryStage) {
		// try {
		// BorderPane root = new BorderPane();
		// Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("application.fxml").toExternalForm());
		// scene.setRoot(FXMLLoader.load(getClass().getResource("application.fxml")));
		// primaryStage.setScene(scene);
		// primaryStage.show();
		// } catch(Exception e) {
		// e.printStackTrace();
		// }
		// System.out.println("finished starting stuff");

		try {
			System.out.println("program is starting");
			StackPane page = (StackPane) FXMLLoader.load(getClass().getResource("application.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.setTitle("FXML is Simple");
			primaryStage.show();
			System.out.println("program has started");
			System.out.println("defining objects");
			
//			page.getChildren().add(btn);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
	private void buttonListener(){
		System.out.println("button was pushed");
	}
	
}
