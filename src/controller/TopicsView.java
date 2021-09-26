package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
	private ToggleButton colour, weather, dayOne, dayTwo, monthOne, monthTwo,
			baby, work, feeling, compassPoint, university, software;
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
	}

	public void toggleTopic(ActionEvent event) {
		ToggleButton selectedTopic = (ToggleButton) event.getSource();
		String id = selectedTopic.getId();
		if (selectedTopic.isSelected()) {
			selectedTopic.setGraphic(new ImageView(onButtonImage));
			try {
				wordListManager.addWordList(wordFileReader.readLines(id));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (!selectedTopic.isSelected()) {
			selectedTopic.setGraphic(new ImageView(offButtonImage));
			// TODO Remove specific list to word list manager
//			wordListManager.removeWordList();
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

}
