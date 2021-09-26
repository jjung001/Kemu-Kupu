package game;

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

		setWordLengthMultiplier();
		setBonusTimes();
		setRewardScheme();
	}

	private void setWordLengthMultiplier() {
		wordLengthMultiplier = (double) word.length() / averageWordLength;
	}

	// Rationale: Longer words need more time to type, and vice versa.
	private void setBonusTimes() {
		highBonusTimeDuration = Long.max((long) (10 * ((double) word.length() / averageWordLength)), 6);
		lowBonusTimeDuration = highBonusTimeDuration * 3;
	}

	private void setRewardScheme() {
		highBonusReward *= wordLengthMultiplier;
		lowBonusReward *= wordLengthMultiplier;
		noBonusReward *= wordLengthMultiplier;
	}

	public int getHighBonusReward() {
		return (int) highBonusReward;
	}

	public int getLowBonusReward() {
		return (int) lowBonusReward;
	}

	public int getNoBonusReward() {
		return (int) noBonusReward;
	}

	public long getHighBonusTimeDuration() {
		return highBonusTimeDuration;
	}

	public long getLowBonusTimeDuration() {
		return lowBonusTimeDuration;
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

	public int getFaultedScore() {
		return (int) 0.5 * getScore();
	}
}
