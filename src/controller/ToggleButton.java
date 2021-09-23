package controller;

import javafx.scene.control.Button;

public class ToggleButton extends Button {
	
	private boolean state = false;
	
	public boolean getState() {
		return this.state;
	}
	
	public void turnOnOrOff(String id) {
		if (this.getState()) {
			this.state = false;
			//TO Do image view becomes turn off image
		}
		else {
			this.state = true;
			//To Do image view becomes turn on image
		}
	}
	
}
