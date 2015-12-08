package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainForm implements javafx.fxml.Initializable{

	@FXML
	TextField txt;
	Button btn;
	
	EventHandler<Event> e = new EventHandler<Event>(){

		@Override
		public void handle(Event arg0) {
			// TODO Auto-generated method stub
			
			
		}
		
	};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("consider it initialized");
		
		btn.setOnAction(null);
		txt.setOnAction(null);
		
	}

}
