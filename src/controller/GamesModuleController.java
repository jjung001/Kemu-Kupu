package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import application.AlertBox;
import application.Festival;
import application.Language;
import game.AnswerStatus;
import game.Question;
import game.Quiz;
import game.ScoreTracker;
import game.Scorer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import words.WordList;

/**
 * This is the controller class for the Games module. 
 * It starts the game Kēmu Kupu. 
 * @author Jared Daniel Recomendable
 * @author Juwon Jung
 *
 */
public class GamesModuleController extends Controller {

	private static final ActionEvent ActionEvent = null;
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
	private Timeline bonusBarTimeline;

	/**
	 * When ENTER key is pressed the word is submitted to check if spelt correclty,
	 * instead of pressing the submit button.
	 * @param e KeyEvent when ENTER is pressed on keyboard
	 */
	@FXML
	public void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			submitButton();
		}
	}

	/**
	 * Alert Box appears when back button is pressed.
	 * If ok is selected the screen returns to main main.
	 * @param event ActionEvent when back button is pressed
	 */
	@FXML
	public void quitGame(ActionEvent event) {
		// to return to Main Menu confirm exit on AlertBox
		String header = "Are you sure you want to quit the game?";
		String description = "Your tree will die.";
		AlertBox alertBox = new AlertBox(header, description);
		boolean result = alertBox.displayAndGetResult();

		if (result) {
			backToMain(event);
		}
	}

	/**
	 * Inputs macron character into textField when macaron button is pressd.
	 *
	 * @param event ActionEvent when maracon character is pressed
	 */
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

	/** 
	 * Sets up the interface for accepting question and gets the first word.
	 * If there is another question canProceed boolean is true, to run getNextQuestion.
	 * Pauses the screen for a duration of two seconds before moving onto next word or next screen
	 * to show the status label. 
	 * Sets the status label as Spell it on the first attempt of the word.
	 */
	public void submitButton() {
		toggleButtonVisibility(true, true, false);
		wordTextField.setDisable(true);

		if (isBeginning) {
			// Set up the interface for accepting questions
			isBeginning = false;
			btnSubmit.setText("Submit");
			statusLabel.setText("SPELL IT:");

			// Gather the first question
			getNextQuestion();
		} else {
			boolean canProceed = submitQuestion();
			PauseTransition pause = new PauseTransition(Duration.seconds(2));
			pause.setOnFinished(event -> {
				if (canProceed) {
					getNextQuestion();
				}
			});
			pause.play();
		}
	}

	/**
	 * Checks if word is spelt correct case insensitive.
	 * BonusBar pauses from decreasing when answer is being checked.
	 * WordTextField is reset to empty after each attempt.
	 * Gets current bonus points from end of timing and store in current score.
	 * Stores each state of answer as emu AnswerStatus
	 * @return the state of answer and whether to proceed to next question or give another attempt.
	 */
	private boolean submitQuestion() {
		bonusBarTimeline.pause();
		String answer = wordTextField.getText().strip().toLowerCase();
		AnswerStatus answerStatus = currentQuestion.checkAnswer(answer);
		wordTextField.setText("");
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

	/**
	 * Answer status as mastered and question gains a score.
	 * scoreTracker updates with question Number and scoreLabel updates to show total score of the game.
	 * Status label replaces with CORRECT.
	 * Method speak is calls to run festival command "correct." in english voice.
	 */
	private void masteredWord() {
		int questionNumber = quiz.getQuestionNumber();
		int score = currentScorer.getScore();
		String word = currentQuestion.getWord();
		scoreTracker.update(questionNumber, score, word);
		scoreLabel.setText(Integer.toString(scoreTracker.getTotalScore()));
		statusLabel.setText("CORRECT");
		speak("correct", false);
	}

	/**
	 * Answer status is incorrect.
	 * Provides the user with a hint with second character of word.
	 * Status label is updated to "INCORRECT, SPELL AGAIN".
	 * Method speak calls to run festival command "incorrect." in english voice.
	 * Pauses transition for two seconds before giving next attempt of the word.
	 */
	private void incorrectWord() {
		char secondCharacter = currentQuestion.getSecondLetter();
		String parsedMessage = "The second letter is '" + secondCharacter + "'.";
		hintLabel.setText(parsedMessage);
		statusLabel.setText("INCORRECT, SPELL AGAIN:");
		speak("Incorrect.", false);
		PauseTransition pauseBeforeTesting = new PauseTransition(Duration.seconds(2));
		pauseBeforeTesting.setOnFinished(event -> {
			testWord();
		});
		pauseBeforeTesting.play();
	}

	/**
	 * Answer status is faulted.
	 * scoreTracker updates with question Number and scoreLabel updates to show total score of the game.
	 * Status label is updated to "GOOD JOB".
	 * Method speak calls to run festival command "Good job." in English voice.
	 */
	private void faultedWord() {
		int questionNumber = quiz.getQuestionNumber();
		int score = currentScorer.getFaultedScore();
		String word = currentQuestion.getWord();
		scoreTracker.update(questionNumber, score, word);
		scoreLabel.setText(Integer.toString(scoreTracker.getTotalScore()));
		statusLabel.setText("GOOD JOB");
		speak("Good job.", false);
	}

	/**
	 * Answer status is failed and question fails to gain score.
	 * scoreTracker updates with question Number and scoreLabel updates to show total score of the game.
	 * Status label shows encouraging message.
	 * Method speak calls to run festival command of encouraging message in English voice.

	 */
	private void failedWord() {
		int questionNumber = quiz.getQuestionNumber();
		int score = 0;
		String word = currentQuestion.getWord();
		scoreTracker.update(questionNumber, score, word);
		scoreLabel.setText(Integer.toString(scoreTracker.getTotalScore()));
		String encouragingMessage = pickRandomEncouragingMessage();
		statusLabel.setText(encouragingMessage);
		speak(encouragingMessage, false);
	}

	/**
	 * Picks a random encouraging message of 4 cases.
	 *
	 * @return String encouraging message
	 */
	private String pickRandomEncouragingMessage() {
		Random r = new Random();
		int index = r.nextInt(4);
		switch (index) {
		case 0:
			return "You got this!";
		case 1:
			return "Keep on going!";
		case 2:
			return "Nothing is impossible!";
		default:
			return "You can do it!";
		}
	}

	/**
	 * Gets next question of the word list and tests.
	 * Hint label is set as empty.
	 * Status label updates to "SPELL IT".
	 * QuestionNum label shows the current question number out of 5.
	 * If there is no question left, screen changes to results screen.

	 */
	private void getNextQuestion() {
		if (quiz.hasNextQuestion()) {
			hintLabel.setText("");
			statusLabel.setText("SPELL IT:");
			currentQuestion = quiz.getNextQuestion();
			currentScorer = new Scorer(currentQuestion.getWord());
			testWord();

			int questionNumber = quiz.getQuestionNumber();
			int totalNumberOfQuestions = quiz.getTotalNumberOfQuestions();
			questionNumLabel.setText(questionNumber + " of " + totalNumberOfQuestions);
		} else {
			Stage primaryStage = (Stage) statusLabel.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ResultScreen.fxml"));
			try {
				Parent root = (Parent) loader.load();
				ResultsView controller = loader.getController();
				controller.setUp(scoreTracker);
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Pauses transition for three seconds before time counts down.
	 */
	private void testWord() {
		introduceWord();
		PauseTransition pauseBeforeStartingCountdown = new PauseTransition(Duration.seconds(3));
		pauseBeforeStartingCountdown.setOnFinished(event -> {
			startProgressBarCountdown();
			currentScorer.startTiming();

			toggleButtonVisibility(false, true, true);
			wordTextField.setDisable(false);
		});
		pauseBeforeStartingCountdown.play();
	}

	/**
	 * Method speak calls to run festival command of "Spell the word." in English voice.
	 * Pauses transition for two seconds before saying the question word.
	 */
	private void introduceWord() {
		speak("Spell the word.", false);
		PauseTransition pauseBeforeSayingWord = new PauseTransition(Duration.seconds(2));
		pauseBeforeSayingWord.setOnFinished(event -> {
			sayWord();
		});
		pauseBeforeSayingWord.play();
	}

	/**
	 * Setup of games module prior to start the game and initliaizes models for spelling quiz.
	 *
	 * @param combinedWordList selectedWords is implemented to get the random words.
	 */
	public void setUp(WordList combinedWordList) {
		// labels show according to progress of game
		currentSpeed = speedOfSpeech.getValue();

		// Initialize models for spelling quiz
		quiz = new Quiz(combinedWordList.getRandomWords(5));
		scoreTracker = new ScoreTracker(5);

		// Set up interface prior to start of game
		isBeginning = true;
		btnSubmit.setText("Start");
		bonusBar.setProgress(0);
		statusLabel.setText("Press \"Start\"!");
		questionNumLabel.setText("");
		scoreLabel.setText("0");
		toggleButtonVisibility(isBeginning, true, true);
		wordTextField.setDisable(true);
		btnSubmit.setDisable(false);
	}

	/**
	 * All Buttons expect for Start and wordTextField set as disabled prior to starting the game.
	 * @param visibilityState boolean set as true for buttons and textField that is disabled.
	 */
	private void toggleButtonVisibility(boolean visibilityState, boolean applyToControlButtons,
			boolean applyToMacrons) {
		if (applyToControlButtons) {
			btnSubmit.setDisable(visibilityState);
			btnIDontKnow.setDisable(visibilityState);
			btnRepeat.setDisable(visibilityState);
		}

		if (applyToMacrons) {
			btnA.setDisable(visibilityState);
			btnE.setDisable(visibilityState);
			btnI.setDisable(visibilityState);
			btnO.setDisable(visibilityState);
			btnU.setDisable(visibilityState);
		}
	}

	/**
	 * ProgressBar decreases as it counts down in seconds from dependent bonus points for each question and attempt number.
	 * The possible amount of bonus score available is visible on top of the progress bar.
	 * When ProgressBar is in high bonus range progress Bar is green, changes to orange in low bonus bar
	 * and no bonus range when progress bar is empty.
	 */
	private void startProgressBarCountdown() {
		bonusBar.setProgress(1.0);
		bonusBar.progressProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				double progress = newValue == null ? 0 : newValue.doubleValue();
				int highBonusReward = currentScorer.getHighBonusReward();
				int lowBonusReward = currentScorer.getLowBonusReward();
				int noBonusReward = currentScorer.getNoBonusReward();

				if (currentQuestion.isSecondAttempt()) {
					highBonusReward /= 2;
					lowBonusReward /= 2;
					noBonusReward /= 2;
				}

				if (progress > 0.667) {
					bonusBar.setStyle("-fx-accent: green");
					bonusLabel.setText("+" + Integer.toString(highBonusReward));
				} else if (progress > 0) {
					bonusBar.setStyle("-fx-accent: orange");
					bonusLabel.setText("+" + Integer.toString(lowBonusReward));
				} else {
					bonusLabel.setText("+" + Integer.toString(noBonusReward));
				}
			}
		});
		long totalDuration = currentScorer.getLowBonusTimeDuration();
		decreaseProgress(totalDuration);
	}

	/**
	 * Method sends run festival command.
	 * @param text String word wanting to be spoken.
	 * @param isMaori Boolean of whether Maori or English voice is required.
	 */
	private void speak(String text, boolean isMaori) {
		double speed = speedOfSpeech.getValue();
		if (isMaori) {
			Festival.festival(speed, text, Language.MAORI);
		} else {
			Festival.festival(speed, text, Language.ENGLISH);
		}
	}

	/**
	 * Calls speak method with the "-" replaced with " " as Maori voice cannot read "-".
	 */
	private void sayWord() {
		String currentWord = currentQuestion.getWord();
		String currentWordSanitised = currentWord.replaceAll("-", " ");
		speak(currentWordSanitised, true);
	}

	/**
	 * Skips word if I dont Know button is pressed.
	 * Progress Bar stops decreasing and wordTextField is reset to empty.
	 * Pauses transition for two seconds before going onto next question word.

	 *
	 * @param event
	 */
	@FXML
	public void skipButton(ActionEvent event) {
		// skip word and get next word
		bonusBarTimeline.pause();
		toggleButtonVisibility(true, true, false);
		wordTextField.setDisable(true);
		failedWord();
		wordTextField.setText("");
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(pauseEvent -> {
			getNextQuestion();
		});
		pause.play();
	}

	/**
	 * Repeats the word as many times as the user wants.
	 *
	 * @param event ActionEvene from the repeatButton.
	 */
	@FXML
	public void repeatButton(ActionEvent event) {
		// repeat word
		toggleButtonVisibility(true, true, false);
		PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
		pause.setOnFinished(pauseEvent -> {
			toggleButtonVisibility(false, true, false);
		});
		pause.play();

		sayWord();
	}

	/**
	 * Updates speed label according to slider changes.
	 * If speed slider drags to < 0.75 Speed is determined as fast,
	 * speed < 1.25 is normal else is determined as slow.
	 * @param event MouseEvent slider is dragged in speed ranges.
	 */
	@FXML
	public void updateSpeedLabel(MouseEvent event) {
		// update the speed label during dragging of slider
		double speed = speedOfSpeech.getValue();
		if (speed < 0.75) {
			speedLabel.setText("Speed: Fast");
		} else if (speed < 1.25) {
			speedLabel.setText("Speed: Normal");
		} else {
			speedLabel.setText("Speed: Slow");
		}
	}

	/**
	 * Adjusts speed when user updates and changes speed with speed slider.
	 *
	 * @param event MouseEvent slider is dragged onto speed levels 0.5, 1, 1.5
	 */
	@FXML
	public void adjustSpeed(MouseEvent event) {
		// adjust speed of speech after dragging slider - 0.5, 1, 1.5
		currentSpeed = speedOfSpeech.getValue();
		updateSpeedLabel(event);
	}

	/**
	 * Progress Bar decreases according to bonus score for each question.
	 *
	 * @param totalDuration duration of progressbar
	 */
	@FXML
	public void decreaseProgress(long totalDuration) {
		bonusBarTimeline = new Timeline(new KeyFrame(Duration.millis(0), new KeyValue(bonusBar.progressProperty(), 1)),
				new KeyFrame(Duration.millis(totalDuration * 1000), new KeyValue(bonusBar.progressProperty(), 0)));
		bonusBarTimeline.play();
	}

}
