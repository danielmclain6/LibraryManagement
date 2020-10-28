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
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Book;
import com.cognixia.jump.library.models.Patron;

/**
 * Servlet implementation class PatronsServlet
 */
@WebServlet("/patrons")
public class PatronsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatronDAO patronDao;

	@Override
	public void init() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String action = request.getParameter("which_filter");
		List<Patron> patrons = patronDao.getAllPatrons();

		if(action == null || action.equals("all")) {
			patrons = patronDao.getAllPatrons();
		} else if(action.equals("all_available")) {
			patrons = patronDao.getAllAvailablePatrons();
		} else if(action.equals("all_available")) {
			patrons = patronDao.getAllFrozenPatrons();
		}

		System.out.println(action + " < - action");
		System.out.println("called listPatrons, allPatrons = " + patrons);

		Patron patron = null;
		if(request.getParameter("patron_id") != null) {
			patron = patronDao.getPatronById(Integer.parseInt(request.getParameter("patron_id")));
		}
		
		request.setAttribute("patrons", patrons);
		request.setAttribute("patron", patron);
		request.setAttribute("userId", 
				session.getAttribute("userId") == null ? null : session.getAttribute("userId"));
		request.setAttribute("isLibrarian", 
				session.getAttribute("isLibrarian") == null ? false : session.getAttribute("isLibrarian"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("patrons.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
