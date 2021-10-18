package controller;

import java.io.IOException;
import java.util.ArrayList;

import application.FileSaveLocations;
import fileio.StatisticsIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import statistics.ProportionsFeed;
import statistics.TimelineFeed;

public class SpellingPerformanceView extends Controller {
	@FXML
	private Button controlsVocabulary;
	@FXML
	private Button controlsWordEarnings;
	@FXML
	private Button timelineTabButton;
	@FXML
	private Button proportionsTabButton;
	@FXML
	private ToggleButton timeScaleToggle;
	@FXML
	private AnchorPane graphHolder;

	private boolean isWeekly = false;
	private boolean isProportions = false;
	private Image onButtonImage = new Image(getClass().getResourceAsStream("/resources/Toggle_Button_On.png"));
	private Image offButtonImage = new Image(getClass().getResourceAsStream("/resources/Toggle_Button_Off.png"));

	@FXML
	private void initialize() {
		viewTimeline();
	}

	private void setGraph(Node node) {
		graphHolder.getChildren().setAll(node);
	}

	@FXML
	private void viewTimeline() {
		isProportions = false;
		darkenButton(timelineTabButton);
		lightenButton(proportionsTabButton);

		StatisticsIO statisticsIO = new StatisticsIO(FileSaveLocations.STATISTICS);

		TimelineFeed timelineFeedMastered;
		TimelineFeed timelineFeedFaulted;
		TimelineFeed timelineFeedFailed;

		if (isWeekly) {
			timelineFeedMastered = statisticsIO.getMasteredTimelineFeedForWeek();
			timelineFeedFaulted = statisticsIO.getFaultedTimelineFeedForWeek();
			timelineFeedFailed = statisticsIO.getFailedTimelineFeedForWeek();
		} else {
			timelineFeedMastered = statisticsIO.getMasteredTimelineFeedForDay();
			timelineFeedFaulted = statisticsIO.getFaultedTimelineFeedForDay();
			timelineFeedFailed = statisticsIO.getFailedTimelineFeedForDay();
		}

		XYChart.Series<String, Integer> masteredSeries = new XYChart.Series<>();
		XYChart.Series<String, Integer> faultedSeries = new XYChart.Series<>();
		XYChart.Series<String, Integer> failedSeries = new XYChart.Series<>();

		masteredSeries.setName("Mastered");
		faultedSeries.setName("Faulted");
		failedSeries.setName("Failed");

		loadTimelineFeedToSeries(timelineFeedMastered, masteredSeries);
		loadTimelineFeedToSeries(timelineFeedFaulted, faultedSeries);
		loadTimelineFeedToSeries(timelineFeedFailed, failedSeries);

		try {
			LineChart lineChart = FXMLLoader.load(getClass().getResource("/view/SPTimeline.fxml"));
			lineChart.getData().add(masteredSeries);
			lineChart.getData().add(faultedSeries);
			lineChart.getData().add(failedSeries);
			setGraph(lineChart);
		} catch (IOException e) {
			// Keep empty
		}
	}

	private void loadTimelineFeedToSeries(TimelineFeed timelineFeed, XYChart.Series<String, Integer> series) {
		ArrayList<String> categoryStrings = timelineFeed.getCategories();
		for (String category : categoryStrings) {
			int dataValue = timelineFeed.getDataOnExistingCategory(category);
			series.getData().add(new XYChart.Data<String, Integer>(category, dataValue));
		}
	}

	@FXML
	private void viewProportions() {
		isProportions = true;
		lightenButton(timelineTabButton);
		darkenButton(proportionsTabButton);

		StatisticsIO statisticsIO = new StatisticsIO(FileSaveLocations.STATISTICS);

		ProportionsFeed proportionsFeed;
		if (isWeekly) {
			proportionsFeed = statisticsIO.getProportionsFeedForWeek();
		} else {
			proportionsFeed = statisticsIO.getProportionsFeedForDay();
		}

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Mastered", proportionsFeed.getMastered()),
				new PieChart.Data("Faulted", proportionsFeed.getFaulted()),
				new PieChart.Data("Failed", proportionsFeed.getFailed()));

		try {
			PieChart pieChart = FXMLLoader.load(getClass().getResource("/view/SPProportions.fxml"));
			pieChart.setData(pieChartData);
			setGraph(pieChart);
		} catch (IOException e) {
			// Keep empty
		}
	}

	private void darkenButton(Button button) {
		button.setTextFill(Paint.valueOf("#eeeeee"));
		button.setStyle("-fx-background-color: #714e42;");
	}

	private void lightenButton(Button button) {
		button.setTextFill(Paint.valueOf("#666666"));
		button.setStyle("-fx-background-color: #EEEEEE;");
	}

	@FXML
	private void toggleTimeScale() {
		if (isWeekly) {
			isWeekly = false;
			timeScaleToggle.setGraphic(new ImageView(offButtonImage));
		} else {
			isWeekly = true;
			timeScaleToggle.setGraphic(new ImageView(onButtonImage));
		}

		if (isProportions) {
			viewProportions();
		} else {
			viewTimeline();
		}
	}
}
