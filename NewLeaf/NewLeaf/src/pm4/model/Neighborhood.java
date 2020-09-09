/**
 * Nolan Bock
 * CS5200 - HW4
 */


package pm4.model;

public class Neighborhood {
	protected int neighborhoodKey;
	protected String name;
	
	public Neighborhood(int companyKey,  String name) {
		this.neighborhoodKey = companyKey;
		this.name = name;
	}
	
	public Neighborhood(String name) {
		this.name = name;
	}

	public int getNeighborhoodKey() {
		return neighborhoodKey;
	}

	public void setNeighborhoodKey(int neighborhoodKey) {
		this.neighborhoodKey = neighborhoodKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
