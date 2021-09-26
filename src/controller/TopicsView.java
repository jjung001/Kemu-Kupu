package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import words.WordFileReader;
import words.WordList;
import words.WordListManager;

public class TopicsView extends Controller {

	@FXML
	private ToggleButton colour, weather, dayOne, dayTwo, monthOne, monthTwo, baby, work, feeling, compassPoint,
			university, software;
	@FXML
	private Button start, back;
	@FXML
	private Label startWarning;

	Image onButtonImage = new Image(getClass().getResourceAsStream("../resources/Toggle_Button_On.png"));
	Image offButtonImage = new Image(getClass().getResourceAsStream("../resources/Toggle_Button_Off.png"));
	private ArrayList<ToggleButton> toggles = new ArrayList<ToggleButton>();
	private WordList combinedWordList;
	private WordListManager wordListManager;
	private WordFileReader wordFileReader;
	private HashMap<String, WordList> loadedWordListHashMap;

	@FXML
	private void initialize() {
		toggles.add(colour);
		toggles.add(weather);
		toggles.add(dayOne);
		toggles.add(dayTwo);
		toggles.add(monthOne);
		toggles.add(monthTwo);
		toggles.add(baby);
		toggles.add(work);
		toggles.add(feeling);
		toggles.add(compassPoint);
		toggles.add(university);
		toggles.add(software);

		// TODO Get word lists from files
		wordFileReader = new WordFileReader();
		wordListManager = new WordListManager();
		loadedWordListHashMap = new HashMap<>();
	}

	public void toggleTopic(ActionEvent event) {
		try {
			ToggleButton selectedTopic = (ToggleButton) event.getSource();
			String id = selectedTopic.getId();
			WordList targetWordList;

			if (selectedTopic.isSelected()) {
				// Set the toggle button image
				selectedTopic.setGraphic(new ImageView(onButtonImage));

				if (loadedWordListHashMap.containsKey(id)) {
					targetWordList = loadedWordListHashMap.get(id);
				} else {
					targetWordList = wordFileReader.readLines(id);
					loadedWordListHashMap.put(id, targetWordList);
				}
				wordListManager.addWordList(targetWordList);
			} else if (!selectedTopic.isSelected()) {
				selectedTopic.setGraphic(new ImageView(offButtonImage));
				targetWordList = loadedWordListHashMap.get(id);
				wordListManager.removeWordList(targetWordList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Spelling Quiz Error");
			alert.setHeaderText("FileNotFound Caught");
			alert.setContentText("Please contact the developer.");
			alert.showAndWait();
		}
	}

	public void startGame(ActionEvent start) {
		int numTopics = 0;
		for (ToggleButton topic : toggles) {
			if (topic.isSelected()) {
				numTopics++;
			}
		}
		if (numTopics == 0) {
			startWarning.setVisible(true);
		} else {
			Stage primaryStage = (Stage) startWarning.getScene().getWindow();
			combinedWordList = wordListManager.getCombinedWords();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GameScreen.fxml"));
			try {
				Parent root = (Parent) loader.load();
				GamesModuleController controller = loader.getController();
				controller.setUp(combinedWordList);
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Spelling Quiz Error");
				alert.setHeaderText("IOException Caught, Game Setup Screen");
				alert.setContentText("Please contact the developer.");
				alert.showAndWait();
			}
		}
	}
	
	public void quitTopic(ActionEvent event) {
		// to return to Main Menu confirm exit on AlertBox
		String header = "Are you sure you want to go back to Main?";
		String description = "The topics will not be saved.";
		if (AlertBox.display(header, description)) {
			backToMain(event);
		}
	}

}
