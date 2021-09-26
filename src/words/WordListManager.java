package words;

import java.util.ArrayList;
import java.util.HashSet;

public class WordListManager {
	private ArrayList<WordList> wordLists;

	public WordListManager() {
		wordLists = new ArrayList<>();
	}

	public void addWordList(WordList wordList) {
		wordLists.add(wordList);
	}

	public void removeWordList(WordList wordList) {
		wordLists.remove(wordList);
	}

	public WordList getCombinedWords() {
		WordList combinedWordList = new WordList();
		combineWordLists(combinedWordList);
		return combinedWordList;
	}

	private void combineWordLists(WordList combinedWordList) {
		for (WordList wordList : wordLists) {
			addWordsToCombinedWordList(wordList, combinedWordList);
		}
	}

	private void addWordsToCombinedWordList(WordList wordList, WordList combinedWordList) {
		HashSet<String> words = wordList.getAllWords();
		for (String word : words) {
			combinedWordList.addWord(word);
		}
	}
}
