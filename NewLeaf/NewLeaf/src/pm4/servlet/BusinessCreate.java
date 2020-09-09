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
import pm4.dal.LocationDao;
import pm4.model.Business;
import pm4.model.Location;

@WebServlet("/businesscreate")
public class BusinessCreate extends HttpServlet {
	protected BusinessDao businessDao;
	
	@Override
	public void init() throws ServletException {
		businessDao = BusinessDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/BusinessCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String locationKey = req.getParameter("LocationFK");
        if (locationKey == null || locationKey.trim().isEmpty()) {
            messages.put("success", "Invalid Location Key");
        } else {
        	LocationDao locationDao = LocationDao.getInstance();
        	
        	String name = req.getParameter("Name");
        	String stars = req.getParameter("Stars");
        	String reviewCount = req.getParameter("ReviewCount");
        	String attributes = req.getParameter("Attributes");
        	String categories = req.getParameter("Categories");
        	String hours = req.getParameter("Hours");
        	Location loc = null;
        	try {
        		loc = locationDao.getLocationByID(Integer.parseInt(locationKey));
        	} catch(SQLException e) {
        		messages.put("success", "Invalid location key");
        	}
        	
        	if(loc == null) {
        		messages.put("sucess", "Invalid location key");
        	}
        	
        	try {
        		Business biz = new Business(name, Double.parseDouble(stars), Integer.parseInt(reviewCount), attributes,
        				categories, hours, loc);
        		biz = businessDao.create(biz);
        		messages.put("success", "Successfully created " + name);
        	} catch (Exception e) {
        		messages.put("success", "Invalid entry/entries");
        	}
        }
        
        req.getRequestDispatcher("/BusinessCreate.jsp").forward(req, resp);
    }
}
