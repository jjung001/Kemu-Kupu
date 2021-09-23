package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TopicsView extends Controller{

	@FXML
	private ToggleButton colour;
	@FXML
	private ToggleButton weather;
	@FXML
	private ToggleButton dayOfWeekOne;
	@FXML
	private ToggleButton dayOfWeekTwo;
	@FXML
	private ToggleButton monthOfYearOne;
	@FXML
	private ToggleButton monthOfYearTwo;
	@FXML
	private ToggleButton baby;
	@FXML
	private ToggleButton work;
	@FXML
	private ToggleButton feeling;
	@FXML
	private ToggleButton compassPoint;
	@FXML
	private ToggleButton university;
	@FXML
	private Button software;
	@FXML
	private Button start;
	@FXML
	private Button back;
	@FXML
	private ImageView colourSwitch;
	@FXML
	private ImageView weatherSwitch;
	@FXML
	private ImageView dayOfWeekOneSwitch;
	@FXML
	private ImageView dayOfWeekTwoSwitch;
	@FXML
	private ImageView monthOfYearOneSwitch;
	@FXML
	private ImageView monthOfYearTwoSwitch;
	@FXML
	private ImageView babySwitch;
	@FXML
	private ImageView workSwitch;
	@FXML
	private ImageView feelingSwitch;
	@FXML
	private ImageView compassPointSwitch;
	@FXML
	private ImageView universitySwitch;
	@FXML
	private ImageView softwareSWitch;
	
	Image onButtonImage = new Image(getClass().getResourceAsStream("Toggle_Button_On.png"));
	Image offButtonImage = new Image(getClass().getResourceAsStream("Toggle_Button_Off.png"));
	
	ArrayList<ImageView> buttonArray = new ArrayList <>();
	
	public void startGame(ActionEvent start) {
		switchScene(start, "GameScreen.fxml");
	}
	
	public void toggleTopic(ActionEvent event){
		ToggleButton pressedTopic = (ToggleButton) event.getSource();
		pressedTopic.turnOnOrOff();
		
		//updateWordList(pressedButton.getState());
	}
	
	public void changeSwitchImage(ActionEvent event) {
		ImageView switchToToggle = (ImageView) event.getSource();
		if (switchToToggle.getImage() == onButtonImage) {
			switchToToggle.setImage(onButtonImage);
		}
		else {
			switchToToggle.setImage(offButtonImage);
		}
	}
	
	
}
