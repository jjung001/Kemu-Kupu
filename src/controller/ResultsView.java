package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ResultsModel;

public class ResultsView extends Controller{

	@FXML
	private Button playAgainButton;
	@FXML
	private Button backButton;
	@FXML
	private Label scoreLabel;
	@FXML
	private Label levelLabel;
	@FXML
	private ImageView treeImage;
	@FXML
	LineChart<String, Number> scoreChart;
	
	private String scoreDisplay;
	private String level;
	private int totalScore;
	
	public void initialize () {
		totalScore = getTotalScore();
		scoreDisplay = String.valueOf(totalScore);
		scoreLabel.setText(scoreDisplay);
		
		ResultsModel model = new ResultsModel();
		
		level = model.determineLevel(totalScore);
		levelLabel.setText(level);
		
		model.displayImage(level);
		treeImage.setImage(model.displayImage(level));
		
		model.setChart(scoreChart);
	}
	
	public void playAgain(ActionEvent event) {
		switchScene(event, "TopicList.fxml");
	}

}
