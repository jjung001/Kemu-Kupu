package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
	private Button btnBuyItems;

	@FXML
	void quitMyTree(ActionEvent event) {
		backToMain(event);
	}
	
	@FXML
    void buyItems(ActionEvent event) {
		switchScene(event, "MyTreeShop.fxml");
    }


}
