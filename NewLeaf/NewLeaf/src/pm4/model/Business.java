package pm4.model;

public class Business {
	protected int parksPK;
	protected String name;
	protected double stars;
	protected int reviewCount;
	protected String attributes;
	protected String categories;
	protected String hours;
	protected Location location;
	public Business(int parksPK, String name, double stars, int reviewCount, String attributes, String categories,
			String hours, Location location) {
		super();
		this.parksPK = parksPK;
		this.name = name;
		this.stars = stars;
		this.reviewCount = reviewCount;
		this.attributes = attributes;
		this.categories = categories;
		this.hours = hours;
		this.location = location;
	}
	
	public Business( String name, double stars, int reviewCount, String attributes, String categories,
			String hours, Location location) {
		this.name = name;
		this.stars = stars;
		this.reviewCount = reviewCount;
		this.attributes = attributes;
		this.categories = categories;
		this.hours = hours;
		this.location = location;
	}

	public int getParksPK() {
		return parksPK;
	}

	public void setParksPK(int parksPK) {
		this.parksPK = parksPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getStars() {
		return stars;
	}

	public void setStars(double stars) {
		this.stars = stars;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
