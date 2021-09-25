package quiz;

public class Question {
	private String word;
	private int attemptNumber;

	public Question(String word) {
		this.word = word;
		attemptNumber = 1;
	}

	public AnswerStatus checkAnswer(String answer) {
		if (word.equals(answer)) {
			return determineAnswerStatusIfCorrect();
		} else {
			attemptNumber++;
			return determineAnswerStatusIfIncorrect();
		}
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
