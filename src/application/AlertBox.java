package application;

import java.util.Optional;

import controller.AlertBoxView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	String header;
	String description;
	
	public AlertBox(String header, String description) {
		this.header = header;
		this.description = description;
	}

	public boolean displayAndGetResult() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AlertBox.fxml"));
		try {
			Parent root = (Parent) loader.load();
			AlertBoxView controller = loader.getController();
			controller.setUp(header, description);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.showAndWait();
			boolean result = controller.getResult();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
