package com.cognixia.jump.library.servlets;

import java.io.IOException;
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
import com.cognixia.jump.library.dao.BookDao;
import com.cognixia.jump.library.dao.BookDaoImp;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.BookCheckout;
import com.cognixia.jump.library.models.BookCheckoutWithBook;
import com.cognixia.jump.library.models.Patron;

/**
 * Servlet implementation class PatronServelt
 */
@WebServlet("/patron")
public class PatronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatronDAO patronDao;
	private BookDao bookDao;
	private BookCheckoutDao bookcheckoutDao;
	@Override
	public void init() {
		patronDao = new PatronDAOImp();
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
		int id = Integer.parseInt(request.getParameter("id"));
		Patron p = patronDao.getPatronById(id);
		List<BookCheckout> historybookcheckouts = bookcheckoutDao.getBookHistory(p.getId());
		List<BookCheckoutWithBook> currentBooks = new ArrayList<BookCheckoutWithBook>();
		
		if(p != null && historybookcheckouts.size() != 0) {
			for (BookCheckout checked: historybookcheckouts) {
				if (checked.getReturned() == null) {
					BookCheckoutWithBook book = new BookCheckoutWithBook(checked.getCheckout_id(),p.getId(), 
																		checked.getIsbn(), checked.getCheckedout(), 
																		checked.getDue_date(),checked.getReturned());
					
					currentBooks.add(book);
				}
				
				
			}
		}
		
		request.setAttribute("patron", p);
		request.setAttribute("currentbooks", currentBooks);
		request.setAttribute("historybookcheckouts", historybookcheckouts);
		request.setAttribute("userId", 
				session.getAttribute("userId") == null ? null : session.getAttribute("userId"));
		request.setAttribute("isLibrarian", 
				session.getAttribute("isLibrarian") == null ? false : session.getAttribute("isLibrarian"));
		
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
