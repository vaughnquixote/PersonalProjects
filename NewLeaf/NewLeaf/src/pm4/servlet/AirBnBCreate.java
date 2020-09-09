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
import pm4.dal.LocationDao;
import pm4.model.AirBnB;
import pm4.model.Location;

@WebServlet("/airbnbcreate")
public class AirBnBCreate extends HttpServlet {
	protected AirBnBDao airDao;
	
	@Override
	public void init() throws ServletException {
		airDao = airDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AirBnBCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String locationKey = req.getParameter("location");
        if (locationKey == null || locationKey.trim().isEmpty()) {
            messages.put("success", "Invalid location key");
        } else {
        	LocationDao locationDao = LocationDao.getInstance();
        	// Create the airbnb.
        	
        	
        	String summary = req.getParameter("summary");
        	String space = req.getParameter("space");
        	String description = req.getParameter("description");
        	String propertyType = req.getParameter("propertytype");
        	String roomType = req.getParameter("roomtype");
        	String bedrooms = req.getParameter("bedrooms");
        	String bathrooms = req.getParameter("bathrooms");
        	float squarefeet = Float.parseFloat(req.getParameter("squarefeet"));
        	String price = req.getParameter("price");
        	String weeklyprice = req.getParameter("weeklyprice");
        	String monthlyprice = req.getParameter("monthlyprice");
        	String securitydeposit = req.getParameter("securitydeposit");
        	int rating = Integer.parseInt(req.getParameter("rating"));
        	
        	Location location = null;
			try {
				location = locationDao.getLocationByID(Integer.parseInt(locationKey));
			} catch (SQLException e1) {
				messages.put("success", "Invalid location key");
			}
        	if (location == null) {
        		messages.put("success", "Invalid location key");
        	}
	        try {
	        	AirBnB airbnb = new AirBnB(summary,space,description,propertyType,roomType,bedrooms
	        			,bathrooms, squarefeet, price, weeklyprice, monthlyprice, securitydeposit,rating,location);
	        	airbnb = airDao.create(airbnb);
	        	messages.put("success", "Successfully created " + locationKey);
	        } catch (Exception e) {
				messages.put("success", "Invalid location key");
	        } 
        }
        
        req.getRequestDispatcher("/AirBnBCreate.jsp").forward(req, resp);
    }
}
