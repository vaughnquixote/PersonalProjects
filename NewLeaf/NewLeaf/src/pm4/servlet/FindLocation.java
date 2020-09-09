package pm4.servlet;

import pm4.dal.*;
import pm4.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/findlocation")
public class FindLocation extends HttpServlet {
	
	protected LocationDao locationDao;
	
	@Override
	public void init() throws ServletException {
		locationDao = LocationDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing neighborhoods.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Location location = null;
        
        // Retrieve and validate name.
        // location is retrieved from the URL query string.
        String address = req.getParameter("address");
        if (address == null || address.trim().isEmpty()) {
            messages.put("success", "Please enter a valid address.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		location = locationDao.getLocationByAddress(address);
            } catch (SQLException e) {
            	messages.put("success", "Please enter a valid address.");
            }
        	messages.put("success", "Displaying results for " + address);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousAddress", address);
        }
        req.setAttribute("location", location);
        
        req.getRequestDispatcher("/FindLocation.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Location location = null;
        
        // Retrieve and validate name.
        // address is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindLocation.jsp).
        String address = req.getParameter("address");
        if (address == null || address.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve location, and store as a message.
        	try {
        		location = locationDao.getLocationByAddress(address);
        		messages.put("success", "Displaying results for " + address);
            } catch (SQLException e) {
            	messages.put("success", "Please enter a valid address.");
            }
        }
        
        try {
        	req.setAttribute("location", location);
        	req.setAttribute("neighborhood", location.getNeighborhood());
        } catch (Exception e) {
        	messages.put("success", "Please enter a valid address");
        }
        
        req.getRequestDispatcher("/FindLocation.jsp").forward(req, resp);
    }
}
