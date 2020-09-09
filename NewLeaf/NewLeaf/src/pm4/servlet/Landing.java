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

import pm4.model.Location;

@WebServlet("/landing")
public class Landing extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Map for storing neighborhoods.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("success", "Choose an action");
        req.getRequestDispatcher("/Landing2.jsp").forward(req, resp);
	}

	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Map for storing neighborhoods.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("success", "Choose an action");
        req.getRequestDispatcher("/Landing2.jsp").forward(req, resp);
	}
	
}
