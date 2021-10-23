package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelpBoxView {
	
	@FXML
	private ImageView helpImage;
	@FXML
	private Button closeButton;
	
	public void setUp(String sceneName) {
		Image helpImageSource = new Image(getClass().getResourceAsStream("/resources/"+sceneName+"_helpImage.png"));
		helpImage.setImage(helpImageSource);
	}
	
	public void closeHelpWindow(ActionEvent event) {
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}

}
