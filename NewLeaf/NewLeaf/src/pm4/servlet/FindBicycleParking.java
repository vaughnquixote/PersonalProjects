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

import pm4.dal.BicycleParkingDao;
import pm4.dal.LocationDao;
import pm4.model.BicycleParking;
import pm4.model.Location;

@WebServlet("/findbicycleparking")

public class FindBicycleParking extends HttpServlet {
	
	protected BicycleParkingDao bikeDao;
	
	@Override
	public void init() throws ServletException {
		bikeDao = BicycleParkingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing neighborhoods.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<BicycleParking> bikeParking = null;
        
        // Retrieve and validate name.
        // location is retrieved from the URL query string.
        String neighborhoodin = req.getParameter("neighborhoodin");
        if (neighborhoodin == null || neighborhoodin.trim().isEmpty()) {
            messages.put("success", "Please enter a valid neighborhood.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		bikeParking = bikeDao.getBicycleParkingByNeighborhood(neighborhoodin);
            } catch (SQLException e) {
            	messages.put("success", "Please enter a valid address.");
            }
        	messages.put("success", "Displaying results for " + neighborhoodin);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousNeighborhood", neighborhoodin);
        }
        req.setAttribute("bikeparking", bikeParking);
        
        req.getRequestDispatcher("/FindBicycleParking.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<BicycleParking> bikeParking = null;
        
        // Retrieve and validate name.
        // address is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindLocation.jsp).
        String neighborhoodin = req.getParameter("neighborhoodin");
        if (neighborhoodin == null || neighborhoodin.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve location, and store as a message.
        	try {
        		bikeParking = bikeDao.getBicycleParkingByNeighborhood(neighborhoodin);
        		messages.put("success", "Displaying results for " + neighborhoodin);
            } catch (SQLException e) {
            	messages.put("success", "Please enter a valid address.");
            }
        }
        
        try {
        	req.setAttribute("bikeparking", bikeParking);
        } catch (Exception e) {
        	messages.put("success", "Please enter a valid address");
        }
        
        req.getRequestDispatcher("/FindBicycleParking.jsp").forward(req, resp);
    }
}