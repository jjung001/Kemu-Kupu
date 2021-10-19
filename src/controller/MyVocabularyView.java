package controller;

import java.util.ArrayList;

import application.FileSaveLocations;
import fileio.StatisticsIO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import statistics.VocabularyStatistics;

public class MyVocabularyView extends Controller {
	@FXML
	private Button previousWordListButton;
	@FXML
	private Button nextWordListButton;

	@FXML
	private Button previousWordsButton;
	@FXML
	private Button nextWordsButton;

	@FXML
	private Button topic1Button;
	@FXML
	private Button topic2Button;
	@FXML
	private Button topic3Button;

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
	private Label word1Label;
	@FXML
	private Label word2Label;
	@FXML
	private Label word3Label;
	@FXML
	private Label word4Label;
	@FXML
	private Label word5Label;

	@FXML
	private Label word1MasteredLabel;
	@FXML
	private Label word2MasteredLabel;
	@FXML
	private Label word3MasteredLabel;
	@FXML
	private Label word4MasteredLabel;
	@FXML
	private Label word5MasteredLabel;

	@FXML
	private Label word1FaultedLabel;
	@FXML
	private Label word2FaultedLabel;
	@FXML
	private Label word3FaultedLabel;
	@FXML
	private Label word4FaultedLabel;
	@FXML
	private Label word5FaultedLabel;

	@FXML
	private Label word1FailedLabel;
	@FXML
	private Label word2FailedLabel;
	@FXML
	private Label word3FailedLabel;
	@FXML
	private Label word4FailedLabel;
	@FXML
	private Label word5FailedLabel;

	private ArrayList<String> topicsList;
	private int topicsPointer;
	private String topic1;
	private String topic2;
	private String topic3;
	private boolean isTopic1Enabled;
	private boolean isTopic2Enabled;
	private boolean isTopic3Enabled;

	private String currentTopic;
	private boolean isTopic1Selected;
	private boolean isTopic2Selected;
	private boolean isTopic3Selected;

	private ArrayList<String> words;
	private VocabularyStatistics vocabularyStatistics;
	private int wordsPointer;
	private boolean isWord1Shown;
	private boolean isWord2Shown;
	private boolean isWord3Shown;
	private boolean isWord4Shown;
	private boolean isWord5Shown;

	@FXML
	private void initialize() {
		topicsList = new ArrayList<>();
		topicsList.add("Babies");
		topicsList.add("Colours");
		topicsList.add("CompassPoints");
		topicsList.add("Day");
		topicsList.add("DayLoan");
		topicsList.add("Feeling");
		topicsList.add("Month");
		topicsList.add("MonthLoan");
		topicsList.add("Software");
		topicsList.add("University");
		topicsList.add("Weather");
		topicsList.add("Work");
		currentTopic = topicsList.get(topicsPointer);
		refreshTopics();
		refreshWords();
	}

	@FXML
	private void previousWordLists() {
		topicsPointer -= 3;
		refreshTopics();
	}

	@FXML
	private void nextWordLists() {
		topicsPointer += 3;
		refreshTopics();
	}

	private void refreshTopics() {
		determineTopicToggleButtonVisibility();
		determineTopicButtonVisibility();
		refreshTopicsStrings();
		refreshTopicButtons();
		determineIfTopicSelected();
		refreshTopicButtonSelectionState();
	}

	private void determineTopicToggleButtonVisibility() {
		previousWordListButton.setDisable(topicsPointer <= 0);
		nextWordListButton.setDisable(topicsPointer + 3 >= topicsList.size());
	}

	private void determineTopicButtonVisibility() {
		isTopic1Enabled = true;

		if (topicsPointer + 2 >= topicsList.size()) {
			isTopic3Enabled = false;
			topic3Button.setDisable(true);
		} else {
			isTopic3Enabled = true;
			topic3Button.setDisable(false);
		}

		if (topicsPointer + 1 >= topicsList.size()) {
			isTopic2Enabled = false;
			topic2Button.setDisable(true);
		} else {
			isTopic2Enabled = true;
			topic2Button.setDisable(false);
		}
	}

	private void refreshTopicsStrings() {
		if (isTopic1Enabled) {
			topic1 = topicsList.get(topicsPointer);
		}
		if (isTopic2Enabled) {
			topic2 = topicsList.get(topicsPointer + 1);
		}
		if (isTopic3Enabled) {
			topic3 = topicsList.get(topicsPointer + 2);
		}
	}

	private void refreshTopicButtons() {
		if (isTopic1Enabled) {
			topic1Button.setText(topic1);
		}
		if (isTopic2Enabled) {
			topic2Button.setText(topic2);
		}
		if (isTopic3Enabled) {
			topic3Button.setText(topic3);
		}
	}

	private void determineIfTopicSelected() {
		isTopic1Selected = currentTopic.equals(topic1);
		isTopic2Selected = currentTopic.equals(topic2);
		isTopic3Selected = currentTopic.equals(topic3);
	}

	private void refreshTopicButtonSelectionState() {
		if (isTopic1Selected) {
			darkenButton(topic1Button);
		} else {
			lightenButton(topic1Button);
		}

		if (isTopic2Selected) {
			darkenButton(topic2Button);
		} else {
			lightenButton(topic2Button);
		}

		if (isTopic3Selected) {
			darkenButton(topic3Button);
		} else {
			lightenButton(topic3Button);
		}
	}

	private void darkenButton(Button button) {
		button.setTextFill(Paint.valueOf("#eeeeee"));
		button.setStyle("-fx-background-color: #714e42;");
	}

	private void lightenButton(Button button) {
		button.setTextFill(Paint.valueOf("#666666"));
		button.setStyle("-fx-background-color: #EEEEEE;");
	}

	@FXML
	private void goToTopic1() {
		currentTopic = topic1;
		refreshTopics();
		refreshWords();
	}

	@FXML
	private void goToTopic2() {
		currentTopic = topic2;
		refreshTopics();
		refreshWords();
	}

	@FXML
	private void goToTopic3() {
		currentTopic = topic3;
		refreshTopics();
		refreshWords();
	}

	@FXML
	private void previousWords() {
		wordsPointer -= 5;
		nextWordsButton.setDisable(false);
		if (wordsPointer <= 0) {
			previousWordsButton.setDisable(true);
		}
		refreshWords();
	}

	@FXML
	private void nextWords() {
		wordsPointer += 5;
		previousWordsButton.setDisable(false);
		if (wordsPointer + 3 >= words.size()) {
			nextWordsButton.setDisable(true);
		}
		refreshWords();
	}

	private void refreshWords() {
		loadWords();
		determineWordToggleButtonVisibility();
		determineWordsPaneVisibility();
		refreshWordLabels();
	}

	private void determineWordToggleButtonVisibility() {
		previousWordsButton.setDisable(wordsPointer <= 0);
		nextWordsButton.setDisable(wordsPointer + 5 >= words.size());
	}

	private void loadWords() {
		StatisticsIO statisticsIO = new StatisticsIO(FileSaveLocations.STATISTICS);
		vocabularyStatistics = statisticsIO.getVocabularyStatistics(currentTopic);
		words = vocabularyStatistics.getWords();
	}

	private void determineWordsPaneVisibility() {
		if (wordsPointer + 4 >= words.size()) {
			isWord5Shown = false;
			word5Container.setVisible(false);
		} else {
			isWord5Shown = true;
			word5Container.setVisible(true);
		}

		if (wordsPointer + 3 >= words.size()) {
			isWord4Shown = false;
			word4Container.setVisible(false);
		} else {
			isWord4Shown = true;
			word4Container.setVisible(true);
		}

		if (wordsPointer + 2 >= words.size()) {
			isWord3Shown = false;
			word3Container.setVisible(false);
		} else {
			isWord3Shown = true;
			word3Container.setVisible(true);
		}

		if (wordsPointer + 1 >= words.size()) {
			isWord2Shown = false;
			word2Container.setVisible(false);
		} else {
			isWord2Shown = true;
			word2Container.setVisible(true);
		}

		if (wordsPointer >= words.size()) {
			isWord1Shown = false;
			word1Container.setVisible(false);
		} else {
			isWord1Shown = true;
			word1Container.setVisible(true);
		}
	}

	private void refreshWordLabels() {
		if (isWord1Shown) {
			String word = words.get(wordsPointer);
			word1Label.setText(word);

			int mastered = vocabularyStatistics.getMastered(word);
			int faulted = vocabularyStatistics.getFaulted(word);
			int failed = vocabularyStatistics.getFailed(word);

			word1MasteredLabel.setText(String.valueOf(mastered));
			word1FaultedLabel.setText(String.valueOf(faulted));
			word1FailedLabel.setText(String.valueOf(failed));
		}

		if (isWord2Shown) {
			String word = words.get(wordsPointer);
			word2Label.setText(word);

			int mastered = vocabularyStatistics.getMastered(word);
			int faulted = vocabularyStatistics.getFaulted(word);
			int failed = vocabularyStatistics.getFailed(word);

			word2MasteredLabel.setText(String.valueOf(mastered));
			word2FaultedLabel.setText(String.valueOf(faulted));
			word2FailedLabel.setText(String.valueOf(failed));
		}

		if (isWord3Shown) {
			String word = words.get(wordsPointer);
			word3Label.setText(word);

			int mastered = vocabularyStatistics.getMastered(word);
			int faulted = vocabularyStatistics.getFaulted(word);
			int failed = vocabularyStatistics.getFailed(word);

			word3MasteredLabel.setText(String.valueOf(mastered));
			word3FaultedLabel.setText(String.valueOf(faulted));
			word3FailedLabel.setText(String.valueOf(failed));
		}

		if (isWord4Shown) {
			String word = words.get(wordsPointer);
			word4Label.setText(word);

			int mastered = vocabularyStatistics.getMastered(word);
			int faulted = vocabularyStatistics.getFaulted(word);
			int failed = vocabularyStatistics.getFailed(word);

			word4MasteredLabel.setText(String.valueOf(mastered));
			word4FaultedLabel.setText(String.valueOf(faulted));
			word4FailedLabel.setText(String.valueOf(failed));
		}

		if (isWord5Shown) {
			String word = words.get(wordsPointer);
			word5Label.setText(word);

			int mastered = vocabularyStatistics.getMastered(word);
			int faulted = vocabularyStatistics.getFaulted(word);
			int failed = vocabularyStatistics.getFailed(word);

			word5MasteredLabel.setText(String.valueOf(mastered));
			word5FaultedLabel.setText(String.valueOf(faulted));
			word5FailedLabel.setText(String.valueOf(failed));
		}
	}
}
