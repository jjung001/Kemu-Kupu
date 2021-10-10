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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import words.WordFileReader;
import words.WordList;
import words.WordListManager;

/**
 * This is a controller class of the Topic list scene. 
 * It reads the user's selection of topics by the toggle buttons allocated for each word list. 
 * Then according to the selection, it calls model class to read file and generate a word list to be passed to the game
 * 
 * @author Julie Kim
 * @author Juwon Jung
 * @author Jared Daniel Recomendable
 *
 */
public class TopicsView extends Controller {

	@FXML
	private ToggleButton colour, weather, dayOne, dayTwo, monthOne, monthTwo, baby, work, feeling, compassPoint,
			university, software;
	@FXML
	private Button start, back;
	@FXML
	private Label startWarning;

	Image onButtonImage = new Image(getClass().getResourceAsStream("/resources/Toggle_Button_On.png"));
	Image offButtonImage = new Image(getClass().getResourceAsStream("/resources/Toggle_Button_Off.png"));
	private ArrayList<ToggleButton> toggles = new ArrayList<ToggleButton>();
	private WordList combinedWordList;
	private WordListManager wordListManager;
	private WordFileReader wordFileReader;
	private HashMap<String, WordList> loadedWordListHashMap;

	/**
	 * Initialises the class by adding all toggle buttons in to an Arraylist. 
	 * Initiates instances from model classes to use for generating word list
	 */
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

		wordFileReader = new WordFileReader();
		wordListManager = new WordListManager();
		loadedWordListHashMap = new HashMap<>();
	}

	/**
	 * Toggles topic when user clicks on the toggle button allocated to each topic. 
	 * Reads toggle button status, and adds the word list of selected to the combined word list to send to games module. 
	 * Removes the word list from combined word list when button is untoggled. 
	 * Displays the toggle-untoggle by changing graphics image to on button image and off button image accordingly. 
	 * 
	 * @param event	ActionEvent from the toggle buttons (On or off)
	 */
	public void toggleTopic(ActionEvent event) {
		try {
			ToggleButton selectedTopic = (ToggleButton) event.getSource();
			String id = selectedTopic.getId();
			WordList targetWordList;

			if (selectedTopic.isSelected()) {
				// Set the toggle button image
				selectedTopic.setGraphic(new ImageView(onButtonImage));
				// Check if word list is already included
				if (loadedWordListHashMap.containsKey(id)) {
					targetWordList = loadedWordListHashMap.get(id);
				} else {
					targetWordList = wordFileReader.readLines(id);
					loadedWordListHashMap.put(id, targetWordList);
				}
				wordListManager.addWordList(targetWordList);
				//When button un-toggled, set image and remove word list. 
			} else if (!selectedTopic.isSelected()) {
				selectedTopic.setGraphic(new ImageView(offButtonImage));
				targetWordList = loadedWordListHashMap.get(id);
				wordListManager.removeWordList(targetWordList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the game when at least one topic button is toggled.
	 * Displays warning message to select a topic if pressed without any toggled button. 
	 * Passes the combinedWordList generate accordingly to the user's selection to the Games scene. 
	 * @param start ActionEvent of the start button. 
	 */
	public void startGame(ActionEvent start) {
		//Search to hash map of toggle buttons to check number of toggled buttons. 
		int numTopics = 0;
		for (ToggleButton topic : toggles) {
			if (topic.isSelected()) {
				numTopics++;
			}
		}
		if (numTopics == 0) {
			startWarning.setVisible(true);
		} else {
			//Call next scene (Game screen) 
			Stage primaryStage = (Stage) startWarning.getScene().getWindow();
			combinedWordList = wordListManager.getCombinedWords();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GameScreen.fxml"));
			try {
				Parent root = (Parent) loader.load();
				GamesModuleController controller = loader.getController();
				controller.setUp(combinedWordList);
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Quits from the topic list and returns to the Main menu screen when user confirms the alert message
	 * @param event ActionEvent of the back button. 
	 */
	public void quitTopic(ActionEvent event) {
		// to return to Main Menu confirm exit on AlertBox
		String header = "Are you sure you want to go back?";
		String description = "Your selected topics will not be saved.";

		AlertBox alertBox = new AlertBox(header, description);
		boolean result = alertBox.displayAndGetResult();
		
		if (result) {
			backToMain(event);
		}
		
	}

}
