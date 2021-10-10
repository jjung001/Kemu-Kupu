package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertBoxView {

	@FXML
	private Label header;
	@FXML
	private Label description;
	
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;
	
	private boolean result;
	
	public void setUp(String headerText, String descriptionText) {
		header.setText(headerText);
		description.setText(descriptionText);
	}
	
	@FXML
	private void cancelAlert() {
		result = false;
		Stage stage = (Stage) btnOK.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void OKAlert() {
		result = true;
		Stage stage = (Stage) btnOK.getScene().getWindow();
		stage.close();
	}
	
	public boolean getResult() {
		return result;
	}
	
	
}
