package quiz;

public class ScoreTracker {
	private int[] scores;
	private int maximumNumberOfQuestions;

	public ScoreTracker(int maximumNumberOfQuestions) {
		this.maximumNumberOfQuestions = maximumNumberOfQuestions;
		scores = new int[maximumNumberOfQuestions];
	}

	public void updateScore(int questionNumber, int score) {
		scores[questionNumber - 1] = score;
	}

	public int getScore(int questionNumber) {
		return scores[questionNumber - 1];
	}
}
