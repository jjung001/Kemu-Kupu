package controller;

import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	public int i;
	public boolean quitOrNot;
	public double currentBonus;
	
	@FXML
    public void quitGame(ActionEvent event) {
		// quit game when back button is pressed return to Main Menu
    	backToMain(event);
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
		wordTextField.setText(wordTextField.getText()+macron.get(currentEvent));
    }
	
	public void endGame(ActionEvent event) {
		// game ended, switch to resultScreen
		quitOrNot = true;
		stopThread();
//        double bonusTime = timeline.getCurrentTime().toSeconds();
        switchScene(event, "ResultScreen.fxml");     
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
		th = new Thread(new hello());
		th.start();
//		decreaseProgress();    
	}
	
	class hello implements Runnable {
		public void run(){
			// bonusBar decreases for time range 20 seconds
			quitOrNot = false;
			for (i = 100; i >= 0; i--) {
				bonusBar.setProgress(i / 100.0);
				currentBonus = bonusBar.getProgress()*10;
				System.out.println("currentBonus = "+currentBonus);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (quitOrNot) {
					System.out.println("breakBonus = "+currentBonus);
					break;
				}
			}
		}
	}
	
	public void stopThread() {
		try {
			th.join();
			th = null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

//	@FXML
//	public void decreaseProgress() {
//		IntegerProperty seconds = new SimpleIntegerProperty();
//		bonusBar.progressProperty().bind(seconds.divide(20.0));
//		timeline = new Timeline(
//				new KeyFrame(Duration.ZERO, new KeyValue(seconds, 20)),
//				new KeyFrame(Duration.seconds(60), new KeyValue(seconds, -60)));
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//	}
	
}
