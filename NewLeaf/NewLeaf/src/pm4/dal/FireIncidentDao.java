package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm4.model.FireIncident;
import pm4.model.Neighborhood;

public class FireIncidentDao {
	private static FireIncidentDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected FireIncidentDao getInstance() {
		if(instance == null) {
			instance = new FireIncidentDao();
		}
		return instance;
	}
	
	public FireIncidentDao() {
		connectionManager = new ConnectionManager(); 
	}
	
	public FireIncident create(FireIncident fire) throws SQLException {
		String insertListing = "INSERT INTO FireIncident(Neighborhood_NeighborhoodPK) "
				+ "VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertListing, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, fire.getNeighborhood().getNeighborhoodKey());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int fireKey = -1;
			if(resultKey.next()) {
				fireKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key");
			}
			fire.setFireIncidentPK(fireKey);
			return fire;
		} catch(SQLException e) {
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
	
	public FireIncident getFireIncidentById(int id) throws SQLException {
		String selectFire = "SELECT FireIncidentPK,Neighborhood_NeighborhoodPK "
				+ "FROM FireIncident "
				+ "WHERE FireIncidentPK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFire);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int firePk = results.getInt("FireIncidentPK");
				int neighborhoodFk = results.getInt("Neighborhood_NeighborhoodPK");
				Neighborhood neighborhood = NeighborhoodDao.getInstance().getNeighborhoodById(neighborhoodFk);
				FireIncident fire = new FireIncident(firePk, neighborhood);
				return fire;
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
	
	public FireIncident updateNeighborhoodFK(FireIncident fire, int newFK) throws SQLException {
		String updateFk = "UPDATE FireIncident SET Neighborhood_NeighborhoodPK=? WHERE FireIncidentPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFk);
			updateStmt.setInt(1, newFK);
			updateStmt.setInt(1, fire.getFireIncidentPK());
			updateStmt.executeUpdate();
			Neighborhood neighborhood = NeighborhoodDao.getInstance().getNeighborhoodById(newFK);
			fire.setNeighborhood(neighborhood);
			return fire;
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
	
	public FireIncident delete(FireIncident fire) throws SQLException {
		String deleteFire = "DELETE FROM FireIncident WHERE FireIncidentPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFire);
			deleteStmt.setInt(1, fire.getFireIncidentPK());
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
