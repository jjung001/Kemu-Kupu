package tree;

import java.time.OffsetDateTime;

import enums.FeedType;

public class FeedEvent {
	private FeedType feedType;
	private double amount;
	private OffsetDateTime dateTime;

	public FeedEvent(FeedType feedType, double amount, OffsetDateTime dateTime) {
		this.feedType = feedType;
		this.amount = amount;
		this.dateTime = dateTime;
	}

	public FeedType getFeedType() {
		return feedType;
	}

	public double getAmount() {
		return amount;
	}

	public OffsetDateTime getDateTime() {
		return dateTime;
	}

	public long getTimePeriodSinceFeed() {
		long nowSinceEpoch = OffsetDateTime.now().toEpochSecond();
		long eventSinceEpoch = dateTime.toEpochSecond();
		return nowSinceEpoch - eventSinceEpoch;
	}

	public boolean isPastTimePeriodSinceFeed(long timePeriod) {
		return (getTimePeriodSinceFeed() > timePeriod);
	}
}
