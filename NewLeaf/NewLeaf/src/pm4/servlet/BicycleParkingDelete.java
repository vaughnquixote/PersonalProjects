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

import pm4.dal.BicycleParkingDao;
import pm4.model.BicycleParking;

@WebServlet("/bicycleparkingdelete")
public class BicycleParkingDelete extends HttpServlet {
	protected BicycleParkingDao BPDao;
	
	@Override
	public void init() throws ServletException {
		BPDao = BicycleParkingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        if (req.getParameter("bicycleparkingkey").trim().equals("")) {
        	messages.put("success", "Can't delete an empty row");
        	req.getRequestDispatcher("/FindBicycleParking.jsp").forward(req, resp);
        }
        messages.put("title", "Delete BicycleParking " + req.getParameter("bicycleparkingkey") + "?");        
        req.getRequestDispatcher("/BicycleParkingDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate BicycleParkingkey.
        String BPString = req.getParameter("bicycleparkingkey");
        int parkingKey = Integer.valueOf(BPString);
        if (parkingKey <= 0) {
            messages.put("title", "Invalid location");
            messages.put("disableSubmit", "true");
        } else {
        	BicycleParking parking = null;
        	try {
	        	// Delete the Location.
        		parking = BPDao.getBicycleParkingById(parkingKey);
        	} catch (Exception e) {
        		messages.put("title", "Failed to delete for key " + parkingKey);
                messages.put("disableSubmit", "true");
        	}
	        try {
	        	parking = BPDao.delete(parking);
	        	// Update the message.
		        if (parking == null) {
		            messages.put("title", "Successfully deleted " + parkingKey);
		            messages.put("disableSubmit", "true");

		        } else {
		        	messages.put("title", "Failed to delete " + parkingKey);
		            messages.put("disableSubmit", "true");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BicycleParkingDelete.jsp").forward(req, resp);
    }

}