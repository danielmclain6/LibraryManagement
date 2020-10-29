package com.cognixia.jump.library.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.dao.BookCheckoutDao;
import com.cognixia.jump.library.dao.BookCheckoutDaoImp;

/**
 * Servlet implementation class RetrunBookServlet
 */
@WebServlet("/return_book")
public class RetrunBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BookCheckoutDao bookCheckDAO = new BookCheckoutDaoImp();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
		String isbn = request.getParameter("isbn");
		System.out.println("in the Return Book.java file");
		
		if (bookCheckDAO.returnBook(isbn)) {
			response.sendRedirect("/LibraryManager/book?isbn=" + isbn);			
		} else {
			response.sendRedirect("/LibraryManager/books");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
