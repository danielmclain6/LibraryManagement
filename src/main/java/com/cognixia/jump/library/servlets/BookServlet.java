package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.connection.ConnectionManager;

public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Connection conn;
	private PreparedStatement pstmt;

	@Override
	public void init() {

		conn = ConnectionManager.getConnection();

		try {
			

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {

		try {
			pstmt.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		doPost - add book to database or edit book (refer to ProductServlet in CrudProject)
//
//		if successful sendRedirect ("book?id=newBookID)
//		route: /books
//
//		books = bookDAO.allBooks()
//		request.setAttribute("books", books)
//		route: /book?isbn=1
//
//		book = bookDAO.getBookbyISBN(isbn)
//		request.setAttribute("book", book)
		
//		route: /books/checkout?isbn='1234567890'
//
//				check session to see if patron has logged on before processing checkout

		doGet(request, response);
	}

}