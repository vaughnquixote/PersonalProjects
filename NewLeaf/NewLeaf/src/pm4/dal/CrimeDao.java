package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pm4.model.Business;
import pm4.model.Crime;
import pm4.model.Neighborhood;

public class CrimeDao {
	private static CrimeDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected CrimeDao() {
		connectionManager = new ConnectionManager();
	}

	public static CrimeDao getInstance() {
		if(instance == null) {
			instance = new CrimeDao();
		}
		return instance;
	}
	
	public Crime create(Crime crime) throws SQLException {
		String insertCrime = "INSERT INTO Crime(PremiseType,Offense,Year,Latitude,Longitude,NeighborhoodFK) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCrime,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, crime.getPremiseType());
			insertStmt.setString(2, crime.getOffense());
			insertStmt.setString(3, crime.getYear());
			insertStmt.setString(4, crime.getLatitude());
			insertStmt.setString(5, crime.getLongitude());
			insertStmt.setInt(6, crime.getNeighborhood().getNeighborhoodKey());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int crimeId = -1;
			if(resultKey.next()) {
				crimeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			crime.setCrimeKey(crimeId);
			return crime;
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
	
	public Crime getCrimeById(int crimeId) throws SQLException {
		String selectCrime = "SELECT CrimePK,PremiseType,Offense,Year,Latitude,Longitude,NeighborhoodFK FROM Crime WHERE CrimePK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCrime);
			selectStmt.setInt(1, crimeId);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				int returnedCrimeKey = results.getInt("CrimePK");
				String premiseType = results.getString("PremiseType");
				String offense = results.getString("Offense");
				String year = results.getString("Year");
				String latitude = results.getString("Latitude");
				String longitude = results.getString("Longitude");
				int neighborhoodKey = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodKey);
				
				
				
				Crime crime = new Crime(returnedCrimeKey,premiseType,offense,year,latitude,longitude,neighborhood);
				return crime;
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
	
	public List<Crime> getCrimesByLatLong(String latitude, String longitude) throws SQLException {
		String selectCrime = "SELECT CrimePK,PremiseType,Offense,Year,Latitude,Longitude,NeighborhoodFK FROM Crime "
				+ "WHERE Latitude=? AND Longitude=?;";
		List<Crime> crimes = new ArrayList<Crime>();
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCrime);
			selectStmt.setString(1, latitude);
			selectStmt.setString(2, longitude);
			
			results = selectStmt.executeQuery();

			while(results.next()) {
				int crimeKey = results.getInt("CrimePK");
				String premiseType = results.getString("PremiseType");
				String offense = results.getString("Offense");
				String year = results.getString("Year");
				String returnedLatitude = results.getString("Latitude");
				String returnedLongitude = results.getString("Longitude");
				int neighborhoodKey = results.getInt("NeighborhoodFK");
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodById(neighborhoodKey);
				
				
				
				Crime crime = new Crime(crimeKey,premiseType,offense,year,returnedLatitude,returnedLongitude,neighborhood);
				crimes.add(crime);
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
		return crimes;
	}
	
	public List<Crime> getCrimesByNeighborhoodName(String name) throws SQLException {
		String selectCrime = "SELECT CrimePK,PremiseType,Offense,Year,Latitude,Longitude,NeighborhoodFK FROM Crime "
				+ "WHERE NeighborhoodFK=?";
		List<Crime> crimes = new ArrayList<Crime>();
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCrime);
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByName(name);
			selectStmt.setInt(1, neighborhood.getNeighborhoodKey());
			
			results = selectStmt.executeQuery();

			while(results.next()) {
				int crimeKey = results.getInt("CrimePK");
				String premiseType = results.getString("PremiseType");
				String offense = results.getString("Offense");
				String year = results.getString("Year");
				String latitude = results.getString("Latitude");
				String longitude = results.getString("Longitude");
				
				Crime crime = new Crime(crimeKey,premiseType,offense,year,latitude,longitude,neighborhood);
				crimes.add(crime);
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
		return crimes;
	}
	
	public Crime updateOffense(Crime crime, String updatedOffense) throws SQLException {
		String updateCrime = "UPDATE Crime SET Offense=? WHERE CrimePK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCrime);
			updateStmt.setString(1, updatedOffense);
			updateStmt.setInt(2, crime.getCrimeKey());
			updateStmt.execute();

			crime.setOffense(updatedOffense);
			return crime;
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
	
	public Crime delete(Crime crime) throws SQLException {
		String deleteNeighborhood = "DELETE FROM Crime WHERE CrimePK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNeighborhood);
			deleteStmt.setInt(1, crime.getCrimeKey());;
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
