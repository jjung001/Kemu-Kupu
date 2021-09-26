package application;

import java.io.IOException;

import javafx.scene.control.Alert;

public class Festival {

	public static void festival(double speed, String word, Language language) {
		String voice;
		if (language.equals(Language.MAORI)) {
			voice = "voice_akl_mi_pk06_cg";
		} else {
			voice = "voice_akl_nz_jdt_diphone";
		}

		String cmd = "echo \"(" + voice + ") (Parameter.set 'Duration_Stretch " + speed + ") (SayText \\\"" + word + "\\\")\" | festival --pipe";
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Spelling Quiz Error");
			alert.setHeaderText("IOException Caught, Using Festival");
			alert.setContentText("Please contact the developer.");
			alert.showAndWait();
		}
	}
}
