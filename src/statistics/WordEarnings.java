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

	public Map<String, Integer> getTopWords(int numberOfWords) {
		// https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values?page=1&tab=votes#tab-top
		Map<String, Integer> topWords = new LinkedHashMap<>();
		List<Entry<String, Integer>> entryList = new ArrayList<>(store.entrySet());
		entryList.sort(Entry.comparingByValue());
		Collections.reverse(entryList);
		entryList = entryList.subList(0, numberOfWords);
		for (Entry<String, Integer> entry : entryList) {
			topWords.put(entry.getKey(), entry.getValue());
		}
		return topWords;
	}
}
