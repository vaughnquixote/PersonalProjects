package pm4.model;

public class AirBnB {
	
	int AirPK;
	String Summary;
	String Space;
	String Description;
	String PropertyType;
	String RoomType;
	String Bedrooms;
	String Bathrooms;
	float SquareFeet;
	String Price;
	String WeeklyPrice;
	String MonthlyPrice;
	String SecurityDeposit;
	int Rating;
	Location Location;
	
	public AirBnB (	int AirPK, String Summary, String Space, String Description,
	String PropertyType, String RoomType, String Bedrooms, String Bathrooms,
	float SquareFeet, String Price, String WeeklyPrice, String MonthlyPrice, String SecurityDeposit,
	int Rating, Location Location) {
		
	this.AirPK = AirPK;
	this.Summary = Summary;
	this.Space = Space;
	this.Description = Description;
	this.PropertyType = PropertyType;
	this.RoomType = RoomType;
	this.Bedrooms = Bedrooms;
	this.Bathrooms = Bathrooms;
	this.SquareFeet = SquareFeet;
	this.Price = Price;
	this.WeeklyPrice  = WeeklyPrice;
	this.MonthlyPrice = MonthlyPrice;
	this.SecurityDeposit = SecurityDeposit;
	this.Rating = Rating;
	this.Location = Location;
}
	
	public AirBnB (String Summary, String Space, String Description,
	String PropertyType, String RoomType, String Bedrooms, String Bathrooms,
	float SquareFeet, String Price, String WeeklyPrice, String MonthlyPrice, String SecurityDeposit,
	int Rating, Location Location) {
		
	this.Summary = Summary;
	this.Space = Space;
	this.Description = Description;
	this.PropertyType = PropertyType;
	this.RoomType = RoomType;
	this.Bedrooms = Bedrooms;
	this.Bathrooms = Bathrooms;
	this.SquareFeet = SquareFeet;
	this.Price = Price;
	this.WeeklyPrice  = WeeklyPrice;
	this.MonthlyPrice = MonthlyPrice;
	this.SecurityDeposit = SecurityDeposit;
	this.Rating = Rating;
	this.Location = Location;
}

	public int getAirPK() {
		return AirPK;
	}

	public void setAirPK(int airPK) {
		AirPK = airPK;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public String getSpace() {
		return Space;
	}

	public void setSpace(String space) {
		Space = space;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPropertyType() {
		return PropertyType;
	}

	public void setPropertyType(String propertyType) {
		PropertyType = propertyType;
	}

	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String roomType) {
		RoomType = roomType;
	}

	public String getBedrooms() {
		return Bedrooms;
	}

	public void setBedrooms(String bedrooms) {
		Bedrooms = bedrooms;
	}

	public String getBathrooms() {
		return Bathrooms;
	}

	public void setBathrooms(String bathrooms) {
		Bathrooms = bathrooms;
	}

	public float getSquareFeet() {
		return SquareFeet;
	}

	public void setSquareFeet(float squareFeet) {
		SquareFeet = squareFeet;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getWeeklyPrice() {
		return WeeklyPrice;
	}

	public void setWeeklyPrice(String weeklyPrice) {
		WeeklyPrice = weeklyPrice;
	}

	public String getMonthlyPrice() {
		return MonthlyPrice;
	}

	public void setMonthlyPrice(String monthlyPrice) {
		MonthlyPrice = monthlyPrice;
	}

	public String getSecurityDeposit() {
		return SecurityDeposit;
	}

	public void setSecurityDeposit(String securityDeposit) {
		SecurityDeposit = securityDeposit;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public Location getLocation() {
		return Location;
	}

	public void setLocation(Location location) {
		Location = location;
	}
	
}
