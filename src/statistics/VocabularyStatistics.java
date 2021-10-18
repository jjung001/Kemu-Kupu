package statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class VocabularyStatistics {
	private HashMap<String, Integer[]> statistics;

	public VocabularyStatistics() {
		statistics = new HashMap<>();
	}

	public HashMap<String, Integer[]> getStatistics() {
		return statistics;
	}

	public ArrayList<String> getWords() {
		ArrayList<String> wordList = new ArrayList<>();
		for (Entry<String, Integer[]> entry : statistics.entrySet()) {
			String word = entry.getKey();
			wordList.add(word);
		}
		return wordList;
	}

	public Integer[] getStatisticsForWord(String word) {
		return statistics.get(word);
	}

	public int getMastered(String word) {
		return getSpecificStatistic(word, 0);
	}

	public int getFaulted(String word) {
		return getSpecificStatistic(word, 0);
	}

	public int getFailed(String word) {
		return getSpecificStatistic(word, 0);
	}

	private int getSpecificStatistic(String word, int statisticIndex) {
		Integer[] wordStatistics = statistics.get(word);
		return wordStatistics[statisticIndex];
	}

	public void incrementMastered(String word) {
		incrementSpecificStatistic(word, 0);
	}

	public void incrementFaulted(String word) {
		incrementSpecificStatistic(word, 1);
	}

	public void incrementFailed(String word) {
		incrementSpecificStatistic(word, 2);
	}

	private void incrementSpecificStatistic(String word, int statisticIndex) {
		if (statistics.containsKey(word)) {
			addToExistingStatisticsForWord(word, statisticIndex);
		} else {
			createNewStatisticsForWord(word, statisticIndex);
		}
	}

	private void addToExistingStatisticsForWord(String word, int statisticIndex) {
		Integer[] wordStatistic = statistics.get(word);
		wordStatistic[statisticIndex]++;
		statistics.put(word, wordStatistic);
	}

	private void createNewStatisticsForWord(String word, int statisticIndex) {
		Integer[] wordStatistic = new Integer[3];
		wordStatistic[statisticIndex] = 1;
		statistics.put(word, wordStatistic);
	}
}
