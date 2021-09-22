package quiz;

public class Scorer {
	private String word;
	private long startTime;
	private long endTime;
	private double wordLengthMultiplier;
	private int averageWordLength;
	private double highBonusReward;
	private double lowBonusReward;
	private double noBonusReward;
	private long highBonusTimeDuration;
	private long lowBonusTimeDuration;

	public Scorer(String word) {
		this.word = word;
		averageWordLength = 10;
		highBonusReward = 300;
		lowBonusReward = 100;
		noBonusReward = 50;
		highBonusTimeDuration = 5;
		lowBonusTimeDuration = 15;

		setWordLengthMultiplier();
		setRewardScheme();
	}

	private void setWordLengthMultiplier() {
		wordLengthMultiplier = (double) word.length() / averageWordLength;
	}

	private void setRewardScheme() {
		highBonusReward *= wordLengthMultiplier;
		lowBonusReward *= wordLengthMultiplier;
		noBonusReward *= wordLengthMultiplier;
	}

	private int getHighBonusReward() {
		return (int) highBonusReward;
	}

	private int getLowBonusReward() {
		return (int) lowBonusReward;
	}

	private int getNoBonusReward() {
		return (int) noBonusReward;
	}


	public void startTiming() {
		startTime = System.currentTimeMillis() / 1000L;
	}

	public void endTiming() {
		endTime = System.currentTimeMillis() / 1000L;
	}

	public int getScore() {
		long timeTaken = endTime - startTime;
		if (timeTaken < highBonusTimeDuration) {
			return (int) highBonusReward;
		} else if (timeTaken < lowBonusTimeDuration) {
			return (int) lowBonusReward;
		} else {
			return (int) noBonusReward;
		}
	}
}
