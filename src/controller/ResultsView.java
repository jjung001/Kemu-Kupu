package controller;

import application.Cash;
import application.FileSaveLocations;
import fileio.CashIO;
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
 * This is a controller class for the Results View screen. It displays the
 * result of the quiz taken. The informations displayed are: total score, the
 * tree level of the user, the tree image, and a graph of score progress.
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
	private Button viewTreeButton;
	@FXML
	private Label scoreLabel;
	@FXML
	private Label levelLabel;
	@FXML
	private ImageView cashImage;
	@FXML
	private Label wordOne, wordTwo, wordThree, wordFour, wordFive;
	@FXML
	private Label wordOneAttemptLabel, wordTwoAttemptLabel, wordThreeAttemptLabel, wordFourAttemptLabel,
			wordFiveAttemptLabel;
	@FXML
	private ImageView wordOneStatus, wordTwoStatus, wordThreeStatus, wordFourStatus, wordFiveStatus;
	@FXML
	LineChart<String, Number> scoreChart;

	private int totalScore;
	private ResultsModel resultsModel;
	private AnswerAttemptTracker answerAttemptTracker;
	private AnswerStatusTracker answerStatusTracker;
	private Cash cash;

	/**
	 * Initially sets up the controller. It calls methods from the helper model
	 * class ResultsModel to get the values and image to display. It sets up the
	 * total score, the level and image of tree of the test, and a line chart.
	 *
	 * @param scoreTracker ScoreTracker user data from the previous game screen that
	 *                     stores the score datas of questions
	 */

	public void setUp(ScoreTracker scoreTracker, AnswerAttemptTracker answerAttemptTracker,
			AnswerStatusTracker answerStatusTracker) {

		resultsModel = new ResultsModel(scoreTracker, answerAttemptTracker, answerStatusTracker);

		totalScore = scoreTracker.getTotalScore();
		scoreLabel.setText(String.valueOf(totalScore));
		cashImage.setImage(resultsModel.displayImage(totalScore));

		resultsModel.setChart(scoreChart);

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

		saveEarnings(totalScore);

	}

	/**
	 * Switches current scene to the topic list scene for another game.
	 *
	 * @param event ActionEvent from the play again button.
	 */
	public void playAgain(ActionEvent event) {
		switchScene(event, "TopicList.fxml");
	}

	@FXML
	public void viewTree(ActionEvent event) {
		switchScene(event, "MyTree.fxml");
	}

	private void saveEarnings(int totalScore) {
		CashIO cashIO = new CashIO(FileSaveLocations.CASH);
		cash = cashIO.loadCash();
		cash.deposit(totalScore);
		cashIO.saveCash(cash);
	}
	
}
