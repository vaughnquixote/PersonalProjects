package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.CrimeDao;
import pm4.dal.NeighborhoodDao;
import pm4.model.Crime;
import pm4.model.Neighborhood;

@WebServlet("/findcrime")
public class FindCrime extends HttpServlet{
	
protected CrimeDao crimeDao;
	
	@Override
	public void init() throws ServletException {
		crimeDao = CrimeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing neighborhoods.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Crime> crime = null;
        NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
        List<Neighborhood> neighborhoods = null;
		try {
			neighborhoods = neighborhoodDao.getNeighborhoods();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // Retrieve and validate name.
        // location is retrieved from the URL query string.
        // String address = req.getParameter("address");
        /*
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
        */
        
        
        req.setAttribute("neighborhoods", neighborhoods);
        
        req.getRequestDispatcher("/FindCrime.jsp").forward(req, resp);
	}
}
