package com.cognixia.jump.library.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.dao.LibrarianDAO;
import com.cognixia.jump.library.dao.LibrarianDAOImp;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Librarian;
import com.cognixia.jump.library.models.Patron;

/**
 * Servlet implementation class Login
 */


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LibrarianDAO librarianDAO;
	private PatronDAO patronDAO;
	
	public void init() {
		librarianDAO = new LibrarianDAOImp();
		patronDAO = new PatronDAOImp();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// rendering template
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginReg.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// logic for login
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("user_type");
		
		HttpSession session = request.getSession();
		
		if (userType.equals("librarian")) {
//			Librarian lib = librarianDAO.getLibrarianByUserName(username);
			Librarian lib = librarianDAO.getLibrarianById(1);
			
			if (lib.getPassword().equals(password)) {
				session.setAttribute("isLibrarian", true);
				session.setAttribute("user", lib);
				response.sendRedirect("/LibraryManager/books");
			} else {
				System.out.println("Wrong username or password or account does not exist");
			}
		}
		
		if (userType.equals("patron")) {
//			Patron patron = patronDAO.getPatronByUsername(username);
			Patron patron = patronDAO.getPatronById(1);
			
			if (patron.getPassword().equals(password)) {
				
				session.setAttribute("isLibrarian", false);
				session.setAttribute("user", patron);
				response.sendRedirect("/LibraryManager/books");
			} else {
				System.out.println("Wrong username or password or account does not exist");
			}
		}
	}

}
