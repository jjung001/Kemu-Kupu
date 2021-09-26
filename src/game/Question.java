package game;

public class Question {
	private String word;
	private int attemptNumber;

	public Question(String word) {
		this.word = word;
		attemptNumber = 1;
	}

	public AnswerStatus checkAnswer(String answer) {
		String wordSanitised = word.strip();
		String answerSanitised = answer.strip();

		if (wordSanitised.equalsIgnoreCase(answerSanitised)) {
			return determineAnswerStatusIfCorrect();
		} else {
			attemptNumber++;
			return determineAnswerStatusIfIncorrect();
		}
	}

	public String getWord() {
		return word;
	}

	public char getSecondLetter() {
		return word.charAt(1);
	}

	private AnswerStatus determineAnswerStatusIfCorrect() {
		switch (attemptNumber) {
		case (2):
			return AnswerStatus.FAULTED;
		default:
			return AnswerStatus.MASTERED;
		}
	}

	private AnswerStatus determineAnswerStatusIfIncorrect() {
		switch (attemptNumber) {
		case (3):
			return AnswerStatus.FAILED;
		default:
			return AnswerStatus.INCORRECT;
		}
	}
}
