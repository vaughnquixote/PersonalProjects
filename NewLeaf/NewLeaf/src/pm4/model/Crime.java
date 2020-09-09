package pm4.model;

public class Crime {
	protected int crimeKey;
	protected String premiseType;
	protected String offense;
	protected String year;
	protected String latitude;
	protected String longitude;
	protected Neighborhood neighborhood;
	
	public Crime(int crimeKey, String premiseType, String offense, String year, 
			String latitude, String longitude, Neighborhood neighborhood) {
		this.crimeKey = crimeKey;
		this.premiseType = premiseType;
		this.offense = offense;
		this.year = year;
		this.latitude = latitude;
		this.longitude = longitude;
		this.neighborhood = neighborhood;
	}
	
	public Crime(String premiseType, String offense, String year, String latitude, 
			String longitude, Neighborhood neighborhood) {
		this.premiseType = premiseType;
		this.offense = offense;
		this.year = year;
		this.latitude = latitude;
		this.longitude = longitude;
		this.neighborhood = neighborhood;
	}
	
	public int getCrimeKey() {
		return crimeKey;
	}

	public void setCrimeKey(int crimeKey) {
		this.crimeKey = crimeKey;
	}

	public String getPremiseType() {
		return premiseType;
	}

	public void setPremiseType(String premiseType) {
		this.premiseType = premiseType;
	}

	public String getOffense() {
		return offense;
	}

	public void setOffense(String offense) {
		this.offense = offense;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}
}
