package game;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;

public class ResultsModel {

	private String level;
	private String sprout = "Sprout";
	private String sapling = "Sapling";
	private String young = "Young"; 
	private String grown = "Grown";
	private String mature = "Mature";
	private String blooming = "Blooming";
	private ScoreTracker scoreTracker;
	
	
	Image sproutImage = new Image(getClass().getResourceAsStream("/resources/Sprout_icon.png"));
	Image saplingImage = new Image(getClass().getResourceAsStream("/resources/Tree_1.png"));
	Image youngImage = new Image(getClass().getResourceAsStream("/resources/Tree_2.png"));
	Image grownImage = new Image(getClass().getResourceAsStream("/resources/Tree_3.png"));
	Image matureImage = new Image(getClass().getResourceAsStream("/resources/Tree_4.png"));
	Image bloomingImage = new Image(getClass().getResourceAsStream("/resources/Tree_final.png"));
	
	public ResultsModel(ScoreTracker scoreTracker) {
		this.scoreTracker = scoreTracker;
	}
	
	public String determineLevel(int score) {
		if (score < 60) {
			level = sprout;
		} else if (score < 160) {
			level = sapling;
		} else if (score < 300) {
			level = young;
		} else if (score < 650) {
			level = grown;
		} else if (score < 1000) {
			level = mature;
		} else {
			level = blooming;
		}
		return level;
	}
	
	public Image displayImage(String level) {
		if (level.equals(sprout)) {
			return sproutImage;
		} else if (level.equals(sapling)) {
			return saplingImage;
		} else if (level.equals(young)) {
			return youngImage;
		} else if (level.equals(grown)) {
			return grownImage;
		} else if (level.equals(mature)) {
			return matureImage;
		} else {
			return bloomingImage;
		}
	}
	
	public void setChart(LineChart<String, Number> chart) {
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.getData().add(new XYChart.Data<String, Number>(scoreTracker.getWord(1), scoreTracker.getScore(1)));
		series.getData().add(new XYChart.Data<String, Number>(scoreTracker.getWord(2), scoreTracker.getCumulativeScore(2)));
		series.getData().add(new XYChart.Data<String, Number>(scoreTracker.getWord(3), scoreTracker.getCumulativeScore(3)));
		series.getData().add(new XYChart.Data<String, Number>(scoreTracker.getWord(4), scoreTracker.getCumulativeScore(4)));
		series.getData().add(new XYChart.Data<String, Number>(scoreTracker.getWord(5), scoreTracker.getCumulativeScore(5)));
		chart.getData().add(series);
		Node line = series.getNode().lookup(".chart-series-line");
		line.setStyle("-fx-stroke: #2e979b");
	}
}
