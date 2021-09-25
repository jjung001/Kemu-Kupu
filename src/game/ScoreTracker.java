package game;

public class ScoreTracker {
	private int[] scores;
	private String[] words;
	private int maximumNumberOfQuestions;

	public ScoreTracker(int maximumNumberOfQuestions) {
		this.maximumNumberOfQuestions = maximumNumberOfQuestions;
		scores = new int[maximumNumberOfQuestions];
	}

	public void update(int questionNumber, int score, String word) {
		scores[questionNumber - 1] = score;
		words[questionNumber - 1] = word;
	}

	public int getScore(int questionNumber) {
		return scores[questionNumber - 1];
	}

	public String getWord(int questionNumber) {
		return words[questionNumber - 1];
	}

	public int getTotalScore() {
		int totalScore = 0;
		for (int i = 0; i < scores.length; i++) {
			totalScore += scores[i];
		}
		return totalScore;
	}
}
