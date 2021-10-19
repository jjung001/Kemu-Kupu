package fileio;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import enums.AnswerStatus;
import statistics.ProportionsFeed;
import statistics.TimelineFeed;
import statistics.VocabularyStatistics;
import statistics.WordEarnings;

public class StatisticsIO extends FileIO {

	public StatisticsIO(String filepath) {
		super(filepath);
	}

	public void recordWordSpelling(OffsetDateTime dateTime, String word, String wordList, AnswerStatus answerStatus,
			int scoreEarned) {
		String formatted = word.replace(' ', '-');
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) + " ");
		stringBuilder.append(formatted + " ");
		stringBuilder.append(wordList + " ");
		stringBuilder.append(answerStatus.toString() + " ");
		stringBuilder.append(scoreEarned);
		appendLine(stringBuilder.toString());
	}

	public VocabularyStatistics getVocabularyStatistics(String wordList) {
		VocabularyStatistics vocabularyStatistics = new VocabularyStatistics();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordIfInTargetWordList(record, wordList, vocabularyStatistics);
		}
		return vocabularyStatistics;
	}

	private void addRecordIfInTargetWordList(String record, String targetWordList,
			VocabularyStatistics vocabularyStatistics) {
		String wordList = getStringPartFromLine(record, 2);
		if (wordList.equals(targetWordList)) {
			String word = getStringPartFromLine(record, 1);
			String answerStatus = getStringPartFromLine(record, 3);
			addToVocabularyStatistics(word, answerStatus, vocabularyStatistics);
		}

	}

	private void addToVocabularyStatistics(String word, String answerStatus,
			VocabularyStatistics vocabularyStatistics) {
		switch (answerStatus) {
		case "MASTERED":
			vocabularyStatistics.incrementMastered(word);
			break;
		case "FAULTED":
			vocabularyStatistics.incrementFaulted(word);
			break;
		case "FAILED":
			vocabularyStatistics.incrementFailed(word);
			break;
		default:
			break;
		}
	}

	public TimelineFeed getMasteredTimelineFeedForDay() {
		return getTimelineFeedForDay("MASTERED");
	}

	public TimelineFeed getFaultedTimelineFeedForDay() {
		return getTimelineFeedForDay("FAULTED");
	}

	public TimelineFeed getFailedTimelineFeedForDay() {
		return getTimelineFeedForDay("FAILED");
	}

	private TimelineFeed getTimelineFeedForDay(String answerStatus) {
		TimelineFeed timelineFeed = new TimelineFeed();
		initialiseTimelineFeedForDay(timelineFeed);
		OffsetDateTime currentDateTime = OffsetDateTime.now();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordToTimelineIfSameDay(record, currentDateTime, timelineFeed, answerStatus);
		}
		return timelineFeed;
	}

	private void initialiseTimelineFeedForDay(TimelineFeed timelineFeed) {
		timelineFeed.addData("0:00", 0);
		timelineFeed.addData("1:00", 0);
		timelineFeed.addData("2:00", 0);
		timelineFeed.addData("3:00", 0);
		timelineFeed.addData("4:00", 0);
		timelineFeed.addData("5:00", 0);
		timelineFeed.addData("6:00", 0);
		timelineFeed.addData("7:00", 0);
		timelineFeed.addData("8:00", 0);
		timelineFeed.addData("9:00", 0);
		timelineFeed.addData("10:00", 0);
		timelineFeed.addData("11:00", 0);
		timelineFeed.addData("12:00", 0);
		timelineFeed.addData("13:00", 0);
		timelineFeed.addData("14:00", 0);
		timelineFeed.addData("15:00", 0);
		timelineFeed.addData("16:00", 0);
		timelineFeed.addData("17:00", 0);
		timelineFeed.addData("18:00", 0);
		timelineFeed.addData("19:00", 0);
		timelineFeed.addData("20:00", 0);
		timelineFeed.addData("21:00", 0);
		timelineFeed.addData("22:00", 0);
		timelineFeed.addData("23:00", 0);
	}

	private void addRecordToTimelineIfSameDay(String record, OffsetDateTime currentDateTime, TimelineFeed timelineFeed,
			String answerStatus) {
		String dateTimeString = getStringPartFromLine(record, 0);
		OffsetDateTime recordDateTime = OffsetDateTime.parse(dateTimeString);
		if (currentDateTime.getYear() == recordDateTime.getYear()
				&& currentDateTime.getDayOfYear() == recordDateTime.getDayOfYear()) {
			addRecordToTimelineFeedForDay(record, recordDateTime, timelineFeed, answerStatus);
		}
	}

	private void addRecordToTimelineFeedForDay(String record, OffsetDateTime recordDateTime, TimelineFeed timelineFeed,
			String answerStatus) {
		int hour = recordDateTime.getHour();
		String hourParsed = String.valueOf(hour) + ":00";
		addRecordToTimelineFeed(record, hourParsed, timelineFeed, answerStatus);
	}

	public TimelineFeed getMasteredTimelineFeedForWeek() {
		return getTimelineFeedForWeek("MASTERED");
	}

	public TimelineFeed getFaultedTimelineFeedForWeek() {
		return getTimelineFeedForWeek("FAULTED");
	}

	public TimelineFeed getFailedTimelineFeedForWeek() {
		return getTimelineFeedForWeek("FAILED");
	}

	private TimelineFeed getTimelineFeedForWeek(String answerStatus) {
		TimelineFeed timelineFeed = new TimelineFeed();
		initialiseTimelineFeedForWeek(timelineFeed);
		OffsetDateTime currentDateTime = OffsetDateTime.now();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordToTimelineIfSameWeek(record, currentDateTime, timelineFeed, answerStatus);
		}
		return timelineFeed;
	}

	private void initialiseTimelineFeedForWeek(TimelineFeed timelineFeed) {
		timelineFeed.addData("Monday", 0);
		timelineFeed.addData("Tuesday", 0);
		timelineFeed.addData("Wednesday", 0);
		timelineFeed.addData("Thursday", 0);
		timelineFeed.addData("Friday", 0);
		timelineFeed.addData("Saturday", 0);
		timelineFeed.addData("Sunday", 0);
	}

	private void addRecordToTimelineIfSameWeek(String record, OffsetDateTime currentDateTime, TimelineFeed timelineFeed,
			String answerStatus) {
		String dateTimeString = getStringPartFromLine(record, 0);
		OffsetDateTime recordDateTime = OffsetDateTime.parse(dateTimeString);
		int currentWeekOfYear = getWeekOfYear(currentDateTime);
		int recordWeekOfYear = getWeekOfYear(recordDateTime);
		if (currentDateTime.getYear() == recordDateTime.getYear() && currentWeekOfYear == recordWeekOfYear) {
			addRecordToTimelineFeedForWeek(record, recordDateTime, timelineFeed, answerStatus);
		}
	}

	private void addRecordToTimelineFeedForWeek(String record, OffsetDateTime recordDateTime, TimelineFeed timelineFeed,
			String answerStatus) {
		DayOfWeek dayOfWeek = recordDateTime.getDayOfWeek();
		String dayOfWeekParsed;
		switch (dayOfWeek) {
		case MONDAY:
			dayOfWeekParsed = "Monday";
			break;
		case TUESDAY:
			dayOfWeekParsed = "Tuesday";
			break;
		case WEDNESDAY:
			dayOfWeekParsed = "Wednesday";
			break;
		case THURSDAY:
			dayOfWeekParsed = "Thursday";
			break;
		case FRIDAY:
			dayOfWeekParsed = "Friday";
			break;
		case SATURDAY:
			dayOfWeekParsed = "Saturday";
			break;
		default:
			dayOfWeekParsed = "Sunday";
			break;
		}
		addRecordToTimelineFeed(record, dayOfWeekParsed, timelineFeed, answerStatus);
	}

	private void addRecordToTimelineFeed(String record, String category, TimelineFeed timelineFeed,
			String answerStatus) {
		String answerStatusString = getStringPartFromLine(record, 3);
		if (answerStatus.equals(answerStatusString)) {
			timelineFeed.addData(category, 1);
		}
	}

	public ProportionsFeed getProportionsFeedForDay() {
		ProportionsFeed proportionsFeed = new ProportionsFeed();
		OffsetDateTime currentDateTime = OffsetDateTime.now();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordToProportionsIfSameDay(record, currentDateTime, proportionsFeed);
		}
		return proportionsFeed;
	}

	private void addRecordToProportionsIfSameDay(String record, OffsetDateTime currentDateTime,
			ProportionsFeed proportionsFeed) {
		String dateTimeString = getStringPartFromLine(record, 0);
		OffsetDateTime recordDateTime = OffsetDateTime.parse(dateTimeString);
		if (currentDateTime.getYear() == recordDateTime.getYear()
				&& currentDateTime.getDayOfYear() == recordDateTime.getDayOfYear()) {
			addRecordToProportionsFeed(record, proportionsFeed);
		}
	}

	public ProportionsFeed getProportionsFeedForWeek() {
		ProportionsFeed proportionsFeed = new ProportionsFeed();
		OffsetDateTime currentDateTime = OffsetDateTime.now();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordToProportionsIfSameWeek(record, currentDateTime, proportionsFeed);
		}
		return proportionsFeed;
	}

	private void addRecordToProportionsIfSameWeek(String record, OffsetDateTime currentDateTime,
			ProportionsFeed proportionsFeed) {
		String dateTimeString = getStringPartFromLine(record, 0);
		OffsetDateTime recordDateTime = OffsetDateTime.parse(dateTimeString);
		int currentWeekOfYear = getWeekOfYear(currentDateTime);
		int recordWeekOfYear = getWeekOfYear(recordDateTime);
		if (currentDateTime.getYear() == recordDateTime.getYear() && currentWeekOfYear == recordWeekOfYear) {
			addRecordToProportionsFeed(record, proportionsFeed);
		}
	}

	private int getWeekOfYear(OffsetDateTime dateTime) {
		Calendar calendar = Calendar.getInstance();
		int year = dateTime.getYear();
		int monthValue = dateTime.getMonthValue();
		int dayOfYear = dateTime.getDayOfYear();
		calendar.set(year, monthValue, dayOfYear);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	private void addRecordToProportionsFeed(String record, ProportionsFeed proportionsFeed) {
		String answerStatus = getStringPartFromLine(record, 3);
		switch (answerStatus) {
		case "MASTERED":
			proportionsFeed.incrementMastered();
			break;
		case "FAULTED":
			proportionsFeed.incrementFaulted();
			break;
		case "FAILED":
			proportionsFeed.incrementFailed();
			break;
		default:
			break;
		}
	}

	public WordEarnings getWordEarnings(int numberOfWords) {
		WordEarnings wordEarnings = new WordEarnings();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addEarningsToWord(record, wordEarnings);
		}
		return wordEarnings;
	}

	private void addEarningsToWord(String record, WordEarnings wordEarnings) {
		String word = getStringPartFromLine(record, 1);
		String score = getStringPartFromLine(record, 4);
		wordEarnings.addEarningsToWord(word, Integer.parseInt(score));
	}
}
