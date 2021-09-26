package application;

import java.io.IOException;

import javafx.scene.control.Alert;

public class Festival {

	public static void festival(double speed, String word) {

		String cmd = "echo \"(voice_akl_mi_pk06_cg) (Parameter.set 'Duration_Stretch " + speed + ") (SayText \\\"" + word + "\\\")\" | festival --pipe";
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builder.start();
//			process.waitFor();
//			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Spelling Quiz Error");
			alert.setHeaderText("IOException Caught, Using Festival");
			alert.setContentText("Please contact the developer.");
			alert.showAndWait();
		} //catch (InterruptedException e) {
//			e.printStackTrace();
//			Alert alert = new Alert(Alert.AlertType.ERROR);
//			alert.setTitle("Spelling Quiz Error");
//			alert.setHeaderText("Operation Interrupted");
//			alert.setContentText("Please contact the developer.");
//			alert.showAndWait();
//		}
	}
	
	public static void festivalEnglish(double speed, String word) {

		String cmd = "echo \"(voice_akl_nz_jdt_diphone) (Parameter.set 'Duration_Stretch " + speed + ") (SayText \\\"" + word + "\\\")\" | festival --pipe";
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builder.start();
//			process.waitFor();
//			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Spelling Quiz Error");
			alert.setHeaderText("IOException Caught, Using Festival");
			alert.setContentText("Please contact the developer.");
			alert.showAndWait();
		} //catch (InterruptedException e) {
//			e.printStackTrace();
//			Alert alert = new Alert(Alert.AlertType.ERROR);
//			alert.setTitle("Spelling Quiz Error");
//			alert.setHeaderText("Operation Interrupted");
//			alert.setContentText("Please contact the developer.");
//			alert.showAndWait();
//		}
	}
}
