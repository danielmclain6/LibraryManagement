package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/")
public class BookServlet extends HttpServlet {
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

//	doPost - add book to database or edit book (refer to ProductServlet in CrudProject)
//
//	if successful sendRedirect ("book?id=newBookID)
//	route: /books
//
//	books = bookDAO.allBooks()
//	request.setAttribute("books", books)
//	route: /book?isbn=1
//
//	book = bookDAO.getBookbyISBN(isbn)
//	request.setAttribute("book", book)

//	route: /books/checkout?isbn='1234567890'
//
//			check session to see if patron has logged on before processing checkout
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("which_filter");
		List<Book> allBooks = new ArrayList<Book>();

		if(action == null || action.equals("all")) {
			allBooks = bookDao.getAllBooks();
		} else if(action.equals("all_rented")) {
			allBooks = bookDao.getAllRentedBooks();
		} else if(action.equals("all_available")) {
			allBooks = bookDao.getAllAvailableBooks();
		}

		System.out.println(action + " < - action");
		System.out.println("called listBooks, allBooks = " + allBooks);

		request.setAttribute("books", allBooks);
		request.setAttribute("userId", 
				session.getAttribute("userId") == null ? null : session.getAttribute("userId"));
		request.setAttribute("isLibrarian", 
				session.getAttribute("isLibrarian") == null ? null : session.getAttribute("isLibrarian"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
		System.out.println("sent");
		dispatcher.forward(request, response);
		

	}

	private void listAllBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = "blah";
		switch (action) {
		
		case "/books":
			listAllBooks(request, response);
			break;
		case "/isbn":
			getBookByIsbn(request, response);
			break;
		case "/delete":
			deleteBook(request, response);
			break;
		case "/update":
			editBook(request, response);
			break;
		case "/insert":
			addNewBook(request, response);
			break;
		case "/check":
			isBookAvailable(request, response);
			break;
		case "/rented":
			listAllRentedBooks(request, response);
			break;
		case "/available":
			listAllAvailableBooks(request, response);
			break;
		default: // default will take you to the index.jsp page
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			break;
		}

		
	}

	private void getBookByIsbn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = request.getParameter("isbn");
		Book book = bookDao.getBookByIsbn(isbn);
		System.out.println("called getBookbyIsbn, book = " + book);

		request.setAttribute("book", book);

		RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
		System.out.println("sent");
		dispatcher.forward(request, response);
	}

	private void addNewBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String descr = request.getParameter("description");

		Book book = new Book(isbn, title, descr, false, null);

		if (bookDao.addBook(book)) {
			System.out.println("Adding book: " + book);
		}

		response.sendRedirect("book?id=newBookID");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = request.getParameter("isbn");

		if (bookDao.deleteBook(isbn)) {
			System.out.println("Deleted product with isbn = " + isbn);
		}

		response.sendRedirect("list");
	}

	private void editBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String descr = request.getParameter("description");

		Book book = new Book(isbn, title, descr, false, null);

		if (bookDao.updateBook(book)) {
			System.out.println("Updating book: " + book);
		}

		response.sendRedirect("book?id=newBookID");
	}

	private void isBookAvailable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = request.getParameter("isbn");
		Book book = bookDao.getBookByIsbn(isbn);

		if (bookDao.isBookAvailable(isbn)) {
			System.out.println("The book " + book + "is available.");
		}

		else {
			System.out.println("The book " + book + "is not available.");
		}
	}

	private void listAllRentedBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Book> allRentedBooks = bookDao.getAllRentedBooks();
		System.out.println("called listBooks, allAvaiableBooks = " + allRentedBooks);

		request.setAttribute("allRentedBooks", allRentedBooks);

		RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
		System.out.println("sent");
		dispatcher.forward(request, response);
	}

	private void listAllAvailableBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Book> allAvailableBooks = bookDao.getAllAvailableBooks();
		System.out.println("called listBooks, allAvaiableBooks = " + allAvailableBooks);

		request.setAttribute("allAvailableBooks", allAvailableBooks);

		RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
		System.out.println("sent");
		dispatcher.forward(request, response);
	}
}