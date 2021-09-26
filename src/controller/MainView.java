package controller;

import application.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MainView extends Controller{

	@FXML
	private Button gameButton;
	@FXML
	private Button quitButton;
	
	public void playGame(ActionEvent play) {
		switchScene(play, "TopicList.fxml");
	}
	
	public void quitGame(ActionEvent quit) {
		if (AlertBox.display("Are you sure you want to quit?", "The window will close immediately.")) {
			System.exit(0);
		}
	}
	
}
