package com.suitecompiletech.userauth;

import java.io.*;

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
		
		String username = "";
		String password = "";
		username = request.getParameter("username");
		password = request.getParameter("password");

		if (username.equals("beta") && password.equals("ray bill")) {
			User user = new User();
			user.setUsername(username);
			request.getSession().setAttribute(SessionKey.USER, user);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/private/index.html");
			dispatcher.forward(request, response);
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