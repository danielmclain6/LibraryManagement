package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.ResolutionException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.LibrarianDAO;
import com.cognixia.jump.library.dao.LibrarianDAOImp;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Librarian;
import com.cognixia.jump.library.models.Patron;
@WebServlet("/register")
public class RegisterServices extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1030946973739033378L;
	private PatronDAO patronDao;
	private LibrarianDAO librarianDao;
	private String errorMessage;

	@Override
	public void init() {
		librarianDao = new LibrarianDAOImp();
		patronDao = new PatronDAOImp();
		errorMessage = "";
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
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("on register route");
		// get params 
		String userType = request.getParameter("user_type");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		
		if(userType.equals("patron")) {
			Patron p = new Patron(0, first_name, last_name, username, password, true);
			boolean b = patronDao.addPatron(p);
			if(b) 
			{
				System.out.println("patron add!");
				session.setAttribute("user", p);
				session.setAttribute("user_id", p.getId());
				session.setAttribute("isLibrarian", false);
				
				response.sendRedirect("/LibraryManager/books");
			}
			else
			{
				errorMessage = "Username already in use.";
				request.setAttribute("errorMessage", errorMessage);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("loginReg.jsp");
				dispatcher.forward(request, response);
			}
			
		} else if(userType.equals("librarian")) {
			Librarian l = new Librarian(0, username, password);
			boolean b = librarianDao.addLibrarian(l);
			if(b) {
				System.out.println("patron add!");
				session.setAttribute("user", l);
				session.setAttribute("user_id", l.getId());
				session.setAttribute("isLibrarian", true);
				
				response.sendRedirect("/LibraryManager/books");
			}
			else
			{
				errorMessage = "Username already in use.";
				request.setAttribute("errorMessage", errorMessage);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("loginReg.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}
}
