package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.AirBnBDao;

@WebServlet("/findavgairbnbbylocation")
public class FindAvgAirBnBLocation extends HttpServlet {
	protected AirBnBDao airDao;
	
	@Override
	public void init() throws ServletException {
		airDao = AirBnBDao.getInstance();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String, String> messages = new HashMap<String, String>();
		
		req.setAttribute("messages", messages);
		
		Double avgPrice = 0.0;
		
		String lat = req.getParameter("latitude");
		Double latitude = null;
		try {
			latitude = Double.parseDouble(lat);
		} catch (Exception e){
			messages.put("success", "Please enter a valid latitude");
		}
		
		String longit = req.getParameter("longitude");
		Double longitude = null;
		try {
			longitude = Double.parseDouble(longit);
		} catch (Exception e){
			messages.put("success", "Please enter a valid latitude");
		}
		
		String rad = req.getParameter("searchradius");
		Double searchRadius = null;
		try {
			searchRadius = Double.parseDouble(rad);
		 }catch (Exception e) {
			messages.put("success", "Please enter a valid latitude");
		}
		
		if(searchRadius == null || longitude == null || latitude == null ) {
			messages.put("success", "Please enter non-negative values for latitude, "
					+ "longitude and search radius");
		} else {
			try {
				avgPrice = airDao.getAvgAirBnBPriceByLocation(latitude, longitude, searchRadius);
				avgPrice = FindAvgAirBnBNeighborhood.round(avgPrice, 2);
				messages.put("success", "The average price for AirBnBs within " + searchRadius + 
						"km of Latitude: " + latitude + " Longitude: " + longitude + " is $" + avgPrice);
			} catch (SQLException e) {
				messages.put("success", "failed to retrieve average price");
			}
		}
		
		try {
			req.setAttribute("avgprice", avgPrice);
		} catch (Exception e) {
			messages.put("success", "Please enter valid search parameters");
		}
		
		req.getRequestDispatcher("/FindAirBnB.jsp").forward(req, resp);
	}
}
