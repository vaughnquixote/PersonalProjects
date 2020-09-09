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

import pm4.dal.CrimeDao;
import pm4.model.Crime;

@WebServlet("/findcrimelatlong")
public class FindCrimeLatLong extends HttpServlet{
	
protected CrimeDao crimeDao;
	
	@Override
	public void init() throws ServletException {
		crimeDao = CrimeDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Crime> crimes = null;
        
        // Retrieve and validate name.
        // address is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindLocation.jsp).
        String latitude = req.getParameter("latitude");
        String longitude = req.getParameter("longitude");
        if (latitude == null || latitude.trim().isEmpty()) {
            messages.put("success", "Please enter a valid latitude.");
        } else if (longitude == null || longitude.trim().isEmpty()) {
            messages.put("success", "Please enter a valid longitude.");
        } else {
        	// Retrieve location, and store as a message.
        	try {
        		crimes = crimeDao.getCrimesByLatLong(latitude, longitude);
        		messages.put("success", "Displaying results for (" + latitude + ", " + longitude + ")");
            } catch (SQLException e) {
            	messages.put("success", "Please enter a valid latitude and longitude.");
            }
        }
        try {
        	req.setAttribute("crimes", crimes);
        } catch (Exception e) {
        	messages.put("success", "Please enter a valid latitude and longitude");
        }
        
        req.getRequestDispatcher("/FindCrime.jsp").forward(req, resp);
    }
}