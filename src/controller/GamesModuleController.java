package controller;

import java.util.HashMap;

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
import application.AlertBox;

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
		
	@FXML
    public void quitGame(ActionEvent event) {
		// to return to Main Menu confirm exit on AlertBox
		if (AlertBox.display()) {
	    	backToMain(event);
		}
    }
		
	@FXML
    public void pressMacronButton(ActionEvent event){
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
		wordTextField.setText(leftPart+macron.get(currentEvent)+rightPart);
		wordTextField.positionCaret(caretPosition+1);
    }
	
	public void endGame(ActionEvent event) {
		// game ended after 5 rounds, switch to resultScreen
        switchScene(event, "ResultScreen.fxml");  
        // else play next round
    }
	
	@FXML
	public void initialize() {
		// labels show according to progress of game
		hintLabel.setText("Hint: the second letter is '"+secondLetter+"'");
//		scoreLabel.setText(score);	
//		questionNumLabel.setText(questionNum);
//		statusLabel.setText(status);
		bonusBar.setProgress(1.0);
		currentSpeed = speedOfSpeech.getValue();
		bonusBar.progressProperty().addListener(new ChangeListener<Number>() {
		      @Override public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		        double progress = newValue == null ? 0 : newValue.doubleValue();
		        if (progress > 0.667) {
		        	bonusBar.setStyle("-fx-accent: green");
		        	bonusLabel.setText("BONUS ×6");
		        } else if (progress > 0){
		        	bonusBar.setStyle("-fx-accent: orange");
		        	bonusLabel.setText("BONUS ×2");
		        } else {
		        	bonusLabel.setText("NO BONUS");
		        }
		      }
		    });
		decreaseProgress();    
	}
	
	@FXML
    public void skipButton(ActionEvent event){
        // skip word and get next word
    }
	
	@FXML
    public void repeatButton(ActionEvent event){
        // repeat word
    }
		
	@FXML
    public void adjustSpeed(MouseEvent event) {
		// adjust speed of speech by dragging slider - 0.5, 1, 1.5
		currentSpeed = speedOfSpeech.getValue();
    }

	@FXML
	public void decreaseProgress() {
		timeline = new Timeline(
				new KeyFrame(Duration.millis(0), new KeyValue(bonusBar.progressProperty(), 1)),
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
