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

import pm4.dal.AirBnBDao;
import pm4.dal.NeighborhoodDao;
import pm4.model.AirBnB;
import pm4.model.Crime;
import pm4.model.Neighborhood;

@WebServlet("/findairbnb")
public class FindAirBnB extends HttpServlet {

	protected AirBnBDao airDao;
	
	@Override
	public void init() throws ServletException {
		airDao = AirBnBDao.getInstance();
	}
	
	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	
		// create a mapping to store messages
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		
		NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
		List<Neighborhood> neighborhoods = null;
		try {
			neighborhoods = neighborhoodDao.getNeighborhoods();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		AirBnB airbnb = null;
		int count = 0;
		Integer id = null;
		
		// Retrieve and validate id.
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			// to flag invalid input
			count ++;
		}
		
		if (count > 0) { // meaning we had invalid input
			messages.put("success", "Please enter a valid id.");
		} else { // try getting airbnb record from db 
			try {
				airbnb = airDao.getAirbBnBById(id);
			} catch (SQLException e) {
            	messages.put("success", "Please enter a valid id.");
            }
			messages.put("success", "Displaying results for " + id);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousId", "" + id);
		}
		
		req.setAttribute("airbnb", airbnb);
		*/
		req.setAttribute("neighborhoods", neighborhoods);
		req.getRequestDispatcher("/FindAirBnB.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// create a mapping to store messages
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		AirBnB airbnb = null;
		int count = 0;
		Integer id = null;
		
		// Retrieve and validate id.
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			// to flag invalid input
			count ++;
		}
		
		if (count > 0) { // meaning we had invalid input
			messages.put("success", "Please enter a valid id.");
		} else { // try getting airbnb record from db 
			try {
				airbnb = airDao.getAirbBnBById(id);
			} catch (SQLException e) {
            	messages.put("success", "Please enter a valid id.");
            }
			messages.put("success", "Displaying results for " + id);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousId", "" + id);
		}
		
		try {
        	req.setAttribute("airbnb", airbnb);
        	req.setAttribute("airpk", airbnb.getAirPK());
        } catch (Exception e) {
        	messages.put("success", "Please enter a valid address");
        }
        
        req.getRequestDispatcher("/FindAirBnB.jsp").forward(req, resp);
	}
}