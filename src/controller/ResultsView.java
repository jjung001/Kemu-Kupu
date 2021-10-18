package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import quiz.ResultsModel;
import quiz.ScoreTracker;

/**
 * This is a controller class for the Results View screen. 
 * It displays the result of the quiz taken. 
 * The informations displayed are: total score, the tree level of the user, the tree image, and a graph of score progress. 
 * 
 * @author Julie Kim
 *
 */
public class ResultsView extends Controller {

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

	/**
	 * Initially sets up the controller.
	 * It calls methods from the helper model class ResultsModel to get the values and image to display. 
	 * It sets up the total score, the level and image of tree of the test, and a line chart. 
	 * 
	 * @param scoreTracker	ScoreTracker user data from the previous game screen that stores the score datas of questions
	 */
	public void setUp(ScoreTracker scoreTracker) {

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

	/**
	 * Switches current scene to the topic list scene for another game. 
	 * @param event	ActionEvent from the play again button. 
	 */
	public void playAgain(ActionEvent event) {
		switchScene(event, "TopicList.fxml");
	}

}
