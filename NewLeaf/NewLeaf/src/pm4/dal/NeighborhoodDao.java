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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pm4.model.Neighborhood;

public class NeighborhoodDao {
	private static NeighborhoodDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected NeighborhoodDao() {
		connectionManager = new ConnectionManager();
	}

	public static NeighborhoodDao getInstance() {
		if(instance == null) {
			instance = new NeighborhoodDao();
		}
		return instance;
	}
	
	public Neighborhood create(Neighborhood neighborhood) throws SQLException {
		String insertNeighborhood = "INSERT INTO Neighborhood(Name) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNeighborhood,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, neighborhood.getName());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int neighborhoodId = -1;
			if(resultKey.next()) {
				neighborhoodId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			neighborhood.setNeighborhoodKey(neighborhoodId);
			return neighborhood;
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
	
	public Neighborhood getNeighborhoodById(int neighborhoodId) throws SQLException {
		String selectNeighborhood = "SELECT NeighborhoodPK,Name FROM Neighborhood WHERE NeighborhoodPK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhood);
			selectStmt.setInt(1, neighborhoodId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int returnedNeighborhoodKey = results.getInt("NeighborhoodPK");
				String name = results.getString("Name");
				
				Neighborhood neighborhood = new Neighborhood(returnedNeighborhoodKey,name);
				return neighborhood;
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
	
	public List<Neighborhood> getNeighborhoods() throws SQLException {
		List<Neighborhood> neighborhoods = new ArrayList<Neighborhood>();
		String selectNeighborhood = "SELECT NeighborhoodPK,Name FROM Neighborhood;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhood);
			results = selectStmt.executeQuery();

			while(results.next()) {
				int neighborhoodKey = results.getInt("NeighborhoodPK");
				String returnedName = results.getString("Name");
				
				Neighborhood neighborhood = new Neighborhood(neighborhoodKey,returnedName);
				neighborhoods.add(neighborhood);
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
		return neighborhoods;
	}
	/*
	 * This is a small container class to store a neighborhood and its related count associated with it. 
	 */
	
	public class Pair {
		protected Neighborhood hood;
		protected int count;
		public Pair(Neighborhood hoodIn, int countIn) {
			this.hood = hoodIn;
			this.count = countIn;
		}
		public Neighborhood getNeighborhood() {
			return hood;
		}
		public int getCount() {
			return count;
		}
	}
	
	public List<Pair> getNeighborhoodsByReviewPhrase(String phrase) throws SQLException {
		List<Pair> neighborhoods = new ArrayList<Pair>();
		String selectNeighborhood = "SELECT N.NeighborhoodPK, N.Name AS Neighborhood, COUNT(ReviewPK) AS count\n" + 
				"FROM Reviews R\n" + 
				"JOIN AirBnB A\n" + 
				"ON R.AirBnB_AirPK = A.AirPK\n" + 
				"JOIN Location L\n" + 
				"ON A.LocationFK = L.LocationPK\n" + 
				"JOIN Neighborhood N\n" + 
				"ON L.NeighborhoodFK = N.NeighborhoodPK\n" + 
				"WHERE Comments LIKE ? \n" + 
				"GROUP BY Neighborhood\n" + 
				"ORDER BY count DESC;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhood);
			/*
			 * The phrase needs to be slightly adjusted. 
			 */
			String phraseAdjusted = "% " + phrase.strip() + " %";
			selectStmt.setString(1, phraseAdjusted);

			results = selectStmt.executeQuery();

			while(results.next()) {
				int neighborhoodKey = results.getInt("NeighborhoodPK");
				String returnedName = results.getString("Neighborhood");
				int count = results.getInt("count");
				
				Neighborhood neighborhood = new Neighborhood(neighborhoodKey,returnedName);
				neighborhoods.add(new Pair(neighborhood, count));
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
		return neighborhoods;
	}
	
	public Neighborhood getNeighborhoodByName(String name) throws SQLException {
		String selectNeighborhood = "SELECT NeighborhoodPK,Name FROM Neighborhood WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhood);
			selectStmt.setString(1, name);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				int neighborhoodKey = results.getInt("NeighborhoodPK");
				String returnedName = results.getString("Name");
				
				Neighborhood neighborhood = new Neighborhood(neighborhoodKey,returnedName);
				return neighborhood;
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
	
	
	public Neighborhood updateName(Neighborhood neighborhood, String updatedName) throws SQLException {
		String updateNeighborhood = "UPDATE Neighborhood SET Name=? WHERE NeighborhoodPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateNeighborhood);
			updateStmt.setString(1, updatedName);
			updateStmt.setInt(2, neighborhood.getNeighborhoodKey());
			updateStmt.execute();

			neighborhood.setName(updatedName);
			return neighborhood;
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
	
	public Neighborhood delete(Neighborhood neighborhood) throws SQLException {
		String deleteNeighborhood = "DELETE FROM Neighborhood WHERE NeighborhoodPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNeighborhood);
			deleteStmt.setInt(1, neighborhood.getNeighborhoodKey());;
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
