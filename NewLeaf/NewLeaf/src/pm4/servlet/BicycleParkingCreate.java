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
import pm4.dal.NeighborhoodDao;
import pm4.model.BicycleParking;
import pm4.model.Neighborhood;


@WebServlet("/bicycleparkingcreate")
public class BicycleParkingCreate extends HttpServlet {

	
protected BicycleParkingDao bikeDao;
	
	@Override
	public void init() throws ServletException {
		bikeDao = bikeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/BicycleParkingCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String NKey = req.getParameter("Neighborhood");
        if (NKey == null || NKey.trim().isEmpty()) {
            messages.put("success", "Invalid NeighborHood");
        } else {
        	NeighborhoodDao NDao = NeighborhoodDao.getInstance();        	
        	
        	String AssetType = req.getParameter("AssetType");
        	String IsActive = req.getParameter("IsActive");
        	String Address = req.getParameter("Address");
        	
        	Neighborhood neighborhood = null;
			try {
				neighborhood = NDao.getNeighborhoodByName(NKey);
			} catch (SQLException e1) {
				messages.put("success", "Invalid neighborhood key (1)");
			}
        	if (neighborhood == null) {
        		messages.put("success", "Invalid neighborhood key, (2)");
        	}
	        try {
	        	BicycleParking bParking = new BicycleParking(AssetType, IsActive, Address, neighborhood);
	        	bParking = bikeDao.create(bParking);
	        	messages.put("success", "Successfully created " + Address);
	        } catch (Exception e) {
				messages.put("success", "Invalid neighborhood key (3)");
	        } 
        }
        
        req.getRequestDispatcher("/BicycleParkingCreate.jsp").forward(req, resp);
    }
}
	
	
	
	

