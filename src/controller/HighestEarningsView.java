package controller;

import java.util.ArrayList;
import java.util.HashMap;

import application.FileSaveLocations;
import fileio.StatisticsIO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import statistics.WordEarnings;

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
	private Label score1Label;
	@FXML
	private Label score2Label;
	@FXML
	private Label score3Label;
	@FXML
	private Label score4Label;
	@FXML
	private Label score5Label;

	@FXML
	private void initialize() {
		StatisticsIO statisticsIO = new StatisticsIO(FileSaveLocations.STATISTICS);
		WordEarnings wordEarnings = statisticsIO.getWordEarnings(0);
		ArrayList<String> topWords = wordEarnings.getTopWordsOnly(5);
		HashMap<String, Integer> topWordsWithScores = (HashMap<String, Integer>) wordEarnings.getTopWordsWithScores(5);
		int numberOfWords = topWords.size();

		resetLabelVisibility(numberOfWords);
		fillUpLabels(numberOfWords, topWords, topWordsWithScores);
	}

	private void resetLabelVisibility(int numberOfWords) {
		word1Container.setVisible(false);
		word2Container.setVisible(false);
		word3Container.setVisible(false);
		word4Container.setVisible(false);
		word5Container.setVisible(false);

		if (numberOfWords > 0) {
			word1Container.setVisible(true);
		}
		if (numberOfWords > 1) {
			word2Container.setVisible(true);
		}
		if (numberOfWords > 2) {
			word3Container.setVisible(true);
		}
		if (numberOfWords > 3) {
			word4Container.setVisible(true);
		}
		if (numberOfWords > 4) {
			word5Container.setVisible(true);
		}

	}

	private void fillUpLabels(int numberOfWords, ArrayList<String> topWords,
			HashMap<String, Integer> topWordsWithScores) {
		if (numberOfWords > 0) {
			fillUpLabelSet1(topWords, topWordsWithScores);
		}
		if (numberOfWords > 1) {
			fillUpLabelSet2(topWords, topWordsWithScores);
		}
		if (numberOfWords > 2) {
			fillUpLabelSet3(topWords, topWordsWithScores);
		}
		if (numberOfWords > 3) {
			fillUpLabelSet4(topWords, topWordsWithScores);
		}
		if (numberOfWords > 4) {
			fillUpLabelSet5(topWords, topWordsWithScores);
		}
	}

	private void fillUpLabelSet1(ArrayList<String> topWords, HashMap<String, Integer> topWordsWithScores) {
		String word = topWords.get(0);
		int score = topWordsWithScores.get(word);
		word1Label.setText(word);
		score1Label.setText(String.valueOf(score));
	}

	private void fillUpLabelSet2(ArrayList<String> topWords, HashMap<String, Integer> topWordsWithScores) {
		String word = topWords.get(1);
		int score = topWordsWithScores.get(word);
		word2Label.setText(word);
		score2Label.setText(String.valueOf(score));
	}

	private void fillUpLabelSet3(ArrayList<String> topWords, HashMap<String, Integer> topWordsWithScores) {
		String word = topWords.get(2);
		int score = topWordsWithScores.get(word);
		word3Label.setText(word);
		score3Label.setText(String.valueOf(score));
	}

	private void fillUpLabelSet4(ArrayList<String> topWords, HashMap<String, Integer> topWordsWithScores) {
		String word = topWords.get(3);
		int score = topWordsWithScores.get(word);
		word4Label.setText(word);
		score4Label.setText(String.valueOf(score));
	}

	private void fillUpLabelSet5(ArrayList<String> topWords, HashMap<String, Integer> topWordsWithScores) {
		String word = topWords.get(4);
		int score = topWordsWithScores.get(word);
		word5Label.setText(word);
		score5Label.setText(String.valueOf(score));
	}
}
