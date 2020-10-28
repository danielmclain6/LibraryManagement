package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.BookDao;
import com.cognixia.jump.library.dao.BookDaoImp;

/**
 * Servlet implementation class OneBook
 */
@WebServlet("/one_book")
public class OneBook extends HttpServlet {
	private static final long serialVersionUID = 13464673L;
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
		
		System.out.println(request.getParameter("isbn"));
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
		System.out.println("sent");
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
