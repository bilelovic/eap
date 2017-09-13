package com.sfl.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "JMSServlet", urlPatterns = {"/jms"})
public class JMSServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(JMSServlet.class.toString());
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("--------++ GET ++------");
		try {
			response.getWriter().append("<h3>Hello</h3> <br/>");
//			consumer.consume(response.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
