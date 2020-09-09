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

@WebServlet("/airbnbdelete")
public class AirBnBDelete extends HttpServlet {
	protected AirBnBDao airDao;
	
	@Override
	public void init() throws ServletException {
		airDao = AirBnBDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		if (req.getParameter("airpk").trim().equals("")) {
			messages.put("success", "Can't delete an empty row");
        	req.getRequestDispatcher("/AirBnBDelete.jsp").forward(req, resp);
		}
		messages.put("title", "Delete AirBnB " + req.getParameter("airpk") + "?");        
        req.getRequestDispatcher("/AirBnBDelete.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		
		
		int airPK = Integer.valueOf(req.getParameter("airbnbPK"));
		
		if (airPK <= 0) {
			messages.put("title", "Invalid location");
            messages.put("disableSubmit", "true");
		} else {
			//messages.put("disableSubmit", "false");
			AirBnB air = null;
			try {
				air = airDao.getAirbBnBById(airPK);
			} catch (SQLException e) {
				messages.put("title", "Failed to delete for key " + airPK);
                messages.put("disableSubmit", "true");
			} 
			
			try {
				air = airDao.delete(air);
				if (air == null) { // successful delete
					messages.put("title", "Successfully deleted");
					messages.put("DisableSubmit", "true");
				} else {
					messages.put("title", "Failed to delete " + airPK);
		            messages.put("disableSubmit", "true");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			req.getRequestDispatcher("/AirBnBDelete.jsp").forward(req, resp);
		}		
	}
}
