package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.connection.ConnectionManager;
@WebServlet("/registerLibrarian")
public class LibrarinServices extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1030946973739033378L;

//	private LibrarianDao librarianDao;
	

	@Override
	public void init() {
//		librarianDao = new LibrarianDaoImp();
		Connection conn = ConnectionManager.getConnection();
	}

	@Override
	public void destroy() {

		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();

		pw.println("<html>");
		
		pw.println("<head>");
		pw.println("<title>Hello Servelts</title>");
		pw.println("</head>");

		pw.println("<body>");
		pw.println("<h1>!!!!</h1>");
		
		pw.println("</body>");
		
		
		pw.println("</html>");
		pw.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
	}
}
