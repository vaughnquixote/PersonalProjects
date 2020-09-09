package pm4.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm4.model.AirBnB;
import pm4.model.CalendarEntry;
import pm4.model.Reviews;

public class ReviewsDao {
	private static ReviewsDao instance = null;
	protected ConnectionManager connectionManager;
	
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}

	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		String insertentry= "INSERT INTO Reviews(AirBnB_AirPK,Date,Comments) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertentry,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, review.getListing().getAirPK());
			insertStmt.setString(2, review.getDate().toString());
			insertStmt.setString(3, review.getComments().toString());
			

			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int PK = -1;
			if(resultKey.next()) {
				PK = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewPK(PK);
			return review;
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
	
	public Reviews getReviewByID(int id) throws SQLException {
		String selectListing = "SELECT ReviewPK, AirBnB_AirPK, Date, Comments FROM Reviews "
				+ "WHERE ReviewPK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListing);
			selectStmt.setInt(1, id );
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int ReviewPK = results.getInt("ReviewPK");
				int AirBnB_AirPK = results.getInt("AirBnB_AirPK");
				java.sql.Date Date = results.getDate("Date");
				String Comments = results.getString("Comments");
				
				AirBnB listing = AirBnBDao.getInstance().getAirbBnBById(AirBnB_AirPK);
				Reviews entry = new Reviews(ReviewPK, listing, Date, Comments);
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
	

	
	public Reviews updateComments(Reviews entry, String newComments) throws SQLException {
		String updatePrice = "UPDATE Reviews SET Comments=? WHERE ReviewPK=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePrice);
			updateStmt.setString(1, newComments);
			updateStmt.setInt(2, entry.getReviewPK());
			updateStmt.execute();

			entry.setComments(newComments);
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
	
	
	public Reviews delete(Reviews entry) throws SQLException {
		String deleteEntry = "DELETE FROM Reviews WHERE ReviewPK=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEntry);
			deleteStmt.setInt(1, entry.getReviewPK());;
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

