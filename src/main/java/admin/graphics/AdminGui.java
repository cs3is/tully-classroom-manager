package graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminGui extends Application {

	public AdminGui() {
		Application.launch(AdminGui.class, (java.lang.String[])null);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			StackPane page = (StackPane) FXMLLoader.load(AdminGui.class.getResource("Layout.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Classroom Manager");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
