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
import com.cognixia.jump.library.utility.Utility;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LibrarianDAO librarianDAO;
	private PatronDAO patronDAO;
	private String errorMessage;
	
	public void init() {
		librarianDAO = new LibrarianDAOImp();
		patronDAO = new PatronDAOImp();
		errorMessage = "";
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
		
		HttpSession session = request.getSession();
		
//		System.out.println(username + " " + password);
		
		Object user = Utility.getUserByUsername(username);
		
		if(user instanceof Librarian)
		{
			Librarian lib = librarianDAO.getLibrarianByUsername(username);
			
//			System.out.println("hello");
			
			if (lib.getPassword().equals(password)) {
				session.setAttribute("isLibrarian", true);
				session.setAttribute("user", lib);
				session.setAttribute("user_id", lib.getId());
				response.sendRedirect("/LibraryManager/books");
			} else {
//				System.out.println("Wrong username or password or account does not exist");
				errorMessage = "Invalid password";
				request.setAttribute("errorMessage", errorMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("loginReg.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if(user instanceof Patron)
		{
			Patron patron = patronDAO.getPatronByUsername(username);
			
			if (patron.getPassword().equals(password)) {
				session.setAttribute("isLibrarian", false);
				session.setAttribute("user", patron);
				session.setAttribute("user_id", patron.getId());
				session.setAttribute("isFrozen", patron.isAccount_frozen());
				session.setAttribute("signinMsg", false);
				response.sendRedirect("/LibraryManager/books");
			} else {
//				System.out.println("Wrong username or password or account does not exist");
				errorMessage = "Invalid password";
				request.setAttribute("errorMessage", errorMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("loginReg.jsp");
				dispatcher.forward(request, response);
			}
		}
		else
		{
//			System.out.println("This user does not exist");
			errorMessage = "User does not exist";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("loginReg.jsp");
			dispatcher.forward(request, response);
		}
	}

}
