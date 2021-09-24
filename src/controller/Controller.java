package controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchScene(ActionEvent event, String nextScene) {
        try {
            root = FXMLLoader.load(getClass().getResource("..//view//" + nextScene));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void backToMain(ActionEvent back) {
		switchScene(back, "MainMenu.fxml");
	}
	
}
