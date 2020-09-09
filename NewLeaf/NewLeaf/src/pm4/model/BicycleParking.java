package pm4.model;

public class BicycleParking {
	protected int bicycleParkingPK;
	protected String assetType;
	protected String isActive;
	protected String address;
	protected Neighborhood neighborhood;
	
	public BicycleParking(int bicycleParkingPK, String assetType, String isActive, String address,
			Neighborhood neighborhood) {
		this.bicycleParkingPK = bicycleParkingPK;
		this.assetType = assetType;
		this.isActive = isActive;
		this.address = address;
		this.neighborhood = neighborhood;
	}

	public BicycleParking(String assetType, String isActive, String address, Neighborhood neighborhood) {
		this.assetType = assetType;
		this.isActive = isActive;
		this.address = address;
		this.neighborhood = neighborhood;
	}

	public int getBicycleParkingPK() {
		return bicycleParkingPK;
	}

	public void setBicycleParkingPK(int bicycleParkingPK) {
		this.bicycleParkingPK = bicycleParkingPK;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}
}