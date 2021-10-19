package statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordEarnings extends StringIntegerStore {

	public void addEarningsToWord(String word, int earnings) {
		addValueToKey(word, earnings);
	}

	public ArrayList<String> getTopWordsOnly(int numberOfWords) {
		ArrayList<String> topWords = new ArrayList<>();
		List<Entry<String, Integer>> entryList = getSortedEntryList(numberOfWords);
		for (Entry<String, Integer> entry : entryList) {
			topWords.add(entry.getKey());
		}
		return topWords;
	}

	public Map<String, Integer> getTopWordsWithScores(int numberOfWords) {
		Map<String, Integer> topWordsWithScores = new LinkedHashMap<>();
		List<Entry<String, Integer>> entryList = getSortedEntryList(numberOfWords);
		for (Entry<String, Integer> entry : entryList) {
			topWordsWithScores.put(entry.getKey(), entry.getValue());
		}
		return topWordsWithScores;
	}

	private List<Entry<String, Integer>> getSortedEntryList(int numberOfWords) {
		// https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values?page=1&tab=votes#tab-top
		List<Entry<String, Integer>> entryList = new ArrayList<>(store.entrySet());
		entryList.sort(Entry.comparingByValue());
		Collections.reverse(entryList);
		int maximumIndex = Integer.min(numberOfWords, entryList.size());
		entryList = entryList.subList(0, maximumIndex);
		return entryList;
	}
}
