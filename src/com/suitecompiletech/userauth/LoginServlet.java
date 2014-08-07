package com.suitecompiletech.userauth;

import java.io.*;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class LoginServlet extends HttpServlet {
	
	
	public void init() throws ServletException {

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.getSession().removeAttribute(SessionKey.LASTERROR);
		
		
		Properties properties = new Properties();
		properties.load(getServletContext().getResourceAsStream("/WEB-INF/user.properties"));
		
		
		String username = "";
		String password = "";
		username = request.getParameter("username");
		password = request.getParameter("password");

		if (properties.getProperty(username) != null && password.equals(properties.getProperty(username))) {
			User user = new User();
			user.setUsername(username);
			request.getSession().setAttribute(SessionKey.USER, user);
			response.sendRedirect("/private/");
		} else {
			request.getSession().setAttribute(SessionKey.LASTERROR, "Invalid credentials");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void destroy() {
		// do nothing.
	}
}