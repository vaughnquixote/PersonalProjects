/**
 * Nolan Bock
 * CS5200 - HW4
 */

package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm4.model.Location;
import pm4.model.Neighborhood;

public class LocationDao {
	private static LocationDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected LocationDao() {
		connectionManager = new ConnectionManager();
	}

	public static LocationDao getInstance() {
		if(instance == null) {
			instance = new LocationDao();
		}
		return instance;
	}
	
	public Location create(Location location) throws SQLException {
		String insertNeighborhood = "INSERT INTO Location(Address,City,State,Zipcode,Latitude,Longitude,NeighborhoodFK) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNeighborhood,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, location.getAddress());
			insertStmt.setString(2, location.getCity());
			insertStmt.setString(3, location.getState());
			insertStmt.setString(4, location.getZipCode());
			insertStmt.setString(5, location.getLatitidue());
			insertStmt.setString(6, location.getLongitude());
			insertStmt.setInt(7, location.getNeighborhood().getNeighborhoodKey());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int locationId = -1;
			if(resultKey.next()) {
				locationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			location.setLocationKey(locationId);
			return location;
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

	public Location getLocationByID(int id) throws SQLException {
		String selectLocation = "SELECT LocationPK,Address,City,State,Zipcode,Latitude,Longitude,NeighborhoodFK FROM Location "
				+ "WHERE LocationPK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setInt(1, id);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int locationKey = results.getInt("LocationPK");
				String returnedAddress = results.getString("Address");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipCode = results.getString("Zipcode");
				String latitude = results.getString("Latitude");
				String longitude = results.getString("Longitude");
				int neighborhoodPK = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodPK);
				
				Location location = new Location(locationKey,returnedAddress,city,state,zipCode,latitude,longitude,neighborhood);
				return location;
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
	
	public Location getLocationByAddress(String address) throws SQLException {
		String selectLocation = "SELECT LocationPK,Address,City,State,Zipcode,Latitude,Longitude,NeighborhoodFK FROM Location "
				+ "WHERE Address=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setString(1, address);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int locationKey = results.getInt("LocationPK");
				String returnedAddress = results.getString("Address");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipCode = results.getString("Zipcode");
				String latitude = results.getString("Latitude");
				String longitude = results.getString("Longitude");
				int neighborhoodPK = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodPK);
				
				Location location = new Location(locationKey,returnedAddress,city,state,zipCode,latitude,longitude,neighborhood);
				return location;
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
	
	public Location getLocationByLatLong(String latitude, String longitude) throws SQLException {
		String selectLocation = "SELECT LocationPK,Address,City,State,Zipcode,Latitude,Longitude,NeighborhoodFK FROM Location "
				+ "WHERE Latitude=? AND Longitude=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setString(1,latitude);
			selectStmt.setString(2,longitude);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int locationKey = results.getInt("LocationPK");
				String address = results.getString("Address");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipCode = results.getString("Zipcode");
				String returnedLatitude = results.getString("Latitude");
				String returnedLongitude = results.getString("Longitude");
				int neighborhoodPK = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodPK);
				
				Location location = new Location(locationKey,address,city,state,zipCode,returnedLatitude,returnedLongitude,neighborhood);
				return location;
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
	
	
	public Location updateNeighborhoodKey(Location location, Neighborhood newNeighborhood) throws SQLException {
		String updateLocation = "UPDATE Location SET NeighborhoodFK=? WHERE LocationPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLocation);
			updateStmt.setInt(1, newNeighborhood.getNeighborhoodKey());
			updateStmt.setInt(2, location.getLocationKey());
			updateStmt.execute();

			location.setNeighborhood(newNeighborhood);
			return location;
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
	
	public Location updateAddress(Location location, String newAddress) throws SQLException {
		String updateLocation = "UPDATE Location SET Address=? WHERE LocationPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLocation);
			updateStmt.setString(1, newAddress);
			updateStmt.setInt(2, location.getLocationKey());
			updateStmt.execute();

			location.setAddress(newAddress);
			return location;
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
	
	public Location delete(Location location) throws SQLException {
		String deleteLocation = "DELETE FROM Location WHERE LocationPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLocation);
			deleteStmt.setInt(1, location.getLocationKey());;
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
