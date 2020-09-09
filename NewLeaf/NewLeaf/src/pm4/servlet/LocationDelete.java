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

@WebServlet("/locationdelete")
public class LocationDelete extends HttpServlet {
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
        // Provide a title and render the JSP.
        if (req.getParameter("locationkey").trim().equals("")) {
        	messages.put("success", "Can't delete an empty row");
        	req.getRequestDispatcher("/FindLocation.jsp").forward(req, resp);
        }
        messages.put("title", "Delete Location " + req.getParameter("locationkey") + "?");        
        req.getRequestDispatcher("/LocationDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate locationkey.
        String locationKeyString = req.getParameter("locationkey");
        int locationKey = Integer.valueOf(locationKeyString);
        if (locationKey <= 0) {
            messages.put("title", "Invalid location");
            messages.put("disableSubmit", "true");
        } else {
        	Location location = null;
        	try {
	        	// Delete the Location.
	        	location = locationDao.getLocationByID(locationKey);
        	} catch (Exception e) {
        		messages.put("title", "Failed to delete for key " + locationKey);
                messages.put("disableSubmit", "true");
        	}
	        try {
	        	location = locationDao.delete(location);
	        	// Update the message.
		        if (location == null) {
		            messages.put("title", "Successfully deleted " + locationKey);
		            messages.put("disableSubmit", "true");

		        } else {
		        	messages.put("title", "Failed to delete " + locationKey);
		            messages.put("disableSubmit", "true");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/LocationDelete.jsp").forward(req, resp);
    }

}
