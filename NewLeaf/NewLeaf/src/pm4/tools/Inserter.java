/**
 * Nolan Bock
 * CS5200 - HW4
 */

package pm4.tools;

import java.sql.SQLException;
import java.util.List;

import pm4.dal.*;
import pm4.model.*;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		
		/*
		 * Neighborhood - Nolan
		 */
		System.out.print("\n\n Testing Neighborhood CRUD  \n");
		NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
		LocationDao locationDao = LocationDao.getInstance();
		
		// INSERT objects from our model.
		Neighborhood nolansFunTime = new Neighborhood("Nolan's fun time baby");
		neighborhoodDao.create(nolansFunTime);
		System.out.println(neighborhoodDao.getNeighborhoodById(141).getName());
		System.out.println(neighborhoodDao.getNeighborhoodByName("Nolan's fun time baby").getNeighborhoodKey());
		neighborhoodDao.updateName(nolansFunTime, "Nolan's earnest time");
		
		
		/*
		 * Location - Nolan
		 */
		System.out.print("\n\n Testing Location CRUD  \n");
		Location nolansSadLocation = new Location("983 Butt Street", "Toronto", "ON", "M8W 4W3", "43.00", "-79.00", nolansFunTime);
		locationDao.create(nolansSadLocation);
		System.out.println(locationDao.getLocationByAddress("983 Butt Street").getAddress());
		System.out.println(locationDao.getLocationByLatLong("43.6647074", "-79.4139395").getAddress());
		Neighborhood nolansMostFunTime = new Neighborhood("Nolan's BEST TIME! WOOOOOOOOO!");
		neighborhoodDao.create(nolansMostFunTime);
		locationDao.updateNeighborhoodKey(nolansSadLocation, nolansMostFunTime);
		System.out.println(nolansSadLocation.getNeighborhood().getName());
		
		neighborhoodDao.delete(nolansFunTime);
		locationDao.delete(nolansSadLocation);
		
		
		
		
		
		/*
		 *  TEST AirBNB CRUD - Brian
		 */
	System.out.print("\n\n Testing AirBnB CRUD  \n");

		AirBnBDao airDao = AirBnBDao.getInstance();
		//Location location1 = new Location("983 Butt Street", "Toronto", "ON", "M8W 4W3", "43.00", "-79.00", nolansFunTime);
		locationDao.create(nolansSadLocation);
		AirBnB listing1 = new AirBnB("summary1", "space1", "description1",
				 "PropertyType1", "RoomType",  "Bedrooms",  "Bathrooms",
				(float) 1250.00,  "Price",  "WeeklyPrice",  "MonthlyPrice",  "SecurityDeposit",
				 10, nolansSadLocation );
		
		// Create New listing.
		AirBnB listings1_U = airDao.create(listing1);
		// Try to get it by ID
		AirBnB retrieved = airDao.getAirbBnBById(listings1_U.getAirPK());
		System.out.format("Checking for listing... summary : %s , price : %s \n", retrieved.getSummary(), retrieved.getPrice());
		// Try updating price. 
		airDao.updatePrice(listings1_U, "price2");
		retrieved = airDao.getAirbBnBById(listings1_U.getAirPK());
		System.out.format("Checking for listing... summary : %s , price : %s \n", retrieved.getSummary(), retrieved.getPrice());
		// Now lets try deleting it. 
		airDao.delete(retrieved);
		retrieved = airDao.getAirbBnBById(listings1_U.getAirPK());
		if (retrieved == null) {
			System.out.print("listing deleleted succesfully \n");
		}
		
		
		/*
		 *  TEST Reviews for CRUD - Brian
		 */
		System.out.print("\n\n Testing Reviews CRUD  \n");

		ReviewsDao reviewDao = ReviewsDao.getInstance();
		AirBnB listings1_2 = airDao.create(listing1);
		Reviews review1 = new Reviews(listings1_2, java.sql.Date.valueOf("1997-03-10"), "comments1");
		reviewDao.create(review1);
		// check to see that it worked. 
		Reviews retrieved2 = reviewDao.getReviewByID(review1.getReviewPK());
		System.out.format("Checking for review... date : %s , comments : %s \n", retrieved2.getDate().toString(), retrieved2.getComments());
		//dope
		// lets try updating it now. 
		reviewDao.updateComments(review1, "New Comments");
		Reviews updated = reviewDao.getReviewByID(review1.getReviewPK());
		System.out.format("Checking for review... date : %s , comments : %s \n", updated.getDate().toString(), updated.getComments());
		
		reviewDao.delete(updated);
		Reviews deleted = reviewDao.getReviewByID(review1.getReviewPK());
		if (deleted == null) {
			System.out.print("review deleleted succesfully \n");
		}
		
		

		/*
		 *  TEST CalendarEntry for CRUD - Brian
		 */
		System.out.print("\n\n Testing CalendarEntry CRUD  \n");

		CalendarEntryDao calDao = CalendarEntryDao.getInstance();
		CalendarEntry calEntry = new CalendarEntry("1", "$1.50", java.sql.Date.valueOf("1997-03-10"), listings1_2);
		calDao.create(calEntry);
		// check that it worked
		CalendarEntry retreived3 = calDao.GetCalendarEntryByID(calEntry.getCalendarEntryPK());
		System.out.format("Checking for calendar Entry... date : %s , price : %s \n", retreived3.getDate().toString(), retreived3.getPrice());
		// check update:
		calDao.updatePrice(calEntry, "new price.. its alot");
		CalendarEntry retreived4 = calDao.GetCalendarEntryByID(calEntry.getCalendarEntryPK());
		System.out.format("Checking for calendar Entry... date : %s , price : %s \n", retreived4.getDate().toString(), retreived4.getPrice());
		// Check Delete
		calDao.delete(retreived4);
		CalendarEntry deleted2 = calDao.GetCalendarEntryByID(calEntry.getCalendarEntryPK());
		if (deleted2 == null) {
			System.out.print("calendarEntry deleleted succesfully \n");
		}
		
		
		/*
		 * Crime tests - Nolan
		 */
		System.out.print("\n\n Testing Crime CRUD  \n");
		CrimeDao crimeDao = CrimeDao.getInstance();
		Crime crime = new Crime("House", "Assault", "2020", "43.77", "-79.41", nolansMostFunTime);
		crimeDao.create(crime);
		System.out.println("Expected: Nolan's BEST TIME! WOOOOOOOOO!" + "     Actual: " + crimeDao.getCrimeById(262141).getNeighborhood().getName());
		crimeDao.updateOffense(crime, "Assault With Weapon");
		System.out.println("Expected: Assault With Weapon" + "     Actual: " + crimeDao.getCrimeById(262141).getOffense());
		
		Crime crimeDelete = new Crime("House", "Assault", "2020", "43.77", "-79.41", nolansMostFunTime);
		System.out.println("Expected: Nolan's BEST TIME! WOOOOOOOOO! " + "     Acutal: " + crimeDelete.getNeighborhood().getName());
		crimeDao.delete(crimeDelete);
		Crime back = crimeDao.getCrimeById(crimeDelete.getCrimeKey());
		System.out.println("Expected: null    Actual: " + back);
		
		List<Crime> crimes = crimeDao.getCrimesByLatLong("43.7057724", "-79.2783966");
		int count = 0;
		for (Crime c : crimes) {
			count += 1;
		}
		System.out.println("Number of crimes by lat,long " + count);
		
		crimes = crimeDao.getCrimesByNeighborhoodName("Annex");
		count = 0;
		for (Crime c : crimes) {
			count += 1;
		}
		System.out.println("Number of crimes by name " + count);
	}
}
