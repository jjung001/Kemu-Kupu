package controller;

import application.HelpBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import quiz.AnswerAttemptTracker;
import quiz.AnswerStatusTracker;
import quiz.ResultsModel;
import quiz.ScoreTracker;

public class PracticeResultsView extends Controller {
	
	@FXML
	private Button viewUpButton, viewDownButton, repracticeButton, backButton, helpButton;
	@FXML
	private Label wordOne, wordTwo, wordThree, wordFour, wordFive;
	@FXML
	private Label wordOneAttemptLabel, wordTwoAttemptLabel, wordThreeAttemptLabel, wordFourAttemptLabel, wordFiveAttemptLabel;
	@FXML
	private ImageView wordOneStatus, wordTwoStatus, wordThreeStatus, wordFourStatus, wordFiveStatus;
	
	ResultsModel resultsModel; 
	
	public void setUp(ScoreTracker scoreTracker, AnswerAttemptTracker answerAttemptTracker, AnswerStatusTracker answerStatusTracker) {
		resultsModel = new ResultsModel(scoreTracker, answerAttemptTracker, answerStatusTracker);
		
		setScreenOne(scoreTracker, resultsModel);
		
	}
	
	private void setScreenOne(ScoreTracker scoreTracker, ResultsModel resultModel) {
		wordOne.setText(scoreTracker.getWord(1));
		wordTwo.setText(scoreTracker.getWord(2));
		wordThree.setText(scoreTracker.getWord(3));
		wordFour.setText(scoreTracker.getWord(4));
		wordFive.setText(scoreTracker.getWord(5));
		
		wordOneAttemptLabel.setText(resultsModel.getAttempt(1));
		wordTwoAttemptLabel.setText(resultsModel.getAttempt(2));
		wordThreeAttemptLabel.setText(resultsModel.getAttempt(3));
		wordFourAttemptLabel.setText(resultsModel.getAttempt(4));
		wordFiveAttemptLabel.setText(resultsModel.getAttempt(5));
		
		wordOneStatus.setImage(resultsModel.getStatusImage(1));
		wordTwoStatus.setImage(resultsModel.getStatusImage(2));
		wordThreeStatus.setImage(resultsModel.getStatusImage(3));
		wordFourStatus.setImage(resultsModel.getStatusImage(4));
		wordFiveStatus.setImage(resultsModel.getStatusImage(5));
	}
	
	public void practiceAgain(ActionEvent event) {
		switchScene(event, "PracticeTopic.fxml");
	}
	
	public void openHelpWindow(ActionEvent event) {
		String sceneName = "PracticeResults";
		HelpBox helpBox = new HelpBox(sceneName);
		helpBox.display();
	}

}
