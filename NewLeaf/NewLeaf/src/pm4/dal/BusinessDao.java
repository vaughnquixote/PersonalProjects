package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm4.model.Location;

import pm4.model.Business;

public class BusinessDao {
	private static BusinessDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected BusinessDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static BusinessDao getInstance() {
		if(instance == null) {
			instance = new BusinessDao();
		}
		return instance;
	}
	
	public Business create(Business biz) throws SQLException {
		String insertBiz = "INSERT INTO Business(Name,Stars,ReviewCount,Attributes,Categories,Hours,LocationFK)"
				+ " VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBiz,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, biz.getName());
			insertStmt.setDouble(2, biz.getStars());
			insertStmt.setInt(3, biz.getReviewCount());
			insertStmt.setString(4, biz.getAttributes());
			insertStmt.setString(5, biz.getCategories());
			insertStmt.setString(6, biz.getHours());
			insertStmt.setInt(7, biz.getLocation().getLocationKey());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int bizKey = -1;
			if(resultKey.next()) {
				bizKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key");
			}
			biz.setParksPK(bizKey);
			return biz;
		} catch(SQLException e) {
			e.printStackTrace();
			throw(e);
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}



	public Business getBusinessById(int id) throws SQLException {
		String selectBiz = "SELECT ParksPK,Name,Stars,ReviewCount,Attributes,Categories,Hours,LocationFK FROM Business "
				+ "WHERE ParksPK = ? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBiz);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int bizPk = results.getInt("ParksPK");
				String name = results.getString("Name");
				double stars = results.getDouble("Stars");
				int reviewCount = results.getInt("ReviewCount");
				String attributes = results.getString("Attributes");
				String categories = results.getString("Categories");
				String hours = results.getString("Hours");
				int locationFK = results.getInt("LocationFK");
				Location location = LocationDao.getInstance().getLocationByID(locationFK);
				Business biz = new Business(bizPk,name,stars,reviewCount,attributes,categories,hours,location);
				return biz;
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
	
	public List<Business> getBusinessForRating(double rating) throws SQLException {
		List<Business> businesses = new ArrayList<Business>();
		String selectBusinesses = "SELECT ParksPK, Name, Stars, ReviewCount, Attributes, Categories, Hours, LocationFK"
				+ " FROM Business"
				+ " WHERE Stars>=? ORDER BY Stars LIMIT 20;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBusinesses);
			selectStmt.setDouble(1, rating);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int parksPk = results.getInt("ParksPK");
				String name = results.getString("Name");
				Double stars = results.getDouble("Stars");
				int reviewCount = results.getInt("ReviewCount");
				String attributes = results.getString("Attributes");
				String categories = results.getString("Categories");
				String hours = results.getString("Hours");
				int locationFk = results.getInt("LocationFK");
				Location loc = LocationDao.getInstance().getLocationByID(locationFk);
				Business biz = new Business(parksPk, name, stars, reviewCount, attributes, categories, hours, loc);
				businesses.add(biz);
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
		return businesses;
	}	
	
	public List<Business> getBusinessForNeighborhood(String neighborhood, double stars, int reviewCount) throws SQLException{
		List<Business> businesses = new ArrayList<Business>();
		String selectBusinesses = "SELECT ParksPK, b.Name, Stars, ReviewCount, Attributes, Categories, Hours, LocationFK"
				+ " FROM Business as b INNER JOIN Location as l on b.LocationFK = l.LocationPK"  
				+ " INNER JOIN Neighborhood as n on l.NeighborhoodFK=n.NeighborhoodPK "
				+ " WHERE Stars>=? and ReviewCount>=? and n.Name=? ORDER BY Stars DESC LIMIT 20;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBusinesses);
			selectStmt.setDouble(1, stars);
			selectStmt.setInt(2, reviewCount);
			selectStmt.setString(3, neighborhood);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int parksPk = results.getInt("ParksPK");
				String name = results.getString("Name");
				stars = results.getDouble("Stars");
				reviewCount = results.getInt("ReviewCount");
				String attributes = results.getString("Attributes");
				String categories = results.getString("Categories");
				String hours = results.getString("Hours");
				int locationFk = results.getInt("LocationFK");
				Location loc = LocationDao.getInstance().getLocationByID(locationFk);
				Business biz = new Business(parksPk, name, stars, reviewCount, attributes, categories, hours, loc);
				businesses.add(biz);
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
		return businesses;
	}
	
	
	public List<Business> getBusinessByCategories(String category) throws SQLException {
		List<Business> businesses = new ArrayList<Business>();
		String selectBusinesses = "SELECT ParksPK,Name,Stars,ReviewCount,Attributes,Categories,Hours,LocationFK FROM Business "
				+ "WHERE Categories LIKE ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBusinesses);
			category = "%" + category + "%";
			selectStmt.setString(1, category);
			results = selectStmt.executeQuery();
			LocationDao locationDao = LocationDao.getInstance();
			while(results.next()) {
				int parksPk = results.getInt("ParksPK");
				String name = results.getString("Name");
				Double stars = results.getDouble("Stars");
				int reviewCount = results.getInt("ReviewCount");
				String attributes = results.getString("Attributes");
				String returnedCategories = results.getString("Categories");
				String hours = results.getString("Hours");
				int locationFk = results.getInt("LocationFK");
				Location loc = locationDao.getLocationByID(locationFk);
				Business biz = new Business(parksPk, name, stars, reviewCount, attributes, returnedCategories, hours, loc);
				businesses.add(biz);
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
		return businesses;
	}	

	
	public Business updateHours(Business biz, String newHours) throws SQLException {
		String updateHours = "UPDATE Business SET Hours=? WHERE ParksPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHours);
			updateStmt.setString(1, newHours);
			updateStmt.setInt(2, biz.getParksPK());
			updateStmt.executeUpdate();
			biz.setHours(newHours);
			return biz;
		} catch(SQLException e) {
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
	
	public Business delete(Business biz) throws SQLException {
		String deleteBiz = "DELETE FROM Business WHERE ParksPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBiz);
			deleteStmt.setInt(1, biz.getParksPK());
			deleteStmt.executeUpdate();
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
