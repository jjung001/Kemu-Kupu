package fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileIO {
	protected String filepath;

	protected FileIO(String filepath) {
		this.filepath = filepath;
	}

	protected ArrayList<String> readLines() {
		ArrayList<String> lines = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File(filepath));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().strip();
				lines.add(line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
//			System.err.println("File not found!");
//			e.printStackTrace();
		}
		return lines;
	}

	protected void overwriteLines(ArrayList<String> lines) {
		writeLines(lines, false);
	}

	protected void appendLines(ArrayList<String> lines) {
		writeLines(lines, true);
	}

	protected void appendLine(String line) {
		ArrayList<String> lines = new ArrayList<>();
		lines.add(line);
		writeLines(lines, true);
	}

	private void writeLines(ArrayList<String> lines, boolean appendMode) {
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(filepath, appendMode));
			for (String line : lines) {
				String formatted = line.strip();
				printWriter.println(formatted);
			}
			printWriter.close();
		} catch (IOException e) {
//			System.err.println("Cannot write to specified filepath.");
//			System.err.println("Please contact the developers.");
//			e.printStackTrace();
		}
	}

	protected String getStringPartFromLine(String line, int index) {
		String formatted = line.strip();
		String[] splitString = formatted.split("\\s+");
		return splitString[index];
	}
}
