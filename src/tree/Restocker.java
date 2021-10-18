package tree;

import java.util.ArrayList;
import java.util.Random;

public class Restocker {
	private ArrayList<Item> itemList;
	private ArrayList<Double> probabilityList;

	public Restocker(ArrayList<Item> itemList, ArrayList<Double> probabilityList) {
		this.itemList = itemList;
		this.probabilityList = probabilityList;
	}

	public ArrayList<Item> getRandomItems(int numberOfItems) {
		ArrayList<Item> randomItems = new ArrayList<>();
		while (numberOfItems > 0) {
			Item item = getRandomItem();
			randomItems.add(item);
			numberOfItems--;
		}
		return randomItems;
	}

	private Item getRandomItem() {
		double randomNumber = generateRandomDouble();
		int index = getItemIndex(randomNumber);
		return itemList.get(index);
	}

	private double generateRandomDouble() {
		Random r = new Random();
		return r.nextDouble();
	}

	private int getItemIndex(double randomNumber) {
		int numberOfItems = probabilityList.size();
		for (int index = 0; index < numberOfItems; index++) {
			randomNumber -= probabilityList.get(index);
			if (randomNumber < 0) {
				return index;
			}
		}
		return numberOfItems - 1;
	}
}
