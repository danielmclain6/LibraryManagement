package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
 * Servlet implementation class EditBook
 */
@WebServlet("/edit_book")
public class EditBook extends HttpServlet {
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

		HttpSession session = request.getSession();
		Boolean isLib = (Boolean)session.getAttribute("isLibrarian");
		if(isLib == null || false) {
			response.sendRedirect("/LibraryManager/books");
		}
		String isbn = request.getParameter("isnb");
		String title = request.getParameter("title");
		String descr = request.getParameter("descr");
		Book book = bookDao.getBookByIsbn(isbn);
		book.setTitle(title);
		book.setDescr(descr);
		bookDao.updateBook(book);
		
		response.sendRedirect("/LibraryManager/book?isbn=" + isbn);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
