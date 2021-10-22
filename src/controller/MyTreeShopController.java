package controller;

import java.time.OffsetDateTime;
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
	private Item itemWater;
	private Item itemWaterEx;
	private Item itemFertiliser;
	private Item itemFertiliserPlus;
	private Item itemSunlight;
	private Item itemInsecticide;

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
    	buyItem(itemWater);
    }

    @FXML
    void buyWaterEx(ActionEvent event) {
    	buyItem(itemWaterEx);
    }

    @FXML
    void buyFertiliser(ActionEvent event) {
    	buyItem(itemFertiliser);
    }

    @FXML
    void buyFertiliserPlus(ActionEvent event) {
    	buyItem(itemFertiliserPlus);
    }

    @FXML
    void buyInsecticide(ActionEvent event) {
    	buyItem(itemInsecticide);
    }

    @FXML
    void buySunlight(ActionEvent event) {
    	buyItem(itemSunlight);
    }

    public void buyItem(Item item) {
    	if (treeMoney.hasEnoughFunds(item.getCost())) {
    		treeMoney.withdraw(item.getCost());
        	stock.addItem(item);
        	saveItemStockAndMoney();
        	updateCashLabel();
    	}
    }

    private void saveItemStockAndMoney() {
    	CashIO cashIO = new CashIO(FileSaveLocations.CASH);
    	ItemStockIO itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	cashIO.saveCash(treeMoney);
    	itemStockIO.saveStockNumbers(stock, OffsetDateTime.now());
    }

    private void updateCashLabel() {
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);
    }

    public void initialize() {
    	btnRight.setVisible(false);
    	btnLeft.setVisible(false);
    	cashIO = new CashIO(FileSaveLocations.CASH);
    	treeMoney = cashIO.loadCash();
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);
    	initializeItems();
    	initializeStock();
    }

    private void initializeItems() {
    	itemWater          = new Item("water",             200, "", 100,   0, 0, 0, 0);
    	itemWaterEx        = new Item("waterEx",           300, "", 150,   0, 0, 0, 0);
    	itemFertiliser     = new Item("fertiliser",        400, "",  50,   1, 0, 0, 0);
    	itemFertiliserPlus = new Item("fertiliserPlus",    500, "", 100,   2, 0, 0, 0);
    	itemSunlight       = new Item("sunlight",          200, "",   0,   2, 0, 0, 0);
    	itemInsecticide    = new Item("insecticide",       600, "", 100,   4, 0, 0, 0);
    }

    private void initializeStock() {
    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	stock = new ItemStock();
    	Map<String, Integer> stockNumbers = itemStockIO.loadStockNumbers();

    	for (String itemName : stockNumbers.keySet()) {
    		loadStock(stock, stockNumbers, itemName);
    	}
    }

    private void loadStock(ItemStock stock, Map<String, Integer> stockNumbers, String itemName) {
    	int stockNumber = stockNumbers.get(itemName);
    	Item item = determineItem(itemName);
    	stock.addItem(item, stockNumber);
    }

    private Item determineItem(String itemName) {
    	switch(itemName) {
    	case "water":
    		return itemWater;
    	case "waterEx":
    		return itemWaterEx;
    	case "fertiliser":
    		return itemFertiliser;
    	case "fertiliserPlus":
    		return itemFertiliserPlus;
    	case "sunlight":
    		return itemSunlight;
    	case "insecticide":
    		return itemInsecticide;
    	default:
    		return null;
    	}
    }
}
