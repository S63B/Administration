package Project.Domain;

import java.util.List;

/**
 * Created by Nekkyou on 11-4-2017.
 */
public class Tracker {
	private int id;
	private String serialNumber;
	private String country;
	private Car car;
	private List<Pol> polls;

	//region Getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<Pol> getPolls() {
		return polls;
	}

	public void setPolls(List<Pol> polls) {
		this.polls = polls;
	}

	//endregion


	public Tracker(int id, String serialNumber,
				   String country, Car car, List<Pol> polls) {
		this.id = id;
		this.serialNumber = serialNumber;
		this.country = country;
		this.car = car;
		this.polls = polls;
	}
}
