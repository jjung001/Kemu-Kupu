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

	public void submitButton(ActionEvent event) {
		if (isBeginning) {
			// Set up the interface for accepting questions
			isBeginning = false;
			btnSubmit.setText("Submit");
			btnIDontKnow.setDisable(false);
			statusLabel.setText("SPELL IT:");

			// Gather the first question
			getNextQuestion(event);
		} else {
			boolean canProceed = submitQuestion();
			wordTextField.setText("");
			if (canProceed) {
				getNextQuestion(event);
			}
		}
	}

	private boolean submitQuestion() {
		String answer = wordTextField.getText().strip().toLowerCase();
		AnswerStatus answerStatus = currentQuestion.checkAnswer(answer);
		currentScorer.endTiming();
		bonusBar.setProgress(0);

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
	}

	private void failedWord() {
		int questionNumber = quiz.getQuestionNumber();
		int score = 0;
		String word = currentQuestion.getWord();
		scoreTracker.update(questionNumber, score, word);
	}

	private void getNextQuestion(ActionEvent event) {
		if (quiz.hasNextQuestion()) {
			System.out.println("THERE IS NEXT QUESTION"); // DEBUG
			hintLabel.setText("");
			currentQuestion = quiz.getNextQuestion();
			System.out.println("CALLED GET NEXT QUESTION"); // DEBUG
			currentScorer = new Scorer(currentQuestion.getWord());
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
		// TEMP
		combinedWordList.addWord("Alpha");
		combinedWordList.addWord("Bravo");
		combinedWordList.addWord("Charlie");
		combinedWordList.addWord("Delta");
		combinedWordList.addWord("Echo");

		// labels show according to progress of game
		currentSpeed = speedOfSpeech.getValue();

		// Initialize models for spelling quiz
		quiz = new Quiz(combinedWordList.getAllWords());
		scoreTracker = new ScoreTracker(5);

		// Set up interface prior to start of game
		isBeginning = true;
		btnSubmit.setText("Start");
		btnIDontKnow.setDisable(true);
		bonusBar.setProgress(0);
		statusLabel.setText("Press \"Start\" to begin!");
		questionNumLabel.setText("");
	}

	private void startProgressBarCountdown() {
		bonusBar.setProgress(1.0);
		bonusBar.progressProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				double progress = newValue == null ? 0 : newValue.doubleValue();
				if (progress > 0.667) {
					bonusBar.setStyle("-fx-accent: green");
					bonusLabel.setText("BONUS ×6");
				} else if (progress > 0) {
					bonusBar.setStyle("-fx-accent: orange");
					bonusLabel.setText("BONUS ×2");
				} else {
					bonusLabel.setText("NO BONUS");
				}
			}
		});
		decreaseProgress();
	}

	private void sayWord() {
		String currentWord = currentQuestion.getWord();
		String currentWordSanitised = currentWord.replaceAll("-", " ");
		double speed = speedOfSpeech.getValue();
		Festival.festival(speed, currentWordSanitised);
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
	public void decreaseProgress() {
		timeline = new Timeline(new KeyFrame(Duration.millis(0), new KeyValue(bonusBar.progressProperty(), 1)),
				new KeyFrame(Duration.millis(15000), new KeyValue(bonusBar.progressProperty(), 0)));
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
