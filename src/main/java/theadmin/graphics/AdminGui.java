package theadmin.graphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sun.glass.ui.Screen;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.stage.Window;
import shared.utils.Log;

public class AdminGui extends Application {

	List<Screen> screens = Screen.getScreens();

	protected final int xRes = 900;
	protected final int yRes = 900;

	Stage primaryStage;

	protected AnchorPane mainPane;

	protected MenuBar menu;
	protected Menu menu_File;

	protected TabPane tabPane;
	protected Tab tab1;
	protected Tab tab2;

	protected AnchorPane question_Pane;
	protected ScrollPane question_ScrollPane;
	protected Canvas question_Canvas;
	protected Button question_Clear;
	protected Button question_ClearAll;


	public AdminGui(){
	}
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;

		primaryStage.setWidth(xRes);
		primaryStage.setHeight(yRes);

		mainPane = new AnchorPane();
		mainPane.setPrefSize(primaryStage.getWidth(), primaryStage.getHeight());

		createQuestionPanel();

		createTabPane();

		createMenu();

		Scene scene = new Scene(mainPane, xRes, yRes);



		primaryStage.setTitle("Tully classroom manager");
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	protected void setAnchorBounds(Node nodeToAnchor, Double left, Double right, Double top, Double bottom) {
		if (left != null)
			AnchorPane.setLeftAnchor(nodeToAnchor, left);
		if (right != null)
			AnchorPane.setRightAnchor(nodeToAnchor, right);
		if (top != null)
			AnchorPane.setTopAnchor(nodeToAnchor, top);
		if (bottom != null)
			AnchorPane.setBottomAnchor(nodeToAnchor, bottom);
	}

	protected void createMenu() {
		menu = new MenuBar();
		setAnchorBounds(menu, 0.0, 0.0, 0.0, null);
		menu_File = new Menu();
		menu_File.setText("File");
		menu.getMenus().add(menu_File);
		mainPane.getChildren().add(menu);
	}

	protected void createTabPane() {
		tabPane = new TabPane() {
			{
				setPrefWidth(mainPane.getWidth() - question_Pane.getWidth());
				this.setTabMaxWidth(100);
				this.setTabMinWidth(100);
				setAnchorBounds(this, 0.0, 300.0, 25.0, 0.0);

			}
		};

		tab1 = new Tab("test") {
			{
				setClosable(false);
			}
		};
		tab2 = new Tab("test2") {
			{
				setClosable(false);
			}
		};

		tabPane.getTabs().addAll(new ArrayList<Tab>() {
			{
				add(tab1);
				add(tab2);
			}
		});

		mainPane.getChildren().add(tabPane);
	}

	protected void createQuestionPanel() {
		question_Pane = new AnchorPane();
		question_Pane.setPrefSize(300, yRes);
		setAnchorBounds(question_Pane, null, 0.0, 0.0, 0.0);

		question_ScrollPane = new ScrollPane() {
			{
				setVbarPolicy(ScrollBarPolicy.ALWAYS);
				setAnchorBounds(this, 0.0, 0.0, 25.0, 25.0);
			}
		};

		/**
		   try {
				AdminMain.getAdminData.getOut().writeObject(new Task(Task.SCREENSHOT));
			} catch (IOException e) {

			}
		 */

		question_Clear = new Button("Clear") {
			{
				setAnchorBounds(this, 0.0, 150.0, null, 0.0);
				setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						Log.info("clearing the next item on the list");
						 		try {
						 	//		ab.removeQuestion(ad.out); TODO this
						 		} catch (Exception e) {
						 			e.printStackTrace();
						 		}

						// TODO ADD BUTTON COMMANDS

					}
				});

			}
		};

		question_ClearAll = new Button("Clear All") {
			{
				setAnchorBounds(this, 150.0, 0.0, null, 0.0);
				setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO ADD BUTTON COMMANDS

					}
				});
			}
		};

		question_Pane.getChildren().addAll(new ArrayList<Node>() {
			{
				add(question_ScrollPane);
				add(question_Clear);
				add(question_ClearAll);
			}
		});

		mainPane.getChildren().add(question_Pane);

	}
	public AdminGui(String[] args){
//		AdminLog.info("GETLAUNCHED");
		launch(args);
	}

}



