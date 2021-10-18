package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import quiz.AnswerAttemptTracker;
import quiz.AnswerStatusTracker;
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
	private ImageView cashImage;
	@FXML
	private Label wordOneLabel, wordTwoLabel, wordThreeLabel, wordFourLabel, wordFiveLabel;
	@FXML
	private Label wordOneAttemptLabel, wordTwoAttemptLabel, wordThreeAttemptLabel, wordFourAttmeptLabel, wordFiveAttemptLabel;
	@FXML
	private ImageView wordOneStatusImage, wordTwoStatusImage, wordThreeStatusImage, wordFourStatusImage, wordFiveStatusImage;
	@FXML
	LineChart<String, Number> scoreChart;

	private int totalScore;
	private ResultsModel resultsModel;

	/**
	 * Initially sets up the controller.
	 * It calls methods from the helper model class ResultsModel to get the values and image to display. 
	 * It sets up the total score, the level and image of tree of the test, and a line chart. 
	 * 
	 * @param scoreTracker	ScoreTracker user data from the previous game screen that stores the score datas of questions
	 */
	public void setUp(ScoreTracker scoreTracker, AnswerAttemptTracker answerAttemptTracker, AnswerStatusTracker answerStatusTracker) {

		resultsModel = new ResultsModel(scoreTracker, answerAttemptTracker, answerStatusTracker);

		totalScore = scoreTracker.getTotalScore();
		scoreLabel.setText(String.valueOf(totalScore));
		cashImage.setImage(resultsModel.displayImage(totalScore));

		resultsModel.setChart(scoreChart);
	
		wordOneLabel.setText(scoreTracker.getWord(1));
		wordTwoLabel.setText(scoreTracker.getWord(2));
		wordThreeLabel.setText(scoreTracker.getWord(3));
		wordFourLabel.setText(scoreTracker.getWord(4));
		wordFiveLabel.setText(scoreTracker.getWord(5));
		
		wordOneAttemptLabel.setText(resultsModel.getAttempt(1));
		wordTwoAttemptLabel.setText(resultsModel.getAttempt(2));
		wordThreeAttemptLabel.setText(resultsModel.getAttempt(3));
		wordFourAttmeptLabel.setText(resultsModel.getAttempt(4));
		wordFiveAttemptLabel.setText(resultsModel.getAttempt(5));
		
		wordOneStatusImage.setImage(resultsModel.getStatusImage(1));
		wordTwoStatusImage.setImage(resultsModel.getStatusImage(2));
		wordThreeStatusImage.setImage(resultsModel.getStatusImage(3));
		wordFourStatusImage.setImage(resultsModel.getStatusImage(4));
		wordFiveStatusImage.setImage(resultsModel.getStatusImage(5));
		
	}

	/**
	 * Switches current scene to the topic list scene for another game. 
	 * @param event	ActionEvent from the play again button. 
	 */
	public void playAgain(ActionEvent event) {
		switchScene(event, "TopicList.fxml");
	}

}
