package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm4.model.AirBnB;
import pm4.model.CalendarEntry;

public class CalendarEntryDao {
	private static CalendarEntryDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected CalendarEntryDao() {
		connectionManager = new ConnectionManager();
	}

	public static CalendarEntryDao getInstance() {
		if(instance == null) {
			instance = new CalendarEntryDao();
		}
		return instance;
	}
	
	public CalendarEntry create(CalendarEntry entry) throws SQLException {
		String insertentry= "INSERT INTO CalendarEntry(IsAvailable,Price,Date,AirBNBFK) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertentry,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, entry.getIsAvailable());
			insertStmt.setString(2, entry.getPrice());
			insertStmt.setString(3, entry.getDate().toString());
			insertStmt.setInt(4, entry.getListing().getAirPK());
			

			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int PK = -1;
			if(resultKey.next()) {
				PK = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			entry.setCalendarEntryPK(PK);
			return entry;
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
	
	public CalendarEntry GetCalendarEntryByID(int id) throws SQLException {
		String selectListing = "SELECT CalendarEntryPK, IsAvailable,Price,Date, AirBNBFK FROM CalendarEntry "
				+ "WHERE CalendarEntryPK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListing);
			selectStmt.setInt(1, id );
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int CalendarEntryPK = results.getInt("CalendarEntryPK");
				String IsAvailable = results.getString("IsAvailable");
				String Price = results.getString("Price");
				java.sql.Date Date = results.getDate("Date");
				int AirBNBFK = results.getInt("AirBNBFK");
				
				AirBnB listing = AirBnBDao.getInstance().getAirbBnBById(AirBNBFK);
				CalendarEntry entry = new CalendarEntry(CalendarEntryPK, IsAvailable, Price, Date, listing);
				return entry;
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
	
	
	
	
	public CalendarEntry updatePrice(CalendarEntry entry, String newPrice) throws SQLException {
		String updatePrice = "UPDATE CalendarEntry SET Price=? WHERE CalendarEntryPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePrice);
			updateStmt.setString(1, newPrice);
			updateStmt.setInt(2, entry.getCalendarEntryPK());
			updateStmt.execute();

			entry.setPrice(newPrice);
			return entry;
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
	
	
	public CalendarEntry delete(CalendarEntry entry) throws SQLException {
		String deleteEntry = "DELETE FROM CalendarEntry WHERE CalendarEntryPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEntry);
			deleteStmt.setInt(1, entry.getCalendarEntryPK());;
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

