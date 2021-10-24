package fileio;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import tree.Item;
import tree.ItemStock;

public class ItemStockIO extends FileIO {

	public ItemStockIO(String filepath) {
		super(filepath);
	}

	public void saveStockNumbers(ItemStock stock, OffsetDateTime dateTime) {
		ArrayList<Item> items = stock.getItems();
		saveDateTime(dateTime);
		for (Item item : items) {
			saveStockNumber(item, stock);
		}
	}

	private void saveDateTime(OffsetDateTime dateTime) {
		String dateTimeString = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		ArrayList<String> lines = new ArrayList<>();
		lines.add(dateTimeString);
		overwriteLines(lines);
	}

	private void saveStockNumber(Item item, ItemStock stock) {
		String itemName = item.getName();
		int stockNumber = stock.getQuantity(item);
		String stockNumberString = String.valueOf(stockNumber);
		String line = itemName + " " + stockNumberString;
		appendLine(line);
	}

	public OffsetDateTime getDateTimeSaved() {
		ArrayList<String> lines = readLines();
		String dateTimeString = lines.get(0);
		OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeString);
		return dateTime;
	}

	public Map<String, Integer> loadStockNumbers() {
		Map<String, Integer> stockNumbers = new LinkedHashMap<>();
		ArrayList<String> stockRecords = readLines();
		stockRecords.remove(0);
		for (String stockRecord : stockRecords) {
			loadStockNumber(stockRecord, stockNumbers);
		}
		return stockNumbers;
	}

	private void loadStockNumber(String stockRecord, Map<String, Integer> stockNumbers) {
		String stockName = getStringPartFromLine(stockRecord, 0);
		String stockNumberString = getStringPartFromLine(stockRecord, 1);
		int stockNumber = Integer.valueOf(stockNumberString);
		stockNumbers.put(stockName, stockNumber);
	}
	
	public void resetItemStock() {
		clearFile();
	}
}
