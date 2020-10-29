package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.BookDao;
import com.cognixia.jump.library.dao.BookDaoImp;
import com.cognixia.jump.library.models.Book;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/book")
public class OneBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookDao bookDao;

	@Override
	public void init() {

		bookDao = new BookDaoImp();
	}

	@Override
	public void destroy() {

		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------in the onebookServelt.java file");
		HttpSession session = request.getSession();
		Book book = bookDao.getBookByIsbn(request.getParameter("isbn"));
		
		// models onto jsps
		request.setAttribute("book", book);
		request.setAttribute("user", 
				session.getAttribute("user") == null ? null : session.getAttribute("user"));
		request.setAttribute("isLibrarian", 
				session.getAttribute("isLibrarian") == null ? false : session.getAttribute("isLibrarian"));
		
		// render template
		RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Boolean isLib = (Boolean)session.getAttribute("isLibrarian");
		if(isLib == null || false) {
			response.sendRedirect("/LibraryManager/books");
		}
		
		String isbn = request.getParameter("isnb");
		String title = request.getParameter("title");
		String descr = request.getParameter("descr");
		boolean rented = false;
		Date added_to_library = new Date();
		
		Book book = new Book(isbn, title, descr, rented, added_to_library);
		try {
			bookDao.addBook(book);
		} catch (Exception e) {
			// error handling here if isbn exists
			
			
		}
		
		response.sendRedirect("/LibraryManager/book?isbn=" + book.getIsbn());
	}

}
