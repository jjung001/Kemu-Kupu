package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class AlertBox {

	Stage stage;
	Button btn;

	public static boolean display(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Quit?");
		alert.setHeaderText(header);
		alert.setContentText(content);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && (result.get() == ButtonType.OK)) {
			return true;
		} else {
			return false;
		}
	}

}
