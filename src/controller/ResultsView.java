package controller;

import game.ResultsModel;
import game.ScoreTracker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private ResultsModel resultsModel;
	
	public void setUp (ScoreTracker scoreTracker) {
		
		resultsModel = new ResultsModel(scoreTracker);
		
		totalScore = scoreTracker.getTotalScore();
		scoreDisplay = String.valueOf(totalScore);
		scoreLabel.setText(scoreDisplay);
		
		level = resultsModel.determineLevel(totalScore);
		levelLabel.setText(level);
		
		resultsModel.displayImage(level);
		treeImage.setImage(resultsModel.displayImage(level));
		
		resultsModel.setChart(scoreChart);
	}
	
	public void playAgain(ActionEvent event) {
		switchScene(event, "TopicList.fxml");
	}

}
