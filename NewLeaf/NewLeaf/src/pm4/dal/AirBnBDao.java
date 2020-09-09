package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm4.model.AirBnB;
import pm4.model.Business;
import pm4.model.Location;

public class AirBnBDao {
	private static AirBnBDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected AirBnBDao() {
		connectionManager = new ConnectionManager();
	}

	public static AirBnBDao getInstance() {
		if(instance == null) {
			instance = new AirBnBDao();
		}
		return instance;
	}
	
	public AirBnB create(AirBnB listing) throws SQLException {
		String insertListing= "INSERT INTO AirBnB(Summary,Space,Description,PropertyType,RoomType,Bedrooms,Bathrooms, SquareFeet, Price, WeeklyPrice,  MonthlyPrice, SecurityDeposit,  Rating, LocationFK) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertListing,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, listing.getSummary());
			insertStmt.setString(2, listing.getSpace());
			insertStmt.setString(3, listing.getDescription());
			insertStmt.setString(4, listing.getPropertyType());
			insertStmt.setString(5, listing.getRoomType());
			insertStmt.setString(6, listing.getBedrooms());
			insertStmt.setString(7, listing.getBathrooms());
			insertStmt.setFloat(8, listing.getSquareFeet());
			insertStmt.setString(9, listing.getPrice());
			insertStmt.setString(10, listing.getWeeklyPrice());
			insertStmt.setString(11, listing.getMonthlyPrice());
			insertStmt.setString(12, listing.getSecurityDeposit());
			insertStmt.setInt(13, listing.getRating());
			insertStmt.setInt(14, listing.getLocation().getLocationKey());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int AirPK = -1;
			if(resultKey.next()) {
				AirPK = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			listing.setAirPK(AirPK);
			return listing;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public AirBnB getAirbBnBById(int id) throws SQLException {
		String selectListing = "SELECT AirPK, Summary,Space,Description,PropertyType,RoomType,Bedrooms,Bathrooms, SquareFeet, Price, WeeklyPrice,  MonthlyPrice, SecurityDeposit,  Rating, LocationFK FROM AirBnB "
				+ "WHERE AirPK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListing);
			selectStmt.setInt(1, id );
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int AirPK = results.getInt("AirPK");
				String Summary = results.getString("Summary");
				String Space = results.getString("Space");
				String Description = results.getString("Description");
				String PropertyType = results.getString("PropertyType");
				String RoomType = results.getString("RoomType");
				String Bedrooms = results.getString("Bedrooms");
				String Bathrooms = results.getString("Bathrooms");

				Float SquareFeet = results.getFloat("SquareFeet");
				String Price = results.getString("Price");
				String WeeklyPrice = results.getString("WeeklyPrice");
				String MonthlyPrice = results.getString("MonthlyPrice");
				String SecurityDeposit = results.getString("SecurityDeposit");
				int Rating = results.getInt("Rating");
				int LocationFK = results.getInt("LocationFK");

				Location location = LocationDao.getInstance().getLocationByID(LocationFK);
				AirBnB listing = new AirBnB(AirPK,Summary,Space,Description,PropertyType,RoomType,
						Bedrooms, Bathrooms,SquareFeet, Price, WeeklyPrice , MonthlyPrice , SecurityDeposit, Rating, location );
				return listing;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public Double getAvgAirBnBPriceByNeighborhood(String neighborhood) throws SQLException{
		String selectAirs = "SELECT N.Name as Name, AVG(CAST(REPLACE(TRIM('$' from A.Price), ',','') as float)) as AvgPrice"
				+ " FROM Neighborhood as N INNER JOIN Location as L ON N.NeighborhoodPK = L.NeighborhoodFK"
				+ " INNER JOIN AirBnB as A ON A.LocationFK = L.LocationPK"
				+ " WHERE N.Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAirs);
			selectStmt.setString(1, neighborhood);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Double averagePrice = results.getDouble("AvgPrice");
				return averagePrice;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return 0.0;
		
	}
	
	public Double getAvgAirBnBPriceByLocation(Double lat, Double longitude, Double radius) throws SQLException{
		String selectAirs = "SELECT AVG(CAST(REPLACE(TRIM('$' from t.Price), ',','') as float)) as AvgPrice FROM(\n" + 
				"    SELECT *, (((acos(sin((?*pi()/180)) \n" + 
				"    * sin((Latitude*pi()/180))+cos((?*pi()/180)) \n" + 
				"    * cos((Latitude*pi()/180)) \n" + 
				"    * cos(((? - Longitude)*pi()/180))))*180/pi())*60*1.1515*1.609344) \n" + 
				"    as distance FROM Location INNER JOIN AirBnB ON Location.LocationPK = AirBnB.LocationFK) as t\n" + 
				"WHERE t.distance <= ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAirs);
			selectStmt.setDouble(1, lat);
			selectStmt.setDouble(2, lat);
			selectStmt.setDouble(3, longitude);
			selectStmt.setDouble(4, radius);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Double averagePrice = results.getDouble("AvgPrice");
				return averagePrice;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return 0.0;
		
	}
	
	public List<AirBnB> getTop20ByRating(String neighborhood) throws SQLException{
		List<AirBnB> airs = new ArrayList<AirBnB>();
		String selectAirs = "SELECT AirPK,Summary,Space,Description,PropertyType,RoomType,Bedrooms,Bathrooms, SquareFeet, Price, WeeklyPrice,  MonthlyPrice, SecurityDeposit,Rating,LocationFK"
				+ " FROM AirBnB as a INNER JOIN Location as l on a.LocationFK=l.LocationPK INNER JOIN Neighborhood as n ON l.NeighborhoodFK=n.NeighborhoodPK"
				+ " WHERE Rating>0 and n.Name=?"
				+ " ORDER BY Rating DESC"
				+ " LIMIT 20;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAirs);
			selectStmt.setString(1, neighborhood);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int AirPK = results.getInt("AirPK");
				String Summary = results.getString("Summary");
				String Space = results.getString("Space");
				String Description = results.getString("Description");
				String PropertyType = results.getString("PropertyType");
				String RoomType = results.getString("RoomType");
				String Bedrooms = results.getString("Bedrooms");
				String Bathrooms = results.getString("Bathrooms");

				Float SquareFeet = results.getFloat("SquareFeet");
				String Price = results.getString("Price");
				String WeeklyPrice = results.getString("WeeklyPrice");
				String MonthlyPrice = results.getString("MonthlyPrice");
				String SecurityDeposit = results.getString("SecurityDeposit");
				int Rating = results.getInt("Rating");
				int LocationFK = results.getInt("LocationFK");

				Location location = LocationDao.getInstance().getLocationByID(LocationFK);
				AirBnB listing = new AirBnB(AirPK,Summary,Space,Description,PropertyType,RoomType,
						Bedrooms, Bathrooms,SquareFeet, Price, WeeklyPrice , MonthlyPrice , SecurityDeposit, Rating, location );
				airs.add(listing);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return airs;
	}
	
	public AirBnB updatePrice(AirBnB listing, String newPrice) throws SQLException {
		String updatePrice = "UPDATE AirBnB SET Price=? WHERE AirPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePrice);
			updateStmt.setString(1, newPrice);
			updateStmt.setInt(2, listing.getAirPK());
			updateStmt.execute();

			listing.setPrice(newPrice);
			return listing;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	
	public AirBnB delete(AirBnB listing) throws SQLException {
		String deleteListing = "DELETE FROM AirBnB WHERE AirPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteListing);
			deleteStmt.setInt(1, listing.getAirPK());;
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}

