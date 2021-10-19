package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.Cash;
import application.FileSaveLocations;
import fileio.CashIO;
import fileio.ItemStockIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tree.Item;
import tree.ItemStock;

public class MyTreeShopController extends Controller {
	
	@FXML
    private Label moneyLabel;
    @FXML
    private Button btnBuyWater;
    @FXML
    private Button btnFertiliserPlus;
    @FXML
    private Button btnBuyExWater;
    @FXML
    private Button btnFertiliser;
    @FXML
    private Button BtnSunlight;
    @FXML
    private Button btnInsecticide;
    @FXML
    private Button btnLeft;
    @FXML
    private Button btnRight;
	@FXML
    private Button back;
	
	private OffsetDateTime offSetDateTime;
	private ItemStockIO itemStockIO;
	private CashIO cashIO;
	private Cash treeMoney;
	private ItemStock stock;
	private Item item;
	private Item itemWaterEx;
	private Item itemFertiliser;
	private Item itemFertiliserPlus;
	private Item itemSunlight;
	private Item itemInsecticider;

    @FXML
    void quitShop(ActionEvent event) {
		switchScene(event, "MyTree.fxml");
    }
    
    public HashMap<String, Integer> itemHashMap() {
    	HashMap<String, Integer> itemHashMap = new HashMap<String, Integer>();
    	itemHashMap.put("water", 200);
    	itemHashMap.put("waterEx", 300);
    	itemHashMap.put("fertiliser", 400);
    	itemHashMap.put("fertiliserPlus", 500);
    	itemHashMap.put("sunlight", 200);
    	itemHashMap.put("insecticide", 600);
    	return itemHashMap;
    }
    
    @FXML
    void buyWater(ActionEvent event) {
    	storeItemStockAndMoney();
    	Item item = new Item();
    	String nameWater = "water";
    	HashMap<String, Integer> itemHashMap = new HashMap<String, Integer>();
    	itemHashMap = itemHashMap();
    	item.setName(nameWater);
    	item.setCost(itemHashMap.get(nameWater));
    	buyItem(treeMoney, item, stock);
    	saveItemStockAndMoney(itemStockIO, stock, offSetDateTime, treeMoney); 
    	writeToFile2(".data/inventory", "2021-10-19T14:34:47.542239+13:00");
    	writeToFile(".data/inventory", "water 2");
    	writeToFile(".data/inventory", "insecticide 1");
    }
    
    static void writeToFile(String file, String write) { 
    	try {
    		FileWriter fw = new FileWriter(file, true);
    		BufferedWriter bw = new BufferedWriter(fw);
    		bw.write(write);
    		bw.newLine();
    		bw.close();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    static void writeToFile2(String file, String write) { 
    	try {
    		FileWriter fw = new FileWriter(file);
    		BufferedWriter bw = new BufferedWriter(fw);
    		bw.write(write);
    		bw.newLine();
    		bw.close();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    
    @FXML
    void buyWaterEx(ActionEvent event) {
    	storeItemStockAndMoney();
    	Item item = new Item();
    	String nameWater = "waterEx";
    	HashMap<String, Integer> itemHashMap = new HashMap<String, Integer>();
    	itemHashMap = itemHashMap();
    	item.setName(nameWater);
    	item.setCost(itemHashMap.get(nameWater));
    	buyItem(treeMoney, item, stock);
    	saveItemStockAndMoney(itemStockIO, stock, offSetDateTime, treeMoney);
    }
    
    @FXML
    void buyFertiliser(ActionEvent event) {
    	storeItemStockAndMoney();
    	Item item = new Item();
    	String nameWater = "fertiliser";
    	HashMap<String, Integer> itemHashMap = new HashMap<String, Integer>();
    	itemHashMap = itemHashMap();
    	item.setName(nameWater);
    	item.setCost(itemHashMap.get(nameWater));
    	buyItem(treeMoney, item, stock);
    	saveItemStockAndMoney(itemStockIO, stock, offSetDateTime, treeMoney);
    }

    @FXML
    void buyFertiliserPlus(ActionEvent event) {
    	storeItemStockAndMoney();
    	Item item = new Item();
    	String nameWater = "fertiliserPlus";
    	HashMap<String, Integer> itemHashMap = new HashMap<String, Integer>();
    	itemHashMap = itemHashMap();
    	item.setName(nameWater);
    	item.setCost(itemHashMap.get(nameWater));
    	buyItem(treeMoney, item, stock);
    	saveItemStockAndMoney(itemStockIO, stock, offSetDateTime, treeMoney);
    }

    @FXML
    void buyInsecticide(ActionEvent event) {
    	storeItemStockAndMoney();
    	Item item = new Item();
    	String nameWater = "insecticide";
    	HashMap<String, Integer> itemHashMap = new HashMap<String, Integer>();
    	itemHashMap = itemHashMap();
    	item.setName(nameWater);
    	item.setCost(itemHashMap.get(nameWater));
    	buyItem(treeMoney, item, stock);
//    	saveItemStockAndMoney(itemStockIO, stock, offSetDateTime, treeMoney);
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);   
    	cashIO.saveCash(treeMoney);
    }

    @FXML
    void buySunlight(ActionEvent event) {
    	storeItemStockAndMoney();
    	Item item = new Item();
    	String nameWater = "sunlight";
    	HashMap<String, Integer> itemHashMap = new HashMap<String, Integer>();
    	itemHashMap = itemHashMap();
    	item.setName(nameWater);
    	item.setCost(itemHashMap.get(nameWater));
    	buyItem(treeMoney, item, stock);
    	saveItemStockAndMoney(itemStockIO, stock, offSetDateTime, treeMoney);
    }
    
    private void saveItemStockAndMoney(ItemStockIO itemStockIO, ItemStock stock, OffsetDateTime offSetDateTime,
    		Cash treeMoney) {
    	itemStockIO.saveStockNumbers(stock, offSetDateTime);
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);   
    	cashIO.saveCash(treeMoney);
    }
    
    public void storeItemStockAndMoney() {
    	offSetDateTime = OffsetDateTime.now(); 
    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	treeMoney.setCash(treeMoney.getCash());
    }
    
    public void buyItem(Cash treeMoney, Item item, ItemStock stock) {
    	if (treeMoney.hasEnoughFunds(item.getCost())) {
    		treeMoney.withdraw(item.getCost());
        	stock.addItem(item);
    	} 
    }
    
    public void initialize() {
    	btnRight.setVisible(false);
    	btnLeft.setVisible(false);
    	cashIO = new CashIO(FileSaveLocations.CASH);
    	treeMoney = cashIO.loadCash();
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);
		stock = new ItemStock();

    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	itemStockIO.loadStockNumbers();
    	
    }
}
