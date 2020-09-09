package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm4.model.BicycleParking;
import pm4.model.BicycleShop;
import pm4.model.Crime;
import pm4.model.Neighborhood;

public class BicycleParkingDao {
	private static BicycleParkingDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected BicycleParkingDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static BicycleParkingDao getInstance() {
		if (instance == null) {
			instance = new BicycleParkingDao();
		}
		return instance;
	}
	
	public BicycleParking create(BicycleParking park) throws SQLException {
		String insertPark = "INSERT INTO BicycleParking(AssetType,IsActive,Address,NeighborhoodFK) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPark,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, park.getAssetType());
			insertStmt.setString(2, park.getIsActive());
			insertStmt.setString(3, park.getAddress());
			insertStmt.setInt(4, park.getNeighborhood().getNeighborhoodKey());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int parkId = -1;
			if(resultKey.next()) {
				parkId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			park.setBicycleParkingPK(parkId);
			return park;
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
	
	public BicycleParking getBicycleParkingByAddress(String address) throws SQLException {
		String selectpark = "SELECT BicycleParkingPK,AssetType,IsActive,Address,NeighborhoodFK FROM BicycleParking "
				+ "WHERE Address=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectpark);
			selectStmt.setString(1, address);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int parkKey = results.getInt("BicycleParkingPK");
				String assetType = results.getString("AssetType");
				String isActive = results.getNString("IsActive");
				String ResultAddress = results.getString("Address");
				int neighborhoodKey = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodKey);
				
				BicycleParking shop = new BicycleParking(parkKey,assetType,isActive,ResultAddress,neighborhood);
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
	public List<BicycleParking> getBicycleParkingByNeighborhood(String NeighborhoodIN) throws SQLException {
		 
		List<BicycleParking> spots = new ArrayList<BicycleParking>();
		 
		String selectpark = "SELECT BicycleParkingPK,AssetType,IsActive,Address,NeighborhoodFK\n" + 
				"FROM BicycleParking BP \n" + 
				"LEFT JOIN Neighborhood N\n" + 
				"ON BP.NeighborhoodFK = N.NeighborhoodPK\n" + 
				"WHERE  N.Name = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectpark);
			selectStmt.setString(1, NeighborhoodIN);
			
			results = selectStmt.executeQuery();
			
			while(results.next()) {

				int parkKey = results.getInt("BicycleParkingPK");
				String assetType = results.getString("AssetType");
				String isActive = results.getNString("IsActive");
				String ResultAddress = results.getString("Address");
				int neighborhoodKey = results.getInt("NeighborhoodFK");
				
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodKey);
				BicycleParking shop = new BicycleParking(parkKey,assetType,isActive,ResultAddress,neighborhood);
				spots.add(shop);
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
		return spots;
	}
	
	public BicycleParking getBicycleParkingById(int parkId) throws SQLException {
		String selectpark = "SELECT BicycleParkingPK,AssetType,IsActive,Address,NeighborhoodFK FROM BicycleParking "
				+ "WHERE BicycleParkingPK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectpark);
			selectStmt.setInt(1, parkId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int parkKey = results.getInt("BicycleParkingPK");
				String assetType = results.getString("AssetType");
				String isActive = results.getNString("IsActive");
				String ResultAddress = results.getString("Address");
				int neighborhoodKey = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodKey);
				
				BicycleParking shop = new BicycleParking(parkKey,assetType,isActive,ResultAddress,neighborhood);
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
	
	
	
	public BicycleParking updateIsActive(BicycleParking park, String newIsActive) throws SQLException {
		String updateShop = "UPDATE BicycleParking SET IsActive=? WHERE BicycleParkingPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateShop);
			updateStmt.setString(1, newIsActive);
			updateStmt.setInt(2, park.getBicycleParkingPK());
			updateStmt.execute();

			park.setIsActive(newIsActive);
			return park;
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
	
	public BicycleParking delete(BicycleParking park) throws SQLException {
		String deletePark = "DELETE FROM BicycleParking WHERE BicycleParkingPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePark);
			deleteStmt.setInt(1, park.getBicycleParkingPK());;
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