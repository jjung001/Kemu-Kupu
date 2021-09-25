package words;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class WordList {
	private HashSet<String> wordList;

	public WordList() {
		wordList = new HashSet<String>();
	}

	public void addWord(String word) {
		wordList.add(word);
	}

	public HashSet<String> getAllWords() {
		return wordList;
	}

	public HashSet<String> getRandomWords(int count) {
		HashSet<String> selection = new HashSet<>();
		count = Integer.min(count, wordList.size());
		while (selection.size() != count) {
			selection.add(getRandomWord());
		}
		return selection;
	}

	private String getRandomWord() {
		String selection = null;
		int selectedIndex = getRandomInteger(wordList.size());
		int currentIndex = 0;

		Iterator<? extends String> iterator = wordList.iterator();
		while (iterator.hasNext()) {
			selection = iterator.next();
			if (currentIndex == selectedIndex) {
				return selection;
			}
			currentIndex++;
		}

		return selection;
	}

	private int getRandomInteger(int bound) {
		Random random = new Random();
		return random.nextInt(bound);
	}
}
