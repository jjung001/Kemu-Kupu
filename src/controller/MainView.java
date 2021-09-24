package controller;

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
		System.exit(0);
	}
	
}
