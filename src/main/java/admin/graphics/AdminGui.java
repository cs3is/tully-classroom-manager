package graphics;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminGui extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			System.out.println("creating the file");
			FileWriter write = new FileWriter("fml.txt",true);
			PrintWriter print = new PrintWriter(write);
			print.print("this sucks");
			System.out.println("created the file");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StackPane page = (StackPane) FXMLLoader.load(getClass().getResource("gui.fxml"));
		Scene scene = new Scene(page);
		primaryStage.setScene(scene);
		primaryStage.setTitle("FXML is Simple");
		primaryStage.show();
	}
	
	public AdminGui(String[] args){
//		System.out.println("GETLAUNCHED");
		launch(args);
	}

}
