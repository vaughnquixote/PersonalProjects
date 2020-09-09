package pm4.model;

public class BicycleShop {
	protected int bicycleShopPK;
	protected String name;
	protected String phone;
	protected String email;
	protected String hasRental; // should we change this to a binary data type?
	protected String address;
	protected Neighborhood neighborhood;
	
	public BicycleShop(int bicycleShopPK, String name, String phone, String email, String hasRental, String address,
			Neighborhood neighborhood) {
		this.bicycleShopPK = bicycleShopPK;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.hasRental = hasRental;
		this.address = address;
		this.neighborhood = neighborhood;
	}

	public BicycleShop(String name, String phone, String email, String hasRental, String address,
			Neighborhood neighborhood) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.hasRental = hasRental;
		this.address = address;
		this.neighborhood = neighborhood;
	}

	public int getBicycleShopPK() {
		return bicycleShopPK;
	}

	public void setBicycleShopPK(int bicycleShopPK) {
		this.bicycleShopPK = bicycleShopPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHasRental() {
		return hasRental;
	}

	public void setHasRental(String hasRental) {
		this.hasRental = hasRental;
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