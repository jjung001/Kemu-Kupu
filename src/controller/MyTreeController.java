package controller;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import application.AlertBox;
import application.Cash;
import application.FileSaveLocations;
import enums.TreeLevel;
import enums.TreeStatus;
import fileio.CashIO;
import fileio.ItemStockIO;
import fileio.TreeStatisticsIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tree.Item;
import tree.ItemStock;
import tree.Tree;

public class MyTreeController extends Controller {

	@FXML
	private Button back;
	@FXML
	private Label moneyLabel;
	@FXML
	private ImageView treeImage;
	@FXML
	private Label statusLabel;
	@FXML
	private Label heightLabel;
	@FXML
	private Label healthLabel;
	@FXML
    private Label itemLabelWater;
    @FXML
    private Button btnUseWater;
    @FXML
    private Label itemLabelWaterEx;
    @FXML
    private Button btnUseWaterEx;
    @FXML
    private Label itemLabelFertiliser;
    @FXML
    private Button btnUseFertiliser;
    @FXML
    private Label itemLabelFertiliserPlus;
    @FXML
    private Button btnUseFertiliserPlus;
    @FXML
    private Label itemLabelSunlight;
    @FXML
    private Button btnUseSunlight;
    @FXML
    private Label itemLabelInsecticide;
    @FXML
    private Button btnUseInsecticide;
    @FXML
    private Button btnAxe;
	@FXML
	private Button btnBuyItems;

	private Tree tree;
	private OffsetDateTime offSetDateTime;
	private Cash treeMoney;;
	private CashIO cashIO;
	private ItemStockIO itemStockIO;
	private ItemStock stock;
	private TreeStatisticsIO treeStatisticsIO;
	
	private Item itemWater;
	private Item itemWaterEx;
	private Item itemFertiliser;
	private Item itemFertiliserPlus;
	private Item itemSunlight;
	private Item itemInsecticide;
	
	private Map<String, Integer> stockNumbers;
	private HashMap<String, Label> useButtons;

	Image sproutImage = new Image(getClass().getResourceAsStream("/resources/Sprout_icon.png"));
	Image saplingImage = new Image(getClass().getResourceAsStream("/resources/Tree_1.png"));
	Image youngImage = new Image(getClass().getResourceAsStream("/resources/Tree_2.png"));
	Image grownImage = new Image(getClass().getResourceAsStream("/resources/Tree_3.png"));
	Image matureImage = new Image(getClass().getResourceAsStream("/resources/Tree_4.png"));
	Image bloomingImage = new Image(getClass().getResourceAsStream("/resources/Tree_final.png"));
	Image sproutImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Sprout_icon.png"));
	Image saplingImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_1.png"));
	Image youngImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_2.png"));
	Image grownImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_3.png"));
	Image matureImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_4.png"));
	Image bloomingImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_final.png"));
	Image sproutImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Sprout.png"));
	Image saplingImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_1.png"));
	Image youngImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_2.png"));
	Image grownImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_3.png"));
	Image matureImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_4.png"));
	Image bloomingImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_final.png"));
	Image deadSign = new Image(getClass().getResourceAsStream("/resources/Dead_sign.png"));
	private String sprout = "SPROUT";
	private String sapling = "SAPLING";
	private String young = "YOUNG";
	private String grown = "GROWN";
	private String mature = "MATURE";
	private String blooming = "BLOOMING";

	@FXML
	void quitMyTree(ActionEvent event) {
		backToMain(event);
	}

	@FXML
    void buyItems(ActionEvent event) {
		switchScene(event, "MyTreeShop.fxml");
    }

    @FXML
    void cutDownTree(ActionEvent event) {
    	String header = "Are you sure you want to cut down \nyour tree?";
		String description = "Your tree will die.";
		AlertBox alertBox = new AlertBox(header, description);
		boolean result = alertBox.displayAndGetResult();
		if (result) {
	    	tree.kill();
	    	StatusHeightHealth();
	    	treeStatisticsIO.saveTree(offSetDateTime, tree);
		}
    }

    @FXML
    void useWater(ActionEvent event) {
    	useItem(itemWater);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    @FXML
    void useWaterEx(ActionEvent event) {
    	useItem(itemWaterEx);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    @FXML
    void useFertiliser(ActionEvent event) {
    	useItem(itemFertiliser);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    @FXML
    void useFertiliserPlus(ActionEvent event) {
    	useItem(itemFertiliserPlus);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    @FXML
    void useSunlight(ActionEvent event) {
    	useItem(itemSunlight);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    @FXML
    void useInsecticide(ActionEvent event) {
    	useItem(itemInsecticide);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }
    
    public void useItem(Item item) {
    	if (stock.isInStock(item)) {
    		double healthImpact = item.getHealthImpact();
    		double health = tree.getHealth()+healthImpact;
    		if (health >= 6) {
    			tree.setHealth(6);
    		} else {
        		tree.setHealth(health);
    		}
    		double heightImpact = item.getHeightImpact();
    		double height = tree.getHeight()+heightImpact;
    		tree.setHeight(height);
    	}
    	stock.removeItem(item);
    	StatusHeightHealth();
    	saveItemStockAndMoney();
    }
    
    private void saveItemStockAndMoney() {
    	cashIO.saveCash(treeMoney);
    	itemStockIO.saveStockNumbers(stock, OffsetDateTime.now());
    	treeStatisticsIO.saveTree(offSetDateTime, tree);
    }

    private void StatusHeightHealth() {
    	if (tree.getHealth() == 0) {
    		disableUseButton(true);
//    		treeImage.setVisible(false);
    		treeImage.setImage(deadSign);
    		setStatusHeightHealth();
    	} else {
    		disableUseButton(false);
    		treeImage.setVisible(true);
    		String level = setStatusHeightHealth();
    		treeImage.setImage(displayImage(level));
    	}
    }
    
    private void disableUseButton(boolean disable) {
    	btnUseWater.setDisable(disable);
		btnUseWaterEx.setDisable(disable);
		btnUseFertiliser.setDisable(disable);
		btnUseFertiliserPlus.setDisable(disable);
		btnUseSunlight.setDisable(disable);
		btnUseInsecticide.setDisable(disable);
    }
    
    public String setStatusHeightHealth() {
    	TreeStatus treestatus = tree.getHealthStatus();
    	String health = treestatus.name();
    	healthLabel.setText(health);
    	String height = String.valueOf(tree.getHeight());
    	heightLabel.setText(height+"cm");
    	TreeLevel treeLevel = tree.getTreeLevel();
    	String level = treeLevel.name();
    	statusLabel.setText(level);
    	return level;
    }
    
    public Image displayImage(String level) {
		if (level.equals(sprout)) {
			if (tree.getHealth() >= 5) {
				return sproutImage;
			} else if (tree.getHealth() >= 3) {
				return sproutImage2;
			} else {
				return sproutImage3;
			}	
		} else if (level.equals(sapling)) {
			if (tree.getHealth() >= 5) {
				return saplingImage;
			} else if (tree.getHealth() >= 3) {
				return saplingImage2;
			} else {
				return saplingImage3;
			}
		} else if (level.equals(young)) {
			if (tree.getHealth() >= 5) {
				return youngImage;
			} else if (tree.getHealth() >= 3) {
				return youngImage2;
			} else {
				return youngImage3;
			}
		} else if (level.equals(grown)) {
			if (tree.getHealth() >= 5) {
				return grownImage;
			} else if (tree.getHealth() >= 3) {
				return grownImage2;
			} else {
				return grownImage3;
			}
		} else if (level.equals(mature)) {
			if (tree.getHealth() >= 5) {
				return matureImage;
			} else if (tree.getHealth() >= 3) {
				return matureImage2;
			} else {
				return matureImage3;
			}
		} else {
			if (tree.getHealth() >= 5) {
				return bloomingImage;
			} else if (tree.getHealth() >= 3) {
				return bloomingImage2;
			} else {
				return bloomingImage3;
			}
		}
	}

    private void itemInventory(Map<String, Integer> stockNumbers, HashMap<String, Label> useButtons) {
    	for (Map.Entry<String, Integer> entry : stockNumbers.entrySet()) {
    	    for (HashMap.Entry<String, Label> entry1 : useButtons.entrySet()) {
    	    	if (entry1.getKey().equals(entry.getKey())) {
    	    		entry1.getValue().setText("x"+entry.getValue().toString());
    	    	}
    	    }
    	}
    }

    private void itemLabelHashMap(HashMap<String, Label> useButtons) {
    	useButtons.put("water", itemLabelWater);
    	useButtons.put("waterEx", itemLabelWaterEx);
    	useButtons.put("fertiliser", itemLabelFertiliser);
    	useButtons.put("fertiliserPlus", itemLabelFertiliserPlus);
    	useButtons.put("sunlight", itemLabelSunlight);
    	useButtons.put("insecticide", itemLabelInsecticide);
    }

    private void initialiseItemLabels() {
    	itemLabelWater.setText("x"+0);
    	itemLabelWaterEx.setText("x"+0);
    	itemLabelFertiliser.setText("x"+0);
    	itemLabelFertiliserPlus.setText("x"+0);
    	itemLabelSunlight.setText("x"+0);
    	itemLabelInsecticide.setText("x"+0);
    }

    private void getDuration(ItemStockIO itemStockIO) {
    	offSetDateTime = OffsetDateTime.now();
    	String s = offSetDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    	System.out.println(s);
    	OffsetDateTime date = itemStockIO.getDateTimeSaved();
      	Duration duration = Duration.between(date, offSetDateTime);
    	System.out.println("Duration: " + duration);
    	long durationSeconds = duration.getSeconds();
        System.out.println("Difference: " + durationSeconds + " seconds");
        decreaseHealth(durationSeconds);
    }

    private void decreaseHealthSeconds(int seconds) {
    	double treeHealth = tree.getHealth();
		System.out.println("decreaseHealth = "+treeHealth);
		if (treeHealth-seconds <= 0) {
			tree.setHealth(0);
		} else {
    		tree.setHealth(treeHealth-seconds);
		}
		TreeStatus treestatus = tree.getHealthStatus();
		String name = treestatus.name();
		healthLabel.setText(name);
		double treeHealtha = tree.getHealth();
		System.out.println("decreaseHealth = "+treeHealtha);
    }

    private void decreaseHealth(long durationSeconds) {
//    	if (durationSeconds > 120) {
//    		decreaseHealthSeconds(6);
//    	} else if (durationSeconds > 100) {
//    		decreaseHealthSeconds(5);
//    	} else if (durationSeconds > 80) {
//    		decreaseHealthSeconds(4);
//    	} else if (durationSeconds > 60) {
//    		decreaseHealthSeconds(3);
//    	} else if (durationSeconds > 40) {
//    		decreaseHealthSeconds(2);
//    	} else if (durationSeconds > 20){
//    		decreaseHealthSeconds(1);
//    	}
    	if (durationSeconds > 120) {
    		decreaseHealthSeconds(1);
    	}
    	treeStatisticsIO.saveTree(offSetDateTime, tree);
    }

    public void initialize() {
    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	treeStatisticsIO = new TreeStatisticsIO(FileSaveLocations.TREE_STATISTICS);
    	tree = treeStatisticsIO.loadTree();
    	getDuration(itemStockIO);

    	cashIO = new CashIO(FileSaveLocations.CASH);
    	treeMoney = cashIO.loadCash();
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);

    	StatusHeightHealth();
    	
    	stockNumbers = new LinkedHashMap<>();
    	stockNumbers = itemStockIO.loadStockNumbers();
    	useButtons = new HashMap<>();
    	itemLabelHashMap(useButtons);
    	initialiseItemLabels();
    	itemInventory(stockNumbers, useButtons);
    	
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
