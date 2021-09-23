package controller;

import javafx.scene.control.Button;

public class ToggleButton extends Button {
	
	private boolean state = false;
	
	public boolean getState() {
		return this.state;
	}
	
	public void turnOnOrOff() {
		if (this.getState()) {
			this.state = false;
		}
		else {
			this.state = true;
		}
	}
	
}
