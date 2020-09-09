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
import pm4.model.BicycleParking;
import pm4.model.Location;
import pm4.dal.BicycleParkingDao;


@WebServlet("/bicycleparkingupdate")
public class BicycleParkingUpdate extends HttpServlet {
	protected BicycleParkingDao bikeDao;
	
	@Override
	public void init() throws ServletException {
		bikeDao = bikeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
        if (req.getParameter("bicycleparkingkey").trim().equals("")) {
        	messages.put("success", "Can't update an empty row");
        	req.getRequestDispatcher("/FindBicycleParking.jsp").forward(req, resp);
        }
        // Retrieve location and validate

    	String bikeparkingString = req.getParameter("bicycleparkingkey");
    	int parkingKey = Integer.valueOf(bikeparkingString);
    	req.setAttribute("bicycleparkingkey", parkingKey);
        if (parkingKey <= 0) {
            messages.put("title", "Invalid bike parking");
        } else {
        	try {
        		BicycleParking bikeParking = bikeDao.getBicycleParkingById(parkingKey);
        		req.setAttribute("bicycelparkingkey", bikeParking.getBicycleParkingPK());
        	} catch (SQLException e) {
        		messages.put("success", "bike parking does not exist.");
	        }
        }
        
        req.getRequestDispatcher("/BicycleParkingUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve location and validate.
    	String bikeparkingString = req.getParameter("bicycleparkingkey");
        int parkingKey = Integer.valueOf(bikeparkingString);
        if (parkingKey <= 0) {
            messages.put("success", "Please enter a valid Location.");
        } else {
        	try {
        		BicycleParking bikeParking = bikeDao.getBicycleParkingById(parkingKey);
        		if(bikeParking == null) {
        			messages.put("success", "Location does not exist. No update to perform.");
        		} else {
        			String newIsActive = req.getParameter("newIsActive");
        			if (newIsActive == null || newIsActive.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid isActive.");
        	        } else {
        	        	bikeParking = bikeDao.updateIsActive(bikeParking, newIsActive);
        	        	messages.put("success", "Successfully updated " + newIsActive);
        	        }
        		}
        		req.setAttribute("bikeParking", bikeParking);
        	} catch (SQLException e) {
        		messages.put("success", "BicycleParking not updated successfully");
	        }
        }
        
        req.getRequestDispatcher("/BicycleParkingUpdate.jsp").forward(req, resp);
    }
	
}
