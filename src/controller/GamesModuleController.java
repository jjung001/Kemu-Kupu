package controller;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        switchScene(event, "ResultScreen.fxml");
    }
	
	@FXML
	public void initialize() {
		// labels show according to progress of game
		hintLabel.setText("Hint: the second letter is '"+secondLetter+"'");
//		scoreLabel.setText(score);	
//		questionNumLabel.setText(questionNum);
//		statusLabel.setText(status);
	}
	
	@FXML
    public void skipButton(ActionEvent event){
        // skip word and get next word
    }
	
	@FXML
    public void repeatButton(ActionEvent event){
        // repeat word
    }
	
	
	
}
