package fileio;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import enums.FeedType;
import tree.FeedEvent;
import tree.FeedEventHistory;

public class TreeFeedEventIO extends FileIO {

	public TreeFeedEventIO(String filepath) {
		super(filepath);
	}

	public void saveFeedEventHistory(FeedEventHistory feedEventHistory) {
		ArrayList<FeedEvent> feedEventList = feedEventHistory.getAllFeedEvents();
		clearFile();
		for (FeedEvent feedEvent : feedEventList) {
			saveFeedEvent(feedEvent);
		}
	}

	private void saveFeedEvent(FeedEvent feedEvent) {
		StringBuilder stringBuilder = new StringBuilder();
		OffsetDateTime feedEventDateTime = feedEvent.getDateTime();
		stringBuilder.append(feedEventDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) + " ");
		stringBuilder.append(feedEvent.getFeedType().toString() + " ");
		stringBuilder.append(String.valueOf(feedEvent.getAmount()));
		appendLine(stringBuilder.toString());
	}

	public FeedEventHistory loadFeedEventHistory() {
		FeedEventHistory feedEventHistory = new FeedEventHistory();
		ArrayList<String> feedEventRecords = readLines();
		for (String feedEventRecord : feedEventRecords) {
			loadFeedEvent(feedEventRecord, feedEventHistory);
		}
		return feedEventHistory;
	}

	private void loadFeedEvent(String feedEventRecord, FeedEventHistory feedEventHistory)
		FeedType feedType = getFeedTypeOfFeedEvent(feedEventRecord);
		double amount = getAmount(feedEventRecord);
		OffsetDateTime feedEventDateTime = getDateTimeOfFeedEvent(feedEventRecord);
		FeedEvent feedEvent = new FeedEvent(feedType, amount, feedEventDateTime);
		feedEventHistory.recordFeedEvent(feedEvent);
	}

	private OffsetDateTime getDateTimeOfFeedEvent(String feedEventRecord) {
		String dateTimeString = getStringPartFromLine(feedEventRecord, 0);
		return OffsetDateTime.parse(dateTimeString);
	}

	private FeedType getFeedTypeOfFeedEvent(String feedEventRecord) {
		String feedTypeString = getStringPartFromLine(feedEventRecord, 1);
		switch (feedTypeString) {
		case "WATER":
			return FeedType.WATER;
		case "NUTRIENT":
			return FeedType.NUTRIENT;
		default:
			return FeedType.CHEMICAL;
		}
	}

	private double getAmount(String feedEventRecord) {
		String amountString = getStringPartFromLine(feedEventRecord, 2);
		return Double.valueOf(amountString);
	}
}
