package application;

import java.util.Optional;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertBox {
	
	Stage stage;
	Button btn;
	
//	public static void display() {
//		Stage window = new Stage();
//		window.initModality(Modality.APPLICATION_MODAL);
//		
//		window.setTitle("AlertBox");
//		window.setMinWidth(250);
//		
//		Label label = new Label();
//		label.setText("Are you sure you want to quit?");
//		
//		Button closeButton = new Button("Close the window");
//		closeButton.setOnAction(e -> window.close());
//		
//		VBox layout = new VBox(10);
//		layout.getChildren().addAll(label, closeButton);
//		layout.setAlignment(Pos.CENTER);
//		
//		Scene scene = new Scene(layout);
//		window.setScene(scene);
//		window.showAndWait();
//	}
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
