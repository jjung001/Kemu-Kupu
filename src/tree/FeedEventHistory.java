package tree;

import java.util.ArrayList;

public class FeedEventHistory {
	private ArrayList<FeedEvent> feedEventList;
	long thresholdTimePeriod;

	public FeedEventHistory() {
		feedEventList = new ArrayList<>();
		thresholdTimePeriod = 60000;
	}

	public void recordFeedEvent(FeedEvent feedEvent) {
		feedEventList.add(feedEvent);
	}

	public boolean hasFeedEventsPastThresholdTimePeriod() {
		for (FeedEvent feedEvent : feedEventList) {
			if (feedEvent.isPastTimePeriodSinceFeed(thresholdTimePeriod)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<FeedEvent> getAllFeedEvents() {
		return feedEventList;
	}

	public ArrayList<FeedEvent> getFeedEventsPastThresholdTimePeriod() {
		ArrayList<FeedEvent> selectFeedEventList = new ArrayList<>();
		for (FeedEvent feedEvent : feedEventList) {
			if (feedEvent.isPastTimePeriodSinceFeed(thresholdTimePeriod)) {
				selectFeedEventList.add(feedEvent);
			}
		}
		return selectFeedEventList;
	}

	public void removeAllFeedEvents() {
		feedEventList = new ArrayList<>();
	}

	public void removeFeedEventsPastThresholdTimePeriod() {
		int index = 0;
		while (index < feedEventList.size()) {
			FeedEvent feedEvent = feedEventList.get(index);
			if (feedEvent.isPastTimePeriodSinceFeed(thresholdTimePeriod)) {
				feedEventList.remove(index);
			} else {
				index++;
			}
		}
	}
}
