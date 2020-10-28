package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.BookCheckoutDao;
import com.cognixia.jump.library.dao.BookCheckoutDaoImp;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Patron;
import com.cognixia.jump.library.models.BookCheckout;

/**
 * Servlet implementation class PatronServelt
 */
@WebServlet("/patron")
public class PatronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatronDAO patronDao;
	private BookCheckoutDao bookcheckoutDao;
	@Override
	public void init() {
		patronDao = new PatronDAOImp();
		bookcheckoutDao = new BookCheckoutDaoImp();
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Patron p = patronDao.getPatronById(id);
		List<BookCheckout> bookcheckouts = bookcheckoutDao.getBookHistory(p.getId());
		request.setAttribute("patron", p);
		request.setAttribute("bookCheckouts", bookcheckouts);
		request.setAttribute("userId", 
				session.getAttribute("userId") == null ? null : session.getAttribute("userId"));
		request.setAttribute("isLibrarian", 
				session.getAttribute("isLibrarian") == null ? null : session.getAttribute("isLibrarian"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("patron.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
