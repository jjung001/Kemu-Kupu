package tree;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemStock {
	private HashMap<Item, Integer> stock;

	public ItemStock() {
		stock = new HashMap<>();
	}

	public boolean isInStock(Item item) {
		return stock.containsKey(item);
	}

	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<>();
		for (Item item : stock.keySet()) {
			items.add(item);
		}
		return items;
	}

	public int getQuantity(Item item) {
		return stock.get(item);
	}

	public void addItem(Item item) {
		addItem(item, 1);
	}

	public void addItem(Item item, int quantity) {
		if (stock.containsKey(item)) {
			stock.put(item, quantity + stock.get(item));
		} else {
			stock.put(item, quantity);
		}
	}

	public void removeItem(Item item) {
		removeItem(item, 1);
	}

	public void removeItem(Item item, int quantity) {
		if (stock.containsKey(item)) {
			int currentQuantity = stock.get(item);
			int newQuantity = currentQuantity - quantity;
			if (newQuantity <= 0) {
				stock.remove(item);
			} else {
				stock.put(item, newQuantity);
			}
		}
	}
}
