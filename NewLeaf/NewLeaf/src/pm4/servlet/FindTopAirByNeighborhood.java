package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.AirBnBDao;
import pm4.model.AirBnB;

@WebServlet("/topairbyneighborhood")
public class FindTopAirByNeighborhood extends HttpServlet{
	
	protected AirBnBDao airDao;
	@Override
	public void init() {
		airDao = AirBnBDao.getInstance();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String,String>();
		req.setAttribute("messages", messages);
		List<AirBnB> airs = null;
		String neighborhood = req.getParameter("neighborhood");
		if(neighborhood == null || neighborhood.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			try {
				airs = airDao.getTop20ByRating(neighborhood);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success1", "Displaying the top 20 AirBnBs in " + neighborhood);
		}
		req.setAttribute("airs", airs);
		req.getRequestDispatcher("/FindAirBnB.jsp").forward(req, resp);
		
	}
}
