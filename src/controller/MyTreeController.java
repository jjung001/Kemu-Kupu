package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import application.AlertBox;
import application.Cash;
import application.FileSaveLocations;
import enums.TreeStatus;
import enums.TreeLevel;
import tree.Item;
import tree.ItemStock;
import tree.Tree;
import fileio.CashIO;
import fileio.ItemStockIO;
import fileio.TreeStatisticsIO;

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
    private Label itemNoLabelWater;
    @FXML
    private Button btnUseWater;
    @FXML
    private Label itemNoLabelWaterEx;
    @FXML
    private Button btnUseWaterEx;
    @FXML
    private Label itemNoLabelFertiliser;
    @FXML
    private Button btnUseFertiliser;
    @FXML
    private Label itemNoLabelFertiliserPlus;
    @FXML
    private Button btnUseFertiliserPlus;
    @FXML
    private Label itemNoLabelSunlight;
    @FXML
    private Button btnUseSunlight;
    @FXML
    private Label itemNoLabelInsecticide;
    @FXML
    private Button btnUseInsecticide;
    @FXML
    private Button btnAxe;
	@FXML
	private Button btnBuyItems;
	
	private Tree tree;
	private OffsetDateTime offSetDateTime;
	private TreeStatisticsIO treeStatistics;
	private Cash treeMoney;;
	private CashIO cashIO;
	private ItemStockIO itemStockIO;
	private ItemStock stock;
	private TreeStatisticsIO treeStatisticsIO;

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
    	String header = "Are you sure you want to cut down your tree?";
		String description = "Your tree will die.";
		AlertBox alertBox = new AlertBox(header, description);
		boolean result = alertBox.displayAndGetResult();

		if (result) {
	    	tree.kill();
	    	StatusHeightHealth();
	    	treeStatistics.saveTree(offSetDateTime, tree);
//			switchScene(event, "MyTree.fxml");
		}
    }
    
    @FXML
    void useWater(ActionEvent event) {
//    	Item item = new Item();
//    	String nameWater = "water";
//    	item.setName(nameWater);
////    	stock.removeItem(item);
////    	itemStockIO.loadStockNumbers();
//    	Map<String, Integer> stockNumbers = new LinkedHashMap<>();
//    	stockNumbers = itemStockIO.loadStockNumbers();
//    	for (Map.Entry<String, Integer> entry : stockNumbers.entrySet()) {
//    	    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//    	}
//    	stock.removeItem(item);
//    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
//    	itemStockIO.loadStockNumbers();
////    	String waterQuantity = Integer.toString(stock.getQuantity(item));
////    	itemNoLabelWater.setText(waterQuantity);
//    	if (stock.isInStock(item)) {
//    		stock.getQuantity(item);
//        	System.out.println("water quantity= "+stock.getQuantity(item));
//    	} else {
//        	System.out.println("not in stock");
//
//    	}
    	itemNoLabelWater.setText("x1");
    	healthLabel.setText("EXCELLENT");
    	statusLabel.setText("SAPLING");
    	heightLabel.setText("5.0m");
    	treeImage.setImage(saplingImage);
    }

    @FXML
    void useWaterEx(ActionEvent event) {
    	Item itemWater = new Item();
    	itemWater.setName("waterEx");
    	ItemStock itemStocking = new ItemStock();
    	ArrayList<Item> itemArray = itemStocking.getItems();
//    	itemArray = stock.getItems();
    	for (int i = 0; i < itemArray.size(); i++) {
    		if (itemArray.get(i).equals(itemWater)) {
    			itemStocking.removeItem(itemArray.get(i));
    		}
    	}

//    	itemStockIO.saveStockNumbers(stock, offSetDateTime);
    	    	
//    	stock.removeItem(item);
//    	itemStockIO.loadStockNumbers();
    	Map<String, Integer> stockNumbers = itemStockIO.loadStockNumbers();
    	for (Map.Entry<String, Integer> entry : stockNumbers.entrySet()) {
    	    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
    	}
    }
    
    @FXML
    void useFertiliser(ActionEvent event) {

    }

    @FXML
    void useFertiliserPlus(ActionEvent event) {

    }

    @FXML
    void useSunlight(ActionEvent event) {

    }

    @FXML
    void useInsecticide(ActionEvent event) {
    	itemNoLabelInsecticide.setText("x0");
    	healthLabel.setText("EXCELLENT");
    	treeImage.setImage(sproutImage);
    }
    
    private void StatusHeightHealth() {
    	TreeStatus treestatus = tree.getHealthStatus();
    	String name = treestatus.name();
    	healthLabel.setText(name);
    	String height = String.valueOf(tree.getHeight());
    	heightLabel.setText(height+"m");
    	TreeLevel treeLevel = tree.getTreeLevel();
    	String level = treeLevel.name();
    	statusLabel.setText(level);
    	
    	if (level.equals(sprout)) {
    		if (tree.getHealth() == 0) {
    			treeImage.setVisible(false);
    		}
    	} else {
        	treeImage.setImage(displayImage(level));
    	}
    	
    }
    
    public Image displayImage(String level) {
		if (level.equals(sprout)) {
			if (tree.getHealth() > 40) {
				return sproutImage;
			} else {
				return sproutImage2;
			}
		} else if (level.equals(sapling)) {
			if (tree.getHealth() > 40) {
				return saplingImage;
			} else {
				return saplingImage2;
			}
		} else if (level.equals(young)) {
			if (tree.getHealth() > 40) {
				return youngImage;
			} else {
				return youngImage2;
			}
		} else if (level.equals(grown)) {
			if (tree.getHealth() > 40) {
				return grownImage;
			} else {
				return grownImage2;
			}
		} else if (level.equals(mature)) {
			if (tree.getHealth() > 40) {
				return matureImage;
			} else {
				return matureImage2;
			}
		} else {
			if (tree.getHealth() > 40) {
				return bloomingImage;
			} else {
				return bloomingImage2;
			}
		}
	}
    
    private void itemNoInventory(Map<String, Integer> stockNumbers, HashMap<String, Label> useButtons) {
    	for (Map.Entry<String, Integer> entry : stockNumbers.entrySet()) {
    	    for (HashMap.Entry<String, Label> entry1 : useButtons.entrySet()) {
    	    	if (entry1.getKey().equals(entry.getKey())) {
    	    		entry1.getValue().setText("x"+entry.getValue().toString());
    	    	}
    	    }
    	}
    }
    
    private void itemLabelHashMap(HashMap<String, Label> useButtons) {
    	useButtons.put("water", itemNoLabelWater);
    	useButtons.put("waterEx", itemNoLabelWaterEx);
    	useButtons.put("fertiliser", itemNoLabelFertiliser);
    	useButtons.put("fertiliserPlus", itemNoLabelFertiliserPlus);
    	useButtons.put("sunlight", itemNoLabelSunlight);
    	useButtons.put("insecticide", itemNoLabelInsecticide);
    }
    
    private void initialiseItemLabels() {
    	itemNoLabelWater.setText("x"+0);
    	itemNoLabelWaterEx.setText("x"+0);
    	itemNoLabelFertiliser.setText("x"+0);
    	itemNoLabelFertiliserPlus.setText("x"+0);
    	itemNoLabelSunlight.setText("x"+0);
    	itemNoLabelInsecticide.setText("x"+0);
    }
    
    private void getDuration(ItemStockIO itemStockIO) {
    	offSetDateTime = OffsetDateTime.now(); 
    	String s = offSetDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    	System.out.println(s);
    	OffsetDateTime date = treeStatisticsIO.getTreeLastSavedDateTime();
      	Duration duration = Duration.between(date, offSetDateTime);
    	System.out.println("Duration: " + duration);
    	long durationSeconds = duration.getSeconds();
        System.out.println("Difference: " + durationSeconds + " seconds");
        decreaseHealth(durationSeconds);
    }
    
    private void decreaseHealthSeconds(int seconds) {
    	double treeHealth = tree.getHealth();
		System.out.println("decreaseHealth = "+treeHealth);

		if (treeHealth  - 25 <= 0) {
			tree.setHealth(0);
		} else {
    		tree.setHealth(treeHealth-25);
		}

		TreeStatus treestatus = tree.getHealthStatus();
		String name = treestatus.name();
		healthLabel.setText(name);
		double treeHealtha = tree.getHealth();
		System.out.println("decreaseHealth = "+treeHealtha);
    }
    
    private void decreaseHealth(long durationSeconds) {
    	if (durationSeconds > 40) {
    		decreaseHealthSeconds(40);
    	} else if (durationSeconds > 20) {
    		decreaseHealthSeconds(20);
    	}
    	treeStatisticsIO.saveTree(offSetDateTime, tree);
    }
    
    public void initialize() {
    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	
    	tree = new Tree();
    	treeStatisticsIO = new TreeStatisticsIO(FileSaveLocations.TREE_STATISTICS);
    	tree = treeStatisticsIO.loadTree();
    
    	getDuration(itemStockIO);
    	
    	
    	cashIO = new CashIO(FileSaveLocations.CASH);
    	treeMoney = cashIO.loadCash();
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);

    	StatusHeightHealth();

    	
    	Map<String, Integer> stockNumbers = new LinkedHashMap<>();
    	stockNumbers = itemStockIO.loadStockNumbers();
    	HashMap<String, Label> useButtons = new HashMap<>();
    	itemLabelHashMap(useButtons);
    	initialiseItemLabels();
    	itemNoInventory(stockNumbers, useButtons);
		stock = new ItemStock();

    }
    	
}
