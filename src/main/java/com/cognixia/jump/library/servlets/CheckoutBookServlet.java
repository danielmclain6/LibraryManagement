package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.BookCheckoutDao;
import com.cognixia.jump.library.dao.BookCheckoutDaoImp;
import com.cognixia.jump.library.dao.BookDao;
import com.cognixia.jump.library.dao.BookDaoImp;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Book;
import com.cognixia.jump.library.models.Patron;

/**
 * Servlet implementation class CheckoutBookServlet
 */
@WebServlet("/checkout_book")
public class CheckoutBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookCheckoutDao bookcheckoutDao;
	private BookDao bookDao;
	private PatronDAO patronDao;

	@Override
	public void init() {
		bookcheckoutDao = new BookCheckoutDaoImp();
		bookDao = new BookDaoImp();
		patronDao = new PatronDAOImp();
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String strId = request.getParameter(("user_id"));
		if(strId == null) {
			System.out.println("REDIRECT");
			response.sendRedirect("/LibraryManager/login");
			
		}
		int id = Integer.parseInt(strId);
		// check it anyone is in session
		Patron p = patronDao.getPatronById(id);
		Book b = bookDao.getBookByIsbn(request.getParameter("isbn"));

//		boolean bool = bookcheckoutDao.checkoutBook(p, b);
//		if (bool) {
//		}
		System.out.println("book " + b.getTitle() +"checkout by " + p.getUsername());
		response.sendRedirect("/LibraryManager/books");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
