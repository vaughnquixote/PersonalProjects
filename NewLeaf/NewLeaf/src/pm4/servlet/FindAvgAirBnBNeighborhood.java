package pm4.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import pm4.dal.AirBnBDao;
import pm4.dal.NeighborhoodDao;
import pm4.model.Neighborhood;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

@WebServlet("/findavgairbnbbyneighborhood")
public class FindAvgAirBnBNeighborhood extends HttpServlet {
	
	protected AirBnBDao airDao;
	
	@Override 
	public void init() throws ServletException {
		airDao = AirBnBDao.getInstance();
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String, String> messages = new HashMap<String, String>();
		
		req.setAttribute("messages", messages);
		
		Double avgPrice = 0.0;
		
		String neighborhood = req.getParameter("neighborhood");
		
		if(neighborhood == null || neighborhood.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name");
		} else {
			try {
				avgPrice = airDao.getAvgAirBnBPriceByNeighborhood(neighborhood);
				avgPrice = round(avgPrice, 2);
				messages.put("success", "The average price for AirBnBs in " + neighborhood + " is $" + avgPrice + " per night");
			} catch (SQLException e) {
				messages.put("success", "failed to retrieve average price");
			}
		}
		
		try {
			req.setAttribute("avgprice", avgPrice);
		} catch (Exception e) {
			messages.put("success", "Please enter a valid neighborhood");
		}
		
		req.getRequestDispatcher("/FindAirBnB.jsp").forward(req, resp);
	}
}
