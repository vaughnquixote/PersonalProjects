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

import pm4.dal.BusinessDao;
import pm4.model.Business;
import pm4.model.Location;

@WebServlet("/businessupdate")
public class BusinessUpdate extends HttpServlet {
	protected BusinessDao businessDao;
	
	@Override
	public void init() throws ServletException{
		businessDao = businessDao.getInstance();
	}
	
	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
        if (req.getParameter("businesskey").trim().equals("")) {
        	messages.put("success", "Can't update an empty row");
        	req.getRequestDispatcher("/FindBusiness.jsp").forward(req, resp);
        }
        // Retrieve location and validate

    	String businessKeyString = req.getParameter("businesskey");
    	int businessKey = Integer.valueOf(businessKeyString);
    	req.setAttribute("businesskey", businessKey);
        if (businessKey <= 0) {
            messages.put("title", "Invalid location");
        } else {
        	try {
        		Business biz = businessDao.getBusinessById(businessKey);
        		req.setAttribute("business", biz.getParksPK());
        	} catch (SQLException e) {
        		messages.put("success", "Business does not exist.");
	        }
        }
        
        req.getRequestDispatcher("/BusinessUpdate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve location and validate.
        String businessKeyString = req.getParameter("businesskey");
        int businessKey = Integer.valueOf(businessKeyString);
        if (businessKey <= 0) {
            messages.put("success", "Please enter a valid Business.");
        } else {
        	try {
        		Business biz = businessDao.getBusinessById(businessKey);
        		if(biz == null) {
        			messages.put("success", "Business does not exist. No update to perform.");
        		} else {
        			String newHours = req.getParameter("newhours");
        			if (newHours == null || newHours.trim().isEmpty()) {
        	            messages.put("success", "Please enter valid hours.");
        	        } else {
        	        	biz = businessDao.updateHours(biz, newHours);
        	        	messages.put("success", "Successfully updated " + newHours);
        	        }
        		}
        		req.setAttribute("business", biz);
        	} catch (SQLException e) {
        		messages.put("success", "Business not updated successfully");
	        }
        }
        
        req.getRequestDispatcher("/BusinessUpdate.jsp").forward(req, resp);
    }
}

