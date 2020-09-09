package pm4.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import pm4.dal.*;
import pm4.model.*;

@WebServlet("/findbusiness")
public class FindBusiness extends HttpServlet {
	protected BusinessDao businessDao;
	
	@Override 
	public void init() throws ServletException {
		businessDao = BusinessDao.getInstance();
	}
	
	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Map<String, String> messages = new HashMap<String,String>();
		req.setAttribute("messages", messages);
		
		List<Business> businesses = new ArrayList<Business>();
		
		String ratingStr = req.getParameter("rating");
		
		
		if(ratingStr == null || ratingStr.trim().isEmpty()) {
			messages.put("success", "Please enter a valid rating");
		} else {
			try {
				double rating = Double.parseDouble(ratingStr);
				businesses = businessDao.getBusinessForRating(rating);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			} 
			messages.put("success", "Displaying results for " + ratingStr);
			messages.put("previousRating", ratingStr);
		}
		req.setAttribute("businesses", businesses);
		req.getRequestDispatcher("/FindBusiness.jsp").forward(req, resp);

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Business> businesses = new ArrayList<Business>();
		
		String ratingStr = req.getParameter("rating");
		if(ratingStr == null || ratingStr.trim().isEmpty()) {
			messages.put("success", "Please eneter a valid rating");		
		} else {
			try {
				double rating = Double.parseDouble(ratingStr);
				businesses = businessDao.getBusinessForRating(rating);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + ratingStr);
		}
		req.setAttribute("businesses", businesses);
		req.getRequestDispatcher("/FindBusiness.jsp").forward(req, resp);
		
	}
}

