package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import quiz.AnswerAttemptTracker;
import quiz.AnswerStatusTracker;
import quiz.ScoreTracker;

public class PracticeResultsView {
	
	@FXML
	private Button viewUpButton, viewDownButton, viewStatsButton, repracticeButton, backButton;
	@FXML
	private Label wordOne, wordTwo, wordThree, wordFour, wordFive;
	@FXML
	private Label wordOneAttemptLabel, wordTwoAttemptLabel, wordThreeAttemptLabel, wordFourAttemptLabel, wordFiveAttemptLabel;
	@FXML
	private ImageView wordOneStatus, wordTwoStatus, wordThreeStatus, wordFourStatus, wordFiveStatus;
	
	public void setUp(ScoreTracker scoreTracker, AnswerAttemptTracker answerAttemptTracker, AnswerStatusTracker answerStatusTracker) {
		
	}

}
