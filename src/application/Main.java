package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
        	Parent root = FXMLLoader.load(getClass().getResource("/view/GameScreen.fxml"));
            Scene main = new Scene(root);
            main.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //Display scene
            primaryStage.setScene(main);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
