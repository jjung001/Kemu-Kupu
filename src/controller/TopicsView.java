package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TopicsView extends Controller{

	@FXML
	private ToggleButton colour;
	@FXML
	private ToggleButton weather;
	@FXML
	private ToggleButton dayOfWeekOne;
	@FXML
	private ToggleButton dayOfWeekTwo;
	@FXML
	private ToggleButton monthOfYearOne;
	@FXML
	private ToggleButton monthOfYearTwo;
	@FXML
	private ToggleButton baby;
	@FXML
	private ToggleButton work;
	@FXML
	private ToggleButton feeling;
	@FXML
	private ToggleButton compassPoint;
	@FXML
	private ToggleButton university;
	@FXML
	private Button software;
	@FXML
	private Button start;
	@FXML
	private Button back;
	
	public void startGame(ActionEvent start) {
		switchScene(start, "GameScreen.fxml");
	}
	
	public void toggleTopic(ActionEvent event){
		ToggleButton pressedButton = (ToggleButton) event.getSource();
		String id = pressedButton.getId();
		pressedButton.turnOnOrOff(id);
		//updateWordList(pressedButton.getState());
	}
}
