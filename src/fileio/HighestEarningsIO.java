package fileio;

import java.util.ArrayList;
import java.util.Collections;

public class HighestEarningsIO extends FileIO {
	private int numberOfEarningsToRecord;

	public HighestEarningsIO(String filepath) {
		super(filepath);
		numberOfEarningsToRecord = 5;
	}

	public void recordQuizEarning(int totalScoreForQuiz) {
		ArrayList<Integer> topQuizScores = getExistingTopQuizEarnings();
		topQuizScores.add(totalScoreForQuiz);
		Collections.sort(topQuizScores);
		topQuizScores.remove(0);
		Collections.reverse(topQuizScores);
		saveTopQuizEarnings(topQuizScores);
	}

	private ArrayList<Integer> getExistingTopQuizEarnings() {
		ArrayList<String> existingTotalScoreStrings = readLines();
		ArrayList<Integer> existingTopQuizScores = new ArrayList<Integer>();
		int numberOfScores = existingTotalScoreStrings.size();
		for (int i = 0; i < numberOfScores; i++) {
			String totalScoreString = existingTotalScoreStrings.get(i);
			int totalScore = Integer.valueOf(totalScoreString);
			existingTopQuizScores.add(totalScore);
		}
		return existingTopQuizScores;
	}

	private void saveTopQuizEarnings(ArrayList<Integer> sortedTopQuizScores) {
		clearFile();
		int numberOfScores = sortedTopQuizScores.size();
		for (int i = 0; i < numberOfScores; i++) {
			int score = sortedTopQuizScores.get(i);
			String scoreString = String.valueOf(score);
			appendLine(scoreString);
		}
	}

	public ArrayList<String> getHighestQuizEarningsAsStrings() {
		return readLines();
	}

	public void resetHighestEarnings() {
		ArrayList<String> lines = new ArrayList<String>();
		for (int i = 0; i < numberOfEarningsToRecord; i++) {
			lines.add("0");
		}
		overwriteLines(lines);
	}
}
