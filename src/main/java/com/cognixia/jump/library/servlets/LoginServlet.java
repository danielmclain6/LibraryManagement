package com.cognixia.jump.library.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.models.Librarian;
import com.cognixia.jump.library.models.Patron;

/**
 * Servlet implementation class Login
 */


@WebServlet("/login")
public class LoginServlet extends HttpServlet implements HttpSession{
	private static final long serialVersionUID = 1L;
	
	private LibrarianDAO librarianDAO;
	private PatronDAO patronDAO;
	
	public void init() {
		librarianDAO = new LibrarianDAO();
		patronDAO = new PatronDAO();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("user_type");
		
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		
		if (userType.equals("librarian")) {
			Librarian lib = librarianDAO.getLibrarianByUsername(username);
			
			if (lib.getPassword().equals(password)) {
				
				session.setAttribute("isLibrarian", true);
				session.setAttribute("librarian", lib);
				response.sendRedirect("books");
			} else {
				System.out.println("Wrong username or password or account does not exist");
			}
		}
		
		if (userType.equals("patron")) {
			Patron patron = patronDAO.getPatronByUsername(username);
			
			if (patron.getPassword().equals(password)) {
				
				session.setAttribute("isLibrarian", false);
				session.setAttribute("patron", patron);
				response.sendRedirect("books");
			} else {
				System.out.println("Wrong username or password or account does not exist");
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
