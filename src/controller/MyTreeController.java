package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.time.OffsetDateTime;

import application.AlertBox;
import application.Cash;
import application.FileSaveLocations;
import enums.TreeStatus;
import enums.TreeLevel;
import tree.Tree;
import fileio.CashIO;
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
	private AnchorPane itemPane1;
	@FXML
	private Label itemLabel1;
	@FXML
	private Label itemNoLabel1;
	@FXML
	private Button btnUse1;
	@FXML
	private ImageView itemImage1;
	@FXML
	private AnchorPane itemPane2;
	@FXML
	private Label itemLabel2;
	@FXML
	private Label itemNoLabel2;
	@FXML
	private Button btnUse2;
	@FXML
	private ImageView itemImage2;
	@FXML
	private AnchorPane itemPane3;
	@FXML
	private Label itemLabel3;
	@FXML
	private Label itemNoLabel3;
	@FXML
	private Button btnUse3;
	@FXML
	private ImageView itemImage3;
	@FXML
	private Button btnUp;
	@FXML
	private Button btnDown;
    @FXML
    private Button btnAxe;
	@FXML
	private Button btnBuyItems;
	
	private Tree tree;
	private OffsetDateTime offSetDateTime;
	private TreeStatisticsIO treeStatistics;
	private Cash treeMoney;;
	private CashIO cashIO;

	Image sproutImage = new Image(getClass().getResourceAsStream("/resources/Sprout_icon.png"));
	Image saplingImage = new Image(getClass().getResourceAsStream("/resources/Tree_1.png"));
	Image youngImage = new Image(getClass().getResourceAsStream("/resources/Tree_2.png"));
	Image grownImage = new Image(getClass().getResourceAsStream("/resources/Tree_3.png"));
	Image matureImage = new Image(getClass().getResourceAsStream("/resources/Tree_4.png"));
	Image bloomingImage = new Image(getClass().getResourceAsStream("/resources/Tree_final.png"));
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
//			switchScene(event, "MyTree.fxml");
		}
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
    	
    	treeImage.setImage(displayImage(level));
    }
    
    public Image displayImage(String level) {
		if (level.equals(sprout)) {
			return sproutImage;
		} else if (level.equals(sapling)) {
			return saplingImage;
		} else if (level.equals(young)) {
			return youngImage;
		} else if (level.equals(grown)) {
			return grownImage;
		} else if (level.equals(mature)) {
			return matureImage;
		} else {
			return bloomingImage;
		}
	}
    
    public void initialize() {
//    	offSetDateTime = OffsetDateTime.now(); 
    	tree = new Tree();
    	cashIO = new CashIO(FileSaveLocations.CASH);
    	treeMoney = cashIO.loadCash();
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);
//    	treeStatistics = new TreeStatisticsIO(FileSaveLocations.TREE_STATISTICS);
//    	treeStatistics.loadTree();
    }
    	
}
