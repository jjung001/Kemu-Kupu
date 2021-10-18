package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MyTreeController extends Controller {

	@FXML
	private Button back;

	@FXML
	void quitMyTree(ActionEvent event) {
		backToMain(event);
	}

}
