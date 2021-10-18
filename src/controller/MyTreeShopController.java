package controller;

import java.time.OffsetDateTime;

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

    @FXML
    void quitShop(ActionEvent event) {
		switchScene(event, "MyTree.fxml");
    }
    
    @FXML
    void buyWater(ActionEvent event) {
//    	offSetDateTime = OffsetDateTime.now(); 
    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	treeMoney.setCash(treeMoney.getCash());
    	if (treeMoney.hasEnoughFunds(200)) {
    		treeMoney.withdraw(200);
    	} 
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);   
    	cashIO.saveCash(treeMoney);
    }
    
    public void initialize() {
    	btnRight.setVisible(false);
    	btnLeft.setVisible(false);
    	cashIO = new CashIO(FileSaveLocations.CASH);
    	treeMoney = cashIO.loadCash();
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);
    }
}
