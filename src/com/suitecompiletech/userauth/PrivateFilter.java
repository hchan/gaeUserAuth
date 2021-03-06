package com.suitecompiletech.userauth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivateFilter implements Filter {

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletrequest;
		HttpServletResponse resp = (HttpServletResponse) servletresponse;
		HttpSession session = req.getSession();
		if (req.getRequestURI().startsWith("/private/")) {
			User user = (User) session.getAttribute(SessionKey.USER);
			if (user == null) {
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

				dispatcher.forward(req, resp);
				return;
			} else {
				String newURL = req.getRequestURI().replace("/private/", "/WEB-INF/private/");
				RequestDispatcher dispatcher = req
						.getRequestDispatcher(newURL);

				dispatcher.forward(req, resp);
			}
		} else {
			filterchain.doFilter(servletrequest, servletresponse);
		}
	}

	@Override
	public void destroy() {

	}

}
