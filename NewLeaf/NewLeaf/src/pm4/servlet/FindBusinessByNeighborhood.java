package pm4.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pm4.dal.BusinessDao;
import pm4.model.Business;

@WebServlet("/findbusinessbyneighborhood")
public class FindBusinessByNeighborhood extends HttpServlet{
	
	protected BusinessDao bizDao;
	
	@Override
	public void init() throws ServletException {
		bizDao = BusinessDao.getInstance();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String, String> messages = new HashMap<String, String>();
		
		req.setAttribute("messages", messages);
		
		List<Business> bizes = null;
		String neighborhoodStr = req.getParameter("neighborhood");
		String starsStr = req.getParameter("rating");
		String revCountStr = req.getParameter("reviewcount");
		
	
		if(neighborhoodStr == null || starsStr == null || revCountStr == null) {
			messages.put("success1", "please enter valid search parameters");
		} else {
			try {
				double stars = Double.parseDouble(starsStr);
				int revCount = Integer.parseInt(revCountStr);
				bizes=bizDao.getBusinessForNeighborhood(neighborhoodStr, stars, revCount);
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success1", "Displaying results");
		}
		req.setAttribute("businesses", bizes);
		req.getRequestDispatcher("/FindBusiness.jsp").forward(req, resp);
				
	}
}
