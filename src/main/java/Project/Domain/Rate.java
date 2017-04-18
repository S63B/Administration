package Project.Domain;

/**
 * Created by Nekkyou on 11-4-2017.
 */
public class Rate {
	private int id;
	private double pricing;

	//region Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPricing() {
		return pricing;
	}

	public void setPricing(double pricing) {
		this.pricing = pricing;
	}
	//endregion
}
