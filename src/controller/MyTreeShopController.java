package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    @FXML
    void quitShop(ActionEvent event) {
		switchScene(event, "MyTree.fxml");
    }
}
