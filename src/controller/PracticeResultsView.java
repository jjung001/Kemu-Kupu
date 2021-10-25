package controller;

import java.util.ArrayList;

import application.HelpBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
	
	private ResultsModel resultsModel; 
	private ScoreTracker scoreTracker;
	private int numberOfQuestions;
	private String[] wordsTested;
	private String[] wordsAttempt;
	private Image[] wordsStatusImage;	
	private ArrayList<Label> wordsTestedLabel = new ArrayList<Label>();
	private ArrayList<Label> wordsAttemptLabel = new ArrayList<Label>();
	private ArrayList<ImageView> wordsStatusImageView = new ArrayList<ImageView>();
	
	public void setUp(ScoreTracker scoreTracker, AnswerAttemptTracker answerAttemptTracker, AnswerStatusTracker answerStatusTracker, int numberOfQuestions) {
		this.resultsModel = new ResultsModel(scoreTracker, answerAttemptTracker, answerStatusTracker);
		this.scoreTracker = scoreTracker;
		
		initialiseArrayList();
		
		this.numberOfQuestions = numberOfQuestions;
		wordsTested = new String[numberOfQuestions];
		wordsAttempt = new String[numberOfQuestions];
		wordsStatusImage = new Image[numberOfQuestions];
		
		for (int i = 0; i < numberOfQuestions; i++) {
			wordsTested[i] = scoreTracker.getWord(i+1);
			wordsAttempt[i] = resultsModel.getAttempt(i+1);
			wordsStatusImage[i] = resultsModel.getStatusImage(i+1);
		}
		
		viewUpButton.setDisable(true);
		
		if(numberOfQuestions < 6) {
			viewDownButton.setDisable(true);
		}
		
		setScreenOne();
	}
	
	public void changeWordList(ActionEvent event) {
		Button buttonClicked = (Button) event.getSource();
		String buttonID = buttonClicked.getId();
		
		if(buttonID.equals("viewUpButton")) {
			setScreenOne();		
		} else {
			setScreenTwo();
		}
	}
	
	private void setScreenOne() {
		
		viewUpButton.setDisable(true);
		if (numberOfQuestions > 5) {
			viewDownButton.setDisable(false);
		}
		
		for (int j = 0; j < wordsTestedLabel.size(); j++) {
			wordsTestedLabel.get(j).setText(wordsTested[j]);
			wordsAttemptLabel.get(j).setText(wordsAttempt[j]);
			wordsStatusImageView.get(j).setVisible(true);
			wordsStatusImageView.get(j).setImage(wordsStatusImage[j]);
		}
	}
	
	public void setScreenTwo() {
		
		viewUpButton.setDisable(false);
		viewDownButton.setDisable(true);
		
		for (int k = 5; k < wordsTested.length; k++) {
			wordsTestedLabel.get(k-5).setText(wordsTested[k]);
			wordsAttemptLabel.get(k-5).setText(wordsAttempt[k]);
			wordsStatusImageView.get(k-5).setImage(wordsStatusImage[k]);
		}
		
		int numberOfWordsInScreenTwo = numberOfQuestions - wordsTestedLabel.size();
		for (int m = numberOfWordsInScreenTwo; m < wordsTestedLabel.size(); m++) {
			wordsTestedLabel.get(m).setText("");
			wordsAttemptLabel.get(m).setText("");
			wordsStatusImageView.get(m).setVisible(false);
		}
	}
	
	private void initialiseArrayList() {
		wordsTestedLabel.add(wordOne);
		wordsTestedLabel.add(wordTwo);
		wordsTestedLabel.add(wordThree);
		wordsTestedLabel.add(wordFour);
		wordsTestedLabel.add(wordFive);
		wordsAttemptLabel.add(wordOneAttemptLabel);
		wordsAttemptLabel.add(wordTwoAttemptLabel);
		wordsAttemptLabel.add(wordThreeAttemptLabel);
		wordsAttemptLabel.add(wordFourAttemptLabel);
		wordsAttemptLabel.add(wordFiveAttemptLabel);
		wordsStatusImageView.add(wordOneStatus);
		wordsStatusImageView.add(wordTwoStatus);
		wordsStatusImageView.add(wordThreeStatus);
		wordsStatusImageView.add(wordFourStatus);
		wordsStatusImageView.add(wordFiveStatus);
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
