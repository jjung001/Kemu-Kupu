package application;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	public static boolean display() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Quit?");
		alert.setHeaderText("Are you sure you want to quit?");
		alert.setContentText("Your tree will die.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && (result.get() == ButtonType.OK)) {
			return true;
		} else {
			return false;
		}
	}

}
