package pm4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.BusinessDao;
import pm4.model.Business;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/businessdelete")
public class BusinessDelete  extends HttpServlet {
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
        // Provide a title and render the JSP.
        if (req.getParameter("businesskey").trim().equals("")) {
        	messages.put("success", "Can't delete an empty row");
        	req.getRequestDispatcher("/FindBusiness.jsp").forward(req, resp);
        }
        messages.put("title", "Delete Business " + req.getParameter("businesskey") + "?");        
        req.getRequestDispatcher("/BusinessDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String businessKeyString = req.getParameter("businesskey");
        int businessKey = Integer.valueOf(businessKeyString);
        if (businessKey <= 0) {
            messages.put("title", "Invalid business");
            messages.put("disableSubmit", "true");
        } else {
        	Business biz = null;
        	try {
	        	// Delete the Location.
	        	biz = businessDao.getBusinessById(businessKey);
        	} catch (Exception e) {
        		messages.put("title", "Failed to delete for key " + businessKey);
                messages.put("disableSubmit", "true");
        	}
	        try {
	        	biz = businessDao.delete(biz);
	        	// Update the message.
		        if (biz == null) {
		            messages.put("title", "Successfully deleted " + businessKey);
		            messages.put("disableSubmit", "true");

		        } else {
		        	messages.put("title", "Failed to delete " + businessKey);
		            messages.put("disableSubmit", "true");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BusinessDelete.jsp").forward(req, resp);
    }
}
