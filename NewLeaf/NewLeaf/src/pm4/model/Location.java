package pm4.model;

public class Location {
	protected int locationKey;
	protected String address;
	protected String city;
	protected String state;
	protected String zipCode;
	protected String latitidue;
	protected String longitude;
	protected Neighborhood neighborhood;
	
	public Location(int locationKey, String address, String city, String state, String zipCode,
			String latitude, String longitude, Neighborhood neighborhood) {
		this.locationKey = locationKey;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.latitidue = latitude;
		this.longitude = longitude;
		this.neighborhood = neighborhood;
	}
	
	public Location(String address, String city, String state, String zipCode, String latitude, 
			String longitude, Neighborhood neighborhood) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.latitidue = latitude;
		this.longitude = longitude;
		this.neighborhood = neighborhood;
	}
	
	public int getLocationKey() {
		return locationKey;
	}

	public void setLocationKey(int locationKey) {
		this.locationKey = locationKey;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLatitidue() {
		return latitidue;
	}

	public void setLatitidue(String latitidue) {
		this.latitidue = latitidue;
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
