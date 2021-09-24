package controller;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TopicsView extends Controller {

	@FXML
	private ToggleButton colourButton,weatherButton,dayOneButton,dayTwoButton,monthOneButton,monthTwoButton,
						 babyButton,workButton,feelingButton,compassPointButton,universityButton,softwareButton;
	@FXML
	private Button start,back;
	@FXML
	private Label startWarning;

	Image onButtonImage = new Image(getClass().getResourceAsStream("../resources/Toggle_Button_On.png"));
	Image offButtonImage = new Image(getClass().getResourceAsStream("../resources/Toggle_Button_Off.png"));
	
	public void toggleTopic(ActionEvent event){
		ToggleButton selectedTopic = (ToggleButton) event.getSource();
		String id = selectedTopic.getId();
		if (selectedTopic.isSelected()) {
			selectedTopic.setGraphic(new ImageView(onButtonImage));
			//addList(id);
		} else if (!selectedTopic.isSelected()){
			selectedTopic.setGraphic(new ImageView(offButtonImage));
			//removeList(id);
		}
	}
	
	public void startGame(ActionEvent start) {
		switchScene(start, "GameScreen.fxml");
	}	
}
