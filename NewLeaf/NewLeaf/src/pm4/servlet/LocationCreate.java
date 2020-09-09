package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.LocationDao;
import pm4.dal.NeighborhoodDao;
import pm4.model.Neighborhood;
import pm4.model.Location;

@WebServlet("/locationcreate")
public class LocationCreate extends HttpServlet {
	protected LocationDao locationDao;
	
	@Override
	public void init() throws ServletException {
		locationDao = LocationDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/LocationCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String neighborhoodName = req.getParameter("neighborhood");
        if (neighborhoodName == null || neighborhoodName.trim().isEmpty()) {
            messages.put("success", "Invalid neighborhood name");
        } else {
        	NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
        	// Create the location.
        	String address = req.getParameter("address");
        	String city = req.getParameter("city");
        	String state = req.getParameter("state");
        	String zipCode = req.getParameter("zipcode");
        	String latitude = req.getParameter("latitude");
        	String longitude = req.getParameter("longitude");
        	Neighborhood neighborhood;
        	neighborhood = null;
			try {
				neighborhood = neighborhoodDao.getNeighborhoodByName(neighborhoodName);
			} catch (SQLException e1) {
				messages.put("success", "Invalid neighborhood name");
			}
        	if (neighborhood == null) {
        		messages.put("success", "Invalid neighborhood name");
        	}
	        try {
	        	Location location = new Location(address,city,state,zipCode,latitude,longitude,neighborhood);
	        	location = locationDao.create(location);
	        	messages.put("success", "Successfully created " + address);
	        } catch (Exception e) {
				messages.put("success", "Invalid neighborhood name");
	        } 
        }
        
        req.getRequestDispatcher("/LocationCreate.jsp").forward(req, resp);
    }

}
