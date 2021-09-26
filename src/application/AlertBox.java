package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
/**
 * Creates Alert Boxes.
 * @author Juwon Jung
 *
 */
public class AlertBox {

	Stage stage;
	Button btn;

	/**
	 * Displays alert box when user tries to exit out of scene.
	 * User needs to select quit button to exit from current scene.
	 * @param header String to put into header of alert box
	 * @param content String to put into context of alert box
	 * @return
	 */
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
