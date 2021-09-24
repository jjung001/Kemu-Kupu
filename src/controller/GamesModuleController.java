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
	public Label charLabel;
	
	public String word;
	public String charA = "ā";
	public String charE = "ē";
	public String charI = "ī";
	public String charO = "ō";
	public String charU = "ū";
	public HashMap<Button, String> macron;
	public String secondLetter = "j";
	
	@FXML
    public void quitGame(ActionEvent event) {
		// quit game when back button is pressed
    	backToMain(event);
    }
	
	@FXML
    public void pressMacronButtonAction(ActionEvent event){
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
	
	public void playGame(ActionEvent play) {
		// game ended, switch to resultScreen
        switchScene(play, "ResultScreen.fxml");
    }
	
	@FXML
	public void checkWord(ActionEvent event){
		if (wordTextField.getText().equals("apple")) {
			System.out.println("correct");
		}
	}	
	
	@FXML
	public void initialize() {
		charLabel.setText("Hint: the second letter is '"+secondLetter+"'");
		charLabel.setAlignment(Pos.CENTER);
	}
	
	@FXML
    public void skipButtonAction(ActionEvent event){
        // skip word and get next word
    }
	
	@FXML
    public void repeatButtonAAction(ActionEvent event){
        // repeat word
    }
	
	
	
}
