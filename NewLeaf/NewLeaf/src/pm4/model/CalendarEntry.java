package pm4.model;

public class CalendarEntry {
	
	int CalendarEntryPK;
	String IsAvailable;
	String Price;
	java.sql.Date Date;
	AirBnB Listing; 
	
	
	public CalendarEntry(int CalendarEntryPK, String IsAvailable, String Price,
	java.sql.Date Date, AirBnB Listing) {
		
		this.CalendarEntryPK = CalendarEntryPK;
		this.IsAvailable = IsAvailable;
		this.Price = Price;
		this.Date = Date;
		this.Listing = Listing;

	}
	
	public CalendarEntry (String IsAvailable, String Price,
	java.sql.Date Date, AirBnB Listing) {
		
		this.IsAvailable = IsAvailable;
		this.Price = Price;
		this.Date = Date;
		this.Listing = Listing;

	}


	public int getCalendarEntryPK() {
		return CalendarEntryPK;
	}


	public void setCalendarEntryPK(int calendarEntryPK) {
		CalendarEntryPK = calendarEntryPK;
	}


	public String getIsAvailable() {
		return IsAvailable;
	}


	public void setIsAvailable(String isAvailable) {
		IsAvailable = isAvailable;
	}


	public String getPrice() {
		return Price;
	}


	public void setPrice(String price) {
		Price = price;
	}


	public java.sql.Date getDate() {
		return Date;
	}


	public void setDate(java.sql.Date date) {
		Date = date;
	}


	public AirBnB getListing() {
		return Listing;
	}


	public void setListing(AirBnB listing) {
		Listing = listing;
	}
	
	
}
