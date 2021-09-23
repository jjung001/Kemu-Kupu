package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TopicsView extends Controller {
	
	@FXML
	private Button colour;
	@FXML
	private Button weather;
	@FXML
	private Button daysOfWeekOne;
	@FXML
	private Button daysOfWeekTwo;
	@FXML
	private Button monthsOfYearOne;
	@FXML
	private Button monthsOfYearTwo;
	@FXML
	private Button baby; 
	@FXML 
	private Button work;
	@FXML
	private Button feeling;
	@FXML
	private Button compassPoint;
	@FXML
	private Button university;
	@FXML
	private Button software;
	@FXML
	private Button back; 
	@FXML
	private Button start;
	
	public void startGame(ActionEvent start) {
		switchScene(start, "GameScreen.fxml");
	}
	

}
