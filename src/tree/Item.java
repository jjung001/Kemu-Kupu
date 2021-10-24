package tree;

public class Item {
	private String name;
	private int cost;
	private String imagePath;
	private double heightImpact;
	private double healthImpact;
	private double waterImpact;
	private double nutrientImpact;
	private double chemicalImpact;

	public Item(String name, int cost, String imagePath, double heightImpact, double healthImpact, double waterImpact, double nutrientImpact, double chemicalImpact) {
		this.name = name;
		this.cost = cost;
		this.imagePath = imagePath;
		this.heightImpact = heightImpact;
		this.healthImpact = healthImpact;
		this.waterImpact = waterImpact;
		this.nutrientImpact = nutrientImpact;
		this.chemicalImpact = chemicalImpact;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null) return false;
		if (object.getClass() != getClass()) return false;
		Item other = (Item) object;
		return other.getName().equals(getName())
				&& (other.getCost() == getCost());
	}

	@Override
	public int hashCode() {
		int result = 0;
		int randomNumber = 13;
		result += name.hashCode() * randomNumber;
		result += cost * 2;
		return result;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public String getImagePath() {
		return imagePath;
	}

	public double getHeightImpact() {
		return heightImpact;
	}

	public double getHealthImpact() {
		return healthImpact;
	}

	public double getWaterImpact() {
		return waterImpact;
	}

	public double getNutrientImpact() {
		return nutrientImpact;
	}

	public double getChemicalImpact() {
		return chemicalImpact;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setHeightImpact(double heightImpact) {
		this.heightImpact = heightImpact;
	}

	public void setHealthImpact(double healthImpact) {
		this.healthImpact = healthImpact;
	}

	public void setWaterImpact(double waterImpact) {
		this.waterImpact = waterImpact;
	}

	public void setNutrientImpact(double nutrientImpact) {
		this.nutrientImpact = nutrientImpact;
	}

	public void setChemicalImpact(double chemicalImpact) {
		this.chemicalImpact = chemicalImpact;
	}
}
