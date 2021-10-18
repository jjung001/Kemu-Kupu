package quiz;

public class AnswerAttemptTracker {
	private String[] answerAttempts;
	private int maximumNumberOfQuestions;

	public AnswerAttemptTracker(int maximumNumberOfQuestions) {
		answerAttempts = new String[maximumNumberOfQuestions];
	}

	public void update(int questionNumber, String answerAttempt) {
		answerAttempts[questionNumber - 1] = answerAttempt;
	}

	public String getAnswerAttempt(int questionNumber) {
		return answerAttempts[questionNumber - 1];
	}
}
