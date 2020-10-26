package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.models.Patron;

/**
 * Servlet implementation class PatronsServlet
 */
@WebServlet("/patrons")
public class PatronsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	public void init() {
//		librarianDao = new LibrarianDao();
		Connection conn = ConnectionManager.getConnection();
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

//		List<Paton> patrons = patronDao.getAll();
		List<Patron> patrons = new ArrayList<Patron>();
		patrons.add(new Patron("frist naem", "dummy", "dummyuser naem", "fakea a", false) );
		request.setAttribute("patrons", patrons);
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
