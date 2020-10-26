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
import com.cognixia.jump.library.models.Patron;

/**
 * Servlet implementation class PatronServelt
 */
@WebServlet("/patron")
public class PatronServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
//		patronDao = new PatronDaoImp();
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws ServletException, IOException {
		int id = request.getAttribute("id");
		Patron p = patronDao.getById(id);
		List<Book> books = bookDao.getCheckoutHistoryOfUserById(id);
		request.setAttribute("id", p);
		request.setAttribute("userId", 
				session.getAttribute("userId") == null ? null : session.getAttribute("userId"));
		request.setAttribute("isLibrarian", 
				session.getAttribute("isLibrarian") == null ? null : session.getAttribute("isLibrarian"));
		
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
