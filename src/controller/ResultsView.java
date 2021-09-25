package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResultsView extends Controller{

	@FXML
	private Button playAgainButton;
	@FXML
	private Button backButton;
	@FXML
	private Label scoreLabel;
	@FXML
	private Label levelLabel;
	@FXML
	private ImageView treeImage;
	@FXML
	LineChart<String, Number> scoreChart;
	
	private String scoreDisplay;
	private String level;
	private int totalScore;
	private String sprout = "Sprout";
	private String sapling = "Sapling";
	private String young = "Young"; 
	private String grown = "Grown";
	private String mature = "Mature";
	private String blooming = "Blooming";
	
	Image sproutImage = new Image(getClass().getResourceAsStream("/resources/Sprout_icon.png"));
	Image saplingImage = new Image(getClass().getResourceAsStream("/resources/Tree_1.png"));
	Image youngImage = new Image(getClass().getResourceAsStream("/resources/Tree_2.png"));
	Image grownImage = new Image(getClass().getResourceAsStream("/resources/Tree_3.png"));
	Image matureImage = new Image(getClass().getResourceAsStream("/resources/Tree_4.png"));
	Image bloomingImage = new Image(getClass().getResourceAsStream("/resources/Tree_final.png"));
	
	public void initialize () {
		totalScore = 300;///getTotalScore();
		scoreDisplay = String.valueOf(totalScore);
		scoreLabel.setText(scoreDisplay);
		
		level = determineLevel(totalScore);
		levelLabel.setText(level);
		
		displayImage(level);
		
		setChart(scoreChart);
	}
	
	private String determineLevel(int score) {
		if (score < 50) {
			level = sprout;
		} else if (score >= 50 && totalScore < 100) {
			level = sapling;
		} else if (score >= 100 && totalScore < 150) {
			level = young;
		} else if (score >= 150 && totalScore < 200) {
			level = grown;
		} else if (score >= 200 && totalScore < 250) {
			level = mature;
		} else if (score >= 250) {
			level = blooming;
		}
		return level;
	}
	
	private void displayImage(String level) {
		if (level.equals(sprout)) {
			treeImage.setImage(sproutImage);
		} else if (level.equals(sapling)) {
			treeImage.setImage(saplingImage);
		} else if (level.equals(young)) {
			treeImage.setImage(youngImage);
		} else if (level.equals(grown)) {
			treeImage.setImage(grownImage);
		} else if (level.equals(mature)) {
			treeImage.setImage(matureImage);
		} else if (level.equals(blooming)) {
			treeImage.setImage(bloomingImage);
		}
	}
	
	private void setChart(LineChart<String, Number> chart) {
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		int  a= 30;
		series.getData().add(new XYChart.Data<String, Number>("Word 1", a));//getScore(1)));
		series.getData().add(new XYChart.Data<String, Number>("Word 2", 50));//getScore(2)));
		series.getData().add(new XYChart.Data<String, Number>("Word 3", 0));//getScore(3)));
		series.getData().add(new XYChart.Data<String, Number>("Word 4", 40));//getScore(4)));
		series.getData().add(new XYChart.Data<String, Number>("Word 5", 10));//getScore(5)));
		chart.getData().add(series);
	}
	
	public void playAgain(ActionEvent event) {
		switchScene(event, "TopicList.fxml");
	}

}
