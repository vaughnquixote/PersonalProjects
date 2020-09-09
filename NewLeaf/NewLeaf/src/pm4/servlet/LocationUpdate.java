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

import pm4.dal.LocationDao;
import pm4.model.Location;

@WebServlet("/locationupdate")
public class LocationUpdate extends HttpServlet {
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

        
        if (req.getParameter("locationkey").trim().equals("")) {
        	messages.put("success", "Can't update an empty row");
        	req.getRequestDispatcher("/FindLocation.jsp").forward(req, resp);
        }
        // Retrieve location and validate

    	String locationKeyString = req.getParameter("locationkey");
    	int locationKey = Integer.valueOf(locationKeyString);
    	req.setAttribute("locationkey", locationKey);
        if (locationKey <= 0) {
            messages.put("title", "Invalid location");
        } else {
        	try {
        		Location location = locationDao.getLocationByID(locationKey);
        		req.setAttribute("location", location.getLocationKey());
        	} catch (SQLException e) {
        		messages.put("success", "Location does not exist.");
	        }
        }
        
        req.getRequestDispatcher("/LocationUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve location and validate.
        String locationKeyString = req.getParameter("locationkey");
        int locationKey = Integer.valueOf(locationKeyString);
        if (locationKey <= 0) {
            messages.put("success", "Please enter a valid Location.");
        } else {
        	try {
        		Location location = locationDao.getLocationByID(locationKey);
        		if(location == null) {
        			messages.put("success", "Location does not exist. No update to perform.");
        		} else {
        			String newAddress = req.getParameter("newaddress");
        			if (newAddress == null || newAddress.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid address.");
        	        } else {
        	        	location = locationDao.updateAddress(location, newAddress);
        	        	messages.put("success", "Successfully updated " + newAddress);
        	        }
        		}
        		req.setAttribute("location", location);
        	} catch (SQLException e) {
        		messages.put("success", "Location not updated successfully");
	        }
        }
        
        req.getRequestDispatcher("/LocationUpdate.jsp").forward(req, resp);
    }
	
}
