package application;

import java.io.IOException;
/**
 * Runs Festival interpreter.
 * @author Juwon Jung
 *
 */
public class Festival {
	
	/**
	 * Runs the festival interpreter according to speed of speech, 
	 * current word and voice of language.
	 * @param speed the speed of speech
	 * @param word the current word to be read out from festival
	 * @param language voice for festival to interpret with different language voices
	 */
	public static void festival(double speed, String word, Language language) {
		String voice;
		if (language.equals(Language.MAORI)) {
			voice = "voice_akl_mi_pk06_cg";
		} else {
			voice = "voice_akl_nz_jdt_diphone";
		}

		String cmd = "echo \"(" + voice + ") (Parameter.set 'Duration_Stretch " + speed + ") (SayText \\\"" + word
				+ "\\\")\" | festival --pipe";
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
