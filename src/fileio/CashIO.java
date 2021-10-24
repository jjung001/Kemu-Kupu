package fileio;

import java.util.ArrayList;

import application.Cash;

public class CashIO extends FileIO {

	public CashIO(String filepath) {
		super(filepath);
	}

	public void saveCash(Cash cash) {
		int amount = cash.getCash();
		String amountString = String.valueOf(amount);
		ArrayList<String> lines = new ArrayList<>();
		lines.add(amountString);
		overwriteLines(lines);
	}

	public Cash loadCash() {
		Cash cash = new Cash();
		ArrayList<String> lines = readLines();
		String amountString = lines.get(0);
		int amount = Integer.valueOf(amountString);
		cash.setCash(amount);
		return cash;
	}
	
	public void resetCash() {
		ArrayList<String> lines = new ArrayList<>();
		lines.add("0");
		overwriteLines(lines);
	}
}
