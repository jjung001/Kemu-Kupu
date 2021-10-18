package fileio;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tree.Tree;

public class TreeStatisticsIO extends FileIO {

	public TreeStatisticsIO(String filepath) {
		super(filepath);
	}

	public void saveTree(OffsetDateTime dateTime, Tree tree) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) + " ");
		stringBuilder.append(String.valueOf(tree.getHeight()) + " ");
		stringBuilder.append(String.valueOf(tree.getHealth()) + " ");
		stringBuilder.append(String.valueOf(tree.getWater()) + " ");
		stringBuilder.append(String.valueOf(tree.getNutrient()) + " ");
		stringBuilder.append(String.valueOf(tree.getChemical()));
		ArrayList<String> lines = new ArrayList<>();
		lines.add(stringBuilder.toString());
		overwriteLines(lines);
	}

	public OffsetDateTime getTreeLastSavedDateTime() {
		OffsetDateTime dateTime;
		ArrayList<String> lines = readLines();
		String treeRecord = lines.get(0);
		String dateTimeString = getStringPartFromLine(treeRecord, 0);
		dateTime = OffsetDateTime.parse(dateTimeString);
		return dateTime;
	}

	public Tree loadTree() {
		Tree tree = new Tree();
		tree.setHeight(getTreeStatistic(1));
		tree.setHealth(getTreeStatistic(2));
		tree.setWater(getTreeStatistic(3));
		tree.setNutrient(getTreeStatistic(4));
		tree.setChemical(getTreeStatistic(5));
		return tree;
	}

	private double getTreeStatistic(int index) {
		double statistic;
		ArrayList<String> lines = readLines();
		String treeRecord = lines.get(0);
		String treeStatisticString = getStringPartFromLine(treeRecord, index);
		statistic = Double.valueOf(treeStatisticString);
		return statistic;
	}
}
