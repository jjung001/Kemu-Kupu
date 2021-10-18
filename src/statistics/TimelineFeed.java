package statistics;

public class TimelineFeed extends StringIntegerStore {

	public void addData(String category, int value) {
		addValueToKey(category, value);
	}

	public int getDataOnExistingCategory(String category) {
		return store.get(category);
	}
}
