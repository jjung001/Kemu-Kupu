package quiz;

<<<<<<< HEAD
import enums.AnswerStatus;
import enums.CashAmount;
=======
>>>>>>> 82af44be0a3ee7bba7546c1338a559c6da1714cf
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;

/**
 * This is a helper model class that provides methods for the Results View controller class. 
 * It has the methods to return tree level and its according image based on user's total score. 
 * It implements the line chart to be displayed in the results screen. 
 * 
 * @author Julie Kim
 *
 */
public class ResultsModel {

	private String level;
	private String sprout = "Sprout";
	private String sapling = "Sapling";
	private String young = "Young";
	private String grown = "Grown";
	private String mature = "Mature";
	private String blooming = "Blooming";
	private ScoreTracker scoreTracker;

	//Get image from file to display for tree levels
	Image sproutImage = new Image(getClass().getResourceAsStream("/resources/Sprout_icon.png"));
	Image saplingImage = new Image(getClass().getResourceAsStream("/resources/Tree_1.png"));
	Image youngImage = new Image(getClass().getResourceAsStream("/resources/Tree_2.png"));
	Image grownImage = new Image(getClass().getResourceAsStream("/resources/Tree_3.png"));
	Image matureImage = new Image(getClass().getResourceAsStream("/resources/Tree_4.png"));
	Image bloomingImage = new Image(getClass().getResourceAsStream("/resources/Tree_final.png"));

	/**
	 * Constructor receives the Score Tracker user data from the results view controller, from the games module
	 * @param scoreTracker	Score Tracker instance with user data of the results of the quiz game. 
	 */
	public ResultsModel(ScoreTracker scoreTracker) {
		this.scoreTracker = scoreTracker;
	}

	/**
	 * Returns the tree level name accordingly to the user's score of the quiz. 
	 * @param score	The total score of the user's quiz
	 * @return String level	The tree level of the user's last quiz game. 
	 */
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

	/**
	 * Returns the image of the user's last quiz game result accordingly to the total score. 
	 * @param level	The string name of the tree level. 
	 * @return Image	image of the correct tree level
	 */

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

	/**
	 * Sets up the line chart that displays cumulative score after each word
	 * Displays the progress of the score through the quiz
	 * @param chart LineChart with x,y axis propoerties
	 */
	public void setChart(LineChart<String, Number> chart) {
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.getData().add(new XYChart.Data<String, Number>(scoreTracker.getWord(1), scoreTracker.getScore(1)));
		series.getData()
				.add(new XYChart.Data<String, Number>(scoreTracker.getWord(2), scoreTracker.getCumulativeScore(2)));
		series.getData()
				.add(new XYChart.Data<String, Number>(scoreTracker.getWord(3), scoreTracker.getCumulativeScore(3)));
		series.getData()
				.add(new XYChart.Data<String, Number>(scoreTracker.getWord(4), scoreTracker.getCumulativeScore(4)));
		series.getData()
				.add(new XYChart.Data<String, Number>(scoreTracker.getWord(5), scoreTracker.getCumulativeScore(5)));
		chart.getData().add(series);
		Node line = series.getNode().lookup(".chart-series-line");
		line.setStyle("-fx-stroke: #2e979b");
	}
	
}
