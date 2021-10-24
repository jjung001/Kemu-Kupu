package quiz;

import enums.AnswerStatus;

public class AnswerStatusTracker {
	private AnswerStatus[] answerStatuses;
	private int maximumNumberOfQuestions;

	public AnswerStatusTracker(int maximumNumberOfQuestions) {
		answerStatuses = new AnswerStatus[maximumNumberOfQuestions];
	}

	public void update(int questionNumber, AnswerStatus answerStatus) {
		answerStatuses[questionNumber - 1] = answerStatus;
	}

	public AnswerStatus getAnswerStatus(int questionNumber) {
		return answerStatuses[questionNumber - 1];
	}
}
