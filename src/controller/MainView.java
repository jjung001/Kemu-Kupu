package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * This is a controller class for Main Menu.
 * @author Julie Kim
 *
 */
public class MainView extends Controller {

	@FXML
	private Button gameButton;
	@FXML
	private Button quitButton;
	@FXML
	private Button practiceButton;
	@FXML
	private Button statisticsButton;
	@FXML
	private Button myTreeButton;
	@FXML
	private Button resetBinButton;

	/**
	 * Starts game module by switching scene to the topic list scene.
	 * @param play	ActionEvent from play game button 
	 */
	public void playGame(ActionEvent play) {
		switchScene(play, "TopicList.fxml");
	}
	
	public void playPractice(ActionEvent play) {
		switchScene(play, "PracticeTopic.fxml");
	}
	
	public void viewMyTree(ActionEvent event) {
		switchScene(event, "MyTree.fxml");
	}
	
	public void viewStatistics(ActionEvent event) {
		switchScene(event, "MyVocabulary.fxml");
	}

	/**
	 * Exits and shuts down window when the quit button is pressed. 
	 * @param quit	ActionEvent from quit button
	 */
	public void quitGame(ActionEvent quit) {
		System.exit(0);
	}

}
