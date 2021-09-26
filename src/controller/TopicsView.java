package controller;

import java.util.ArrayList;

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
	private ArrayList<ToggleButton> toggles = new ArrayList<ToggleButton>();
	
	@FXML
	private void initialize() {
		toggles.add(colourButton);
		toggles.add(weatherButton);
		toggles.add(dayOneButton);
		toggles.add(dayTwoButton);
		toggles.add(monthOneButton);
		toggles.add(monthTwoButton);
		toggles.add(babyButton);
		toggles.add(workButton);
		toggles.add(feelingButton);
		toggles.add(compassPointButton);
		toggles.add(universityButton);
		toggles.add(softwareButton);
	}
	
	public void toggleTopic(ActionEvent event){
		ToggleButton selectedTopic = (ToggleButton) event.getSource();
		String id = selectedTopic.getId();
		if (selectedTopic.isSelected()) {
			selectedTopic.setGraphic(new ImageView(onButtonImage));
			addList(id);
		} else if (!selectedTopic.isSelected()){
			selectedTopic.setGraphic(new ImageView(offButtonImage));
			removeList(id);
		}
	}
	
	public void startGame(ActionEvent start) {
		int numTopics = 0;
		for(ToggleButton topic : toggles) {
			if (topic.isSelected()) {
				numTopics++;
			}
		}
		if (numTopics == 0) {
			startWarning.setVisible(true);
		} else {
			switchScene(start, "GameScreen.fxml");
		}
	}	
	
}
