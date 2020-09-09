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

import pm4.dal.AirBnBDao;
import pm4.model.AirBnB;
import pm4.model.Location;

@WebServlet("/airbnbupdate")
public class AirBnBUpdate extends HttpServlet {
	protected AirBnBDao airdao;
	
	public void init() {
		airdao = AirBnBDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		if (req.getParameter("airpk").trim().equals("")) {
			messages.put("success", "Can't update an empty row");
        	req.getRequestDispatcher("/FindAirBnB.jsp").forward(req, resp);
		}
		String airpkString = req.getParameter("airpk");
    	int airPK = Integer.valueOf(airpkString);
    	req.setAttribute("airbnb", airPK);
    	
    	if (airPK <= 0) {
    		messages.put("title", "Invalid AirBnB");
    	} else {
    		try {
    			AirBnB airbnb = airdao.getAirbBnBById(airPK);
    			req.setAttribute("airbnb", airbnb.getAirPK());
    		} catch (SQLException e) {
        		messages.put("success", "AirBnB does not exist.");
	        }
    	}
    	req.getRequestDispatcher("/AirBnBUpdate.jsp").forward(req, resp);;
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		
		
		int airPK = Integer.valueOf(req.getParameter("airbnbkey"));
		
		if (airPK <= 0) {
			messages.put("title", "Invalid location");
            messages.put("disableSubmit", "true");
		} else {
			try {
				
			
			AirBnB airbnb = null;
			airbnb = airdao.getAirbBnBById(airPK);
			
    		if(airbnb == null) {
    			messages.put("success", "Location does not exist. No update to perform.");
    		} else {
    			String newPrice = req.getParameter("newprice");
    			if (newPrice == null || newPrice.trim().isEmpty()) {
    	            messages.put("success", "Please enter a valid new price.");
    	        } else {
    	        	airbnb = airdao.updatePrice(airbnb, newPrice);
    	        	messages.put("success", "Successfully updated " + newPrice);
    	        }
    		}
    		req.setAttribute("airbnb", airbnb);
			} catch (SQLException e) {
				messages.put("success", "Price not updated successfully");
			}
		}
		
		req.getRequestDispatcher("/AirBnBUpdate.jsp").forward(req, resp);
		
	}
}
