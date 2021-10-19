package tree;

import enums.TreeLevel;
import enums.TreeStatus;

public class Tree {
	private double height;
	private double health;

	private double water;
	private double nutrient;
	private double chemical;

	private double waterThreshold;
	private double nutrientThreshold;
	private double chemicalThreshold;

	public Tree() {
		kill();
	}

	public void kill() {
		startAfresh();
		updateThresholds();
	}

	private void startAfresh() {
		height = 1;
		health = 100;
		water = 50;
		nutrient = 30;
		chemical = 0;
	}

	private void updateThresholds() {
		int level = getLevel();
		waterThreshold = calculateNewThreshold(100, 20);
		nutrientThreshold = calculateNewThreshold(50, 30);
		chemicalThreshold = calculateNewThreshold(30, 10);
	}

	public int getLevel() {
		return (int) Math.ceil(height);
	}

	private double calculateNewThreshold(double startingValue, double increasePerLevel) {
		int level = getLevel();
		double totalIncreaseInThreshold = (level - 1) * increasePerLevel;
		return startingValue + totalIncreaseInThreshold;
	}

	public double getHeight() {
		return height;
	}

	public TreeLevel getTreeLevel() {
		if (height < 240) {
			return TreeLevel.SPROUT;
		} else if (height < 640) {
			return TreeLevel.SAPLING;
		} else if (height < 1200) {
			return TreeLevel.YOUNG;
		} else if (height < 2600) {
			return TreeLevel.GROWN;
		} else if (height < 4000) {
			return TreeLevel.MATURE;
		} else {
			return TreeLevel.BLOOMING;
		}
	}

	public double getHealth() {
		return health;
	}

	public TreeStatus getHealthStatus() {
		if (health > 90) {
			return TreeStatus.EXCELLENT;
		} else if (health > 75) {
			return TreeStatus.VERYGOOD;
		} else if (health > 60) {
			return TreeStatus.AVERAGE;
		} else if (health > 40) {
			return TreeStatus.CONCERNING;
		} else if (health > 15) {
			return TreeStatus.POOR;
		} else if (health > 0) {
			return TreeStatus.DYING;
		} else {
			return TreeStatus.DEAD;
		}
	}

	public double getWater() {
		return water;
	}

	public double getNutrient() {
		return nutrient;
	}

	public double getChemical() {
		return chemical;
	}

	public double getWaterThreshold() {
		updateThresholds();
		return waterThreshold;
	}

	public double getNutrientThreshold() {
		updateThresholds();
		return nutrientThreshold;
	}

	public double getChemicalThreshold() {
		updateThresholds();
		return chemicalThreshold;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setWater(double water) {
		this.water = water;
	}

	public void setNutrient(double nutrient) {
		this.nutrient = nutrient;
	}

	public void setChemical(double chemical) {
		this.chemical = chemical;
	}

	public void changeHeight(double height) {
		this.height += height;
	}

	public void changeHealth(double health) {
		this.health += health;
	}

	public void changeWater(double water) {
		this.water += water;
	}

	public void changeNutrient(double nutrient) {
		this.nutrient += nutrient;
	}

	public void changeChemical(double chemical) {
		this.chemical += chemical;
	}
}
