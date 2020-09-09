package pm4.model;

public class Reviews {
	
	int ReviewPK;
	AirBnB Listing;
	java.sql.Date Date;
	String Comments;
	
	public Reviews(int ReviewPK, AirBnB Listing, java.sql.Date Date, String Comments) {
		
		this.ReviewPK = ReviewPK;
		this.Listing = Listing;
		this.Date = Date;
		this.Comments = Comments;
	}
	
	public Reviews(AirBnB Listing, java.sql.Date Date, String Comments) {
		
		this.Listing = Listing;
		this.Date = Date;
		this.Comments = Comments;
	}

	public int getReviewPK() {
		return ReviewPK;
	}

	public void setReviewPK(int reviewPK) {
		ReviewPK = reviewPK;
	}

	public AirBnB getListing() {
		return Listing;
	}

	public void setListing(AirBnB listing) {
		Listing = listing;
	}

	public java.sql.Date getDate() {
		return Date;
	}

	public void setDate(java.sql.Date date) {
		Date = date;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}
	
	
}
