package quiz;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Stores WordList objects that can be used for testing on the spelling quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class WordListManager {
	private ArrayList<WordList> wordLists;

	/**
	 * Creates a WordListManager object.
	 */
	public WordListManager() {
		wordLists = new ArrayList<>();
	}

	/**
	 * Adds a WordList object to the WordListManager object.
	 *
	 * @param wordList
	 */
	public void addWordList(WordList wordList) {
		wordLists.add(wordList);
	}

	/**
	 * Removes a WordList object from the WordListManager object.
	 *
	 * @param wordList
	 */
	public void removeWordList(WordList wordList) {
		wordLists.remove(wordList);
	}

	/**
	 * Returns a single WordList object containing all words from the stored
	 * WordList objects.
	 *
	 * @return
	 */
	public WordList getCombinedWords() {
		WordList combinedWordList = new WordList();
		combineWordLists(combinedWordList);
		return combinedWordList;
	}

	/**
	 * Private helper method that iterates through the WordListManager object for
	 * WordList objects.
	 *
	 * @param combinedWordList The WordList object used to store all words from the
	 *                         stored WordList objects.
	 */
	private void combineWordLists(WordList combinedWordList) {
		for (WordList wordList : wordLists) {
			addWordsToCombinedWordList(wordList, combinedWordList);
		}
	}

	/**
	 * Private helper method that iterates through a WorddList object for words.
	 *
	 * @param wordList         The WordList object to iterate through.
	 * @param combinedWordList The WordList object used to store all words from the
	 *                         stored WordList objects.
	 */
	private void addWordsToCombinedWordList(WordList wordList, WordList combinedWordList) {
		HashSet<String> words = wordList.getAllWords();
		for (String word : words) {
			combinedWordList.addWord(word);
		}
	}
}
