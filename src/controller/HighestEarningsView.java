package controller;

import java.util.ArrayList;

import application.FileSaveLocations;
import fileio.HighestEarningsIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for the Highest Earnings view of the Statistics screen.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class HighestEarningsView extends Controller {
	@FXML
	private AnchorPane word1Container;
	@FXML
	private AnchorPane word2Container;
	@FXML
	private AnchorPane word3Container;
	@FXML
	private AnchorPane word4Container;
	@FXML
	private AnchorPane word5Container;

	@FXML
	private Label score1Label;
	@FXML
	private Label score2Label;
	@FXML
	private Label score3Label;
	@FXML
	private Label score4Label;
	@FXML
	private Label score5Label;

	/**
	 * Called as soon as the window is shown.
	 */
	@FXML
	private void initialize() {
		HighestEarningsIO highestEarningsIO = new HighestEarningsIO(FileSaveLocations.QUIZ_EARNINGS);
		ArrayList<String> highestEarningsStrings = highestEarningsIO.getHighestQuizEarningsAsStrings();
		int numberOfScores = highestEarningsStrings.size();
		resetLabelVisibility(numberOfScores);
		fillUpLabels(numberOfScores, highestEarningsStrings);
	}

	/**
	 * Determines the visibility of the containers, depending on whether they can be
	 * filled up or not.
	 *
	 * @param numberOfScores
	 */
	private void resetLabelVisibility(int numberOfScores) {
		word1Container.setVisible(false);
		word2Container.setVisible(false);
		word3Container.setVisible(false);
		word4Container.setVisible(false);
		word5Container.setVisible(false);

		if (numberOfScores > 0) {
			word1Container.setVisible(true);
		}
		if (numberOfScores > 1) {
			word2Container.setVisible(true);
		}
		if (numberOfScores > 2) {
			word3Container.setVisible(true);
		}
		if (numberOfScores > 3) {
			word4Container.setVisible(true);
		}
		if (numberOfScores > 4) {
			word5Container.setVisible(true);
		}

	}

	/**
	 * Fill up the labels with the content, if any.
	 */
	private void fillUpLabels(int numberOfScores, ArrayList<String> scoresString) {
		if (numberOfScores > 0) {
			score1Label.setText(scoresString.get(0));
		}
		if (numberOfScores > 1) {
			score2Label.setText(scoresString.get(1));
		}
		if (numberOfScores > 2) {
			score3Label.setText(scoresString.get(2));
		}
		if (numberOfScores > 3) {
			score4Label.setText(scoresString.get(3));
		}
		if (numberOfScores > 4) {
			score5Label.setText(scoresString.get(4));
		}
	}

	/**
	 * Navigate to the Spelling Performance view.
	 */
	@FXML
	private void goToSpellingPerformance(ActionEvent event) {
		switchScene(event, "SpellingPerformance.fxml");
	}

	/**
	 * Navigate to the My Vocabulary view.
	 */
	@FXML
	private void goToMyVocabulary(ActionEvent event) {
		switchScene(event, "MyVocabulary.fxml");
	}
}
