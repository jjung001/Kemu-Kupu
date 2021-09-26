package controller;

import java.util.HashMap;

import application.AlertBox;
import application.Festival;
import game.AnswerStatus;
import game.Question;
import game.Quiz;
import game.ScoreTracker;
import game.Scorer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import words.WordList;

public class GamesModuleController extends Controller {

	@FXML
	public Button btnSubmit;
	@FXML
	public Button btnIDontKnow;
	@FXML
	public Button btnA;
	@FXML
	public Button btnE;
	@FXML
	public Button btnI;
	@FXML
	public Button btnO;
	@FXML
	public Button btnU;
	@FXML
	public Button btnBack;
	@FXML
	public Button btnRepeat;
	@FXML
	public Button btnIdontKnow;
	@FXML
	public TextField wordTextField;
	@FXML
	public Label hintLabel;
	@FXML
	public Label scoreLabel;
	@FXML
	public Label questionNumLabel;
	@FXML
	public Label statusLabel;
	@FXML
	public Label speedLabel;
	@FXML
	public Label bonusLabel;
	@FXML
	public Slider speedOfSpeech;
	@FXML
	public ProgressBar bonusBar;
	@FXML
	public Timeline timeline;

	public String word;
	public String charA = "ā";
	public String charE = "ē";
	public String charI = "ī";
	public String charO = "ō";
	public String charU = "ū";
	public HashMap<Button, String> macron;
	public String secondLetter;
	public String score;
	public String questionNum;
	public String status;
	public double currentSpeed;
	public double progress;
	public Thread th;
	public boolean quitOrNot;
	public double currentBonus;
	private WordList combinedWordList;
	private Quiz quiz;
	private ScoreTracker scoreTracker;
	boolean isBeginning;
	Question currentQuestion;
	Scorer currentScorer;

	@FXML
	public void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			submitButton();
		}
	}

	@FXML
	public void quitGame(ActionEvent event) {
		// to return to Main Menu confirm exit on AlertBox
		if (AlertBox.display()) {
			backToMain(event);
		}
	}

	@FXML
	public void pressMacronButton(ActionEvent event) {
		// macron button is pressed, input macron into text field
		macron = new HashMap<>();
		macron.put(btnA, charA);
		macron.put(btnE, charE);
		macron.put(btnI, charI);
		macron.put(btnO, charO);
		macron.put(btnU, charU);
		Object currentEvent = event.getSource();
		int caretPosition = wordTextField.getCaretPosition();
		String fieldText = wordTextField.getText();
		String leftPart = fieldText.substring(0, caretPosition);
		String rightPart = fieldText.substring(caretPosition);
		wordTextField.setText(leftPart + macron.get(currentEvent) + rightPart);
		wordTextField.positionCaret(caretPosition + 1);
	}

	public void submitButton() {
		if (isBeginning) {
			// Set up the interface for accepting questions
			isBeginning = false;
			btnSubmit.setText("Submit");
			statusLabel.setText("SPELL IT:");
			toggleButtonVisibility(isBeginning);

			// Gather the first question
			getNextQuestion();
		} else {
			boolean canProceed = submitQuestion();
			wordTextField.setText("");
			if (canProceed) {
				getNextQuestion();
			}
		}
	}

	private boolean submitQuestion() {
		bonusBar.setProgress(0);
		String answer = wordTextField.getText().strip().toLowerCase();
		AnswerStatus answerStatus = currentQuestion.checkAnswer(answer);
		currentScorer.endTiming();

		switch (answerStatus) {
		case MASTERED:
			masteredWord();
			return true;
		case INCORRECT:
			incorrectWord();
			return false;
		case FAULTED:
			faultedWord();
			return true;
		case FAILED:
			failedWord();
			return true;
		default:
			return true;
		}
	}

	private void masteredWord() {
		int questionNumber = quiz.getQuestionNumber();
		int score = currentScorer.getScore();
		String word = currentQuestion.getWord();
		scoreTracker.update(questionNumber, score, word);
		scoreLabel.setText(Integer.toString(scoreTracker.getTotalScore()));
	}

	private void incorrectWord() {
		char secondCharacter = currentQuestion.getSecondLetter();
		String parsedMessage = "The second letter is '" + secondCharacter + "'.";
		hintLabel.setText(parsedMessage);
		startProgressBarCountdown();
		currentScorer.startTiming();

	}

	private void faultedWord() {
		int questionNumber = quiz.getQuestionNumber();
		int score = currentScorer.getFaultedScore();
		String word = currentQuestion.getWord();
		scoreTracker.update(questionNumber, score, word);
		scoreLabel.setText(Integer.toString(scoreTracker.getTotalScore()));
	}

	private void failedWord() {
		int questionNumber = quiz.getQuestionNumber();
		int score = 0;
		String word = currentQuestion.getWord();
		scoreTracker.update(questionNumber, score, word);
		scoreLabel.setText(Integer.toString(scoreTracker.getTotalScore()));
	}

	private void getNextQuestion() {
		if (quiz.hasNextQuestion()) {
			hintLabel.setText("");
			currentQuestion = quiz.getNextQuestion();
			currentScorer = new Scorer(currentQuestion.getWord());
			speak("Spell the word.");
			sayWord();
			startProgressBarCountdown();
			currentScorer.startTiming();

			int questionNumber = quiz.getQuestionNumber();
			int totalNumberOfQuestions = quiz.getTotalNumberOfQuestions();
			questionNumLabel.setText(questionNumber + " of " + totalNumberOfQuestions);
		} else {
			System.out.println("NO NEXT QUESTION"); // DEBUG
			// TODO Pause for a bit...
//			Stage primaryStage = (Stage) scoreLabel.getScene().getWindow();
//			primaryStage.setUserData(scoreTracker);
//			switchScene(event, "ResultScreen.fxml");
		}
	}

	public void setUp(WordList combinedWordList) {
		// labels show according to progress of game
		currentSpeed = speedOfSpeech.getValue();

		// Initialize models for spelling quiz
		quiz = new Quiz(combinedWordList.getAllWords());
		scoreTracker = new ScoreTracker(5);

		// Set up interface prior to start of game
		isBeginning = true;
		btnSubmit.setText("Start");
		bonusBar.setProgress(0);
		statusLabel.setText("Press \"Start\"!");
		questionNumLabel.setText("");
		scoreLabel.setText("0");
		toggleButtonVisibility(isBeginning);
	}

	private void toggleButtonVisibility(boolean visibilityState) {
		btnIDontKnow.setDisable(visibilityState);
		wordTextField.setDisable(visibilityState);
		btnA.setDisable(visibilityState);
		btnE.setDisable(visibilityState);
		btnI.setDisable(visibilityState);
		btnO.setDisable(visibilityState);
		btnU.setDisable(visibilityState);
	}

	private void startProgressBarCountdown() {
		bonusBar.setProgress(1.0);
		bonusBar.progressProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				double progress = newValue == null ? 0 : newValue.doubleValue();
				if (progress > 0.667) {
					bonusBar.setStyle("-fx-accent: green");
					bonusLabel.setText("+" + Integer.toString(currentScorer.getHighBonusReward()));
				} else if (progress > 0) {
					bonusBar.setStyle("-fx-accent: orange");
					bonusLabel.setText("+" + Integer.toString(currentScorer.getLowBonusReward()));
				} else {
					bonusLabel.setText("+" + Integer.toString(currentScorer.getNoBonusReward()));
				}
			}
		});
		long totalDuration = currentScorer.getLowBonusTimeDuration();
		decreaseProgress(totalDuration);
	}

	private void speak(String text) {
		double speed = speedOfSpeech.getValue();
		Festival.festival(speed, text);
	}

	private void sayWord() {
		String currentWord = currentQuestion.getWord();
		String currentWordSanitised = currentWord.replaceAll("-", " ");
		speak(currentWordSanitised);
	}

	@FXML
	public void skipButton(ActionEvent event) {
		// skip word and get next word
	}

	@FXML
	public void repeatButton(ActionEvent event) {
		// repeat word
		sayWord();
	}

	@FXML
	public void adjustSpeed(MouseEvent event) {
		// adjust speed of speech by dragging slider - 0.5, 1, 1.5
		currentSpeed = speedOfSpeech.getValue();
	}

	@FXML
	public void decreaseProgress(long totalDuration) {
		timeline = new Timeline(new KeyFrame(Duration.millis(0), new KeyValue(bonusBar.progressProperty(), 1)),
				new KeyFrame(Duration.millis(totalDuration * 1000), new KeyValue(bonusBar.progressProperty(), 0)));
		timeline.play();
	}

	@FXML
	public void setScoreLabel(MouseEvent event) {
		// show score for each game
	}

	@FXML
	public void setquestionNumLabel(MouseEvent event) {
		// show question number
	}

	@FXML
	public void setstatusLabel(MouseEvent event) {
		// show status of game according to accepting state
		// "SPELL IT:", "CORRECT", "INCORRECT"
	}

}
