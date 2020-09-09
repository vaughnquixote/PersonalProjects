package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm4.model.BicycleShop;
import pm4.model.Neighborhood;

public class BicycleShopDao {
	private static BicycleShopDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected BicycleShopDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static BicycleShopDao getInstance() {
		if (instance == null) {
			instance = new BicycleShopDao();
		}
		return instance;
	}
	
	public BicycleShop create(BicycleShop shop) throws SQLException {
		String insertShop = "INSERT INTO BicycleShop(Name,Phone,Email,HasRental,Address,NeighborhoodFK) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertShop,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, shop.getName());
			insertStmt.setString(2, shop.getPhone());
			insertStmt.setString(3, shop.getEmail());
			insertStmt.setString(4, shop.getHasRental());
			insertStmt.setString(5, shop.getAddress());
			insertStmt.setInt(6, shop.getNeighborhood().getNeighborhoodKey());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int shopId = -1;
			if(resultKey.next()) {
				shopId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			shop.setBicycleShopPK(shopId);
			return shop;
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
	
	public BicycleShop getBicycleShopByAddress(String address) throws SQLException {
		String selectshop = "SELECT BicycleShopPK,Name,Phone,Email,HasRental,Address,NeighborhoodFK FROM BicycleShop "
				+ "WHERE Address=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectshop);
			selectStmt.setString(1, address);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int shopKey = results.getInt("BicycleShopPK");
				String name = results.getString("Name");
				String phone = results.getNString("Phone");
				String email = results.getString("Email");
				String hasRental = results.getString("HasRental");
				String returnedAddress = results.getString("Address");
				int neighborhoodKey = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodKey);
				
				BicycleShop shop = new BicycleShop(shopKey,name,phone,email,hasRental,returnedAddress,neighborhood);
				return shop;
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
	
	public BicycleShop getBicycleShopById(int shopId) throws SQLException {
		String selectshop = "SELECT BicycleShopPK,Name,Phone,Email,HasRental,Address,NeighborhoodFK FROM BicycleShop "
				+ "WHERE BicycleShopPK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectshop);
			selectStmt.setInt(1, shopId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int shopKey = results.getInt("BicycleShopPK");
				String name = results.getString("Name");
				String phone = results.getNString("Phone");
				String email = results.getString("Email");
				String hasRental = results.getString("HasRental");
				String returnedAddress = results.getString("Address");
				int neighborhoodKey = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodKey);
				
				BicycleShop shop = new BicycleShop(shopKey, name,phone,email,hasRental,returnedAddress,neighborhood);
				return shop;
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
	
	
	public BicycleShop updateHasRental(BicycleShop shop, String newHasRental) throws SQLException {
		String updateShop = "UPDATE BicycleShop SET HasRental=? WHERE BicycleShopPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateShop);
			updateStmt.setString(1, newHasRental);
			updateStmt.setInt(2, shop.getBicycleShopPK());
			updateStmt.execute();

			shop.setHasRental(newHasRental);
			return shop;
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
	
	public BicycleShop delete(BicycleShop shop) throws SQLException {
		String deleteShop = "DELETE FROM BicycleShop WHERE BicycleShopPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteShop);
			deleteStmt.setInt(1, shop.getBicycleShopPK());;
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