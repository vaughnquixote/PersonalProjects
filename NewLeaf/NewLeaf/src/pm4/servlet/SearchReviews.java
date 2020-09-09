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

import pm4.dal.NeighborhoodDao;
import pm4.dal.NeighborhoodDao.Pair;
import pm4.model.Business;

@WebServlet("/searchreviews")
public class SearchReviews extends HttpServlet{
	protected NeighborhoodDao NDao;
	
	@Override
	public void init() throws ServletException {
		NDao = NDao.getInstance();
	}
	
	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Pair> pairs = new ArrayList<Pair>();
		
		String phrase = req.getParameter("phrase");
		if(phrase == null || phrase.trim().isEmpty()) {
			messages.put("success", "Please enter a search phrase");		
		} else {
			try {
				
				pairs = NDao.getNeighborhoodsByReviewPhrase(phrase);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for reviews containg :" + phrase);
		}
		req.setAttribute("pairs", pairs);
		req.getRequestDispatcher("/SearchReviews.jsp").forward(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Pair> pairs = new ArrayList<Pair>();
		
		String phrase = req.getParameter("phrase");
		if(phrase == null || phrase.trim().isEmpty()) {
			messages.put("success", "Please enter a search phrase");		
		} else {
			try {
				
				pairs = NDao.getNeighborhoodsByReviewPhrase(phrase);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for reviews containg :" + phrase);
		}
		req.setAttribute("pairs", pairs);
		req.getRequestDispatcher("/SearchReviews.jsp").forward(req, resp);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
