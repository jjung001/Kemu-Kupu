package game;

import java.util.ArrayList;
import java.util.Collection;

public class Quiz {
	private ArrayList<String> words;
	private int maximumNumberOfQuestions;
	private int questionNumber;

	public Quiz(Collection<? extends String> words) {
		this.words.addAll(words);
		this.maximumNumberOfQuestions = words.size();
		questionNumber = 0;
	}

	public Boolean hasNextQuestion() {
		return (questionNumber >= maximumNumberOfQuestions);
	}

	public Question getNextQuestion() {
		if (hasNextQuestion()) {
			String word = words.get(questionNumber);
			Question question = new Question(word);
			questionNumber++;
			return question;
		} else {
			return null;
		}
	}

	public int getQuestionNumber() {
		return questionNumber;
	}
}
