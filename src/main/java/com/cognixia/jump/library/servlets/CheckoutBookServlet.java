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

	@Override
	public void init() {
		bookcheckoutDao = new BookCheckoutDaoImp();
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// check it anyone is in session
		if (session.getAttribute("user") == null) {
			// if there is no one in session, then redirect them to login
			response.sendRedirect("/LibraryManager/login");
		}
		System.out.println("session.getAttribute('user')");
		System.out.println(session.getAttribute("user"));
		Patron p = (Patron) session.getAttribute("user");
		Book b = bookDao.getBookByIsbn(request.getParameter("isbn"));

//		boolean bool = bookcheckoutDao.checkoutBook(p, b);
//		if (bool) {
//		}
		System.out.println("book " + b.getTitle() +"checkout by " + p.getUsername());
		response.sendRedirect("/LibraryManager/book?isbn=" + b.getIsbn());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
