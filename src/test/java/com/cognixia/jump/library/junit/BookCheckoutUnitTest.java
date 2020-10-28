package com.cognixia.jump.library.junit;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.BookCheckoutDao;
import com.cognixia.jump.library.dao.BookCheckoutDaoImp;
import com.cognixia.jump.library.dao.BookDao;
import com.cognixia.jump.library.dao.BookDaoImp;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.dao.PatronDAOImp;
import com.cognixia.jump.library.models.Book;
import com.cognixia.jump.library.models.Patron;

public class BookCheckoutUnitTest {
	PatronDAO patronDAO = new PatronDAOImp();
	BookDao bookDAO = new BookDaoImp();
	BookCheckoutDao bookCheckDAO = new BookCheckoutDaoImp();
	Connection conn;
	
	@Before
	public void connectDB() {
		conn = ConnectionManager.getConnection();
	}

	@Test
	public void checkoutBookTest() {
		Patron patron = patronDAO.getPatronById(1);
		Book book = bookDAO.getBookByIsbn("1234567800");
		
		assertTrue(bookCheckDAO.checkoutBook(patron, book));
	}
	
	@Test
	public void returnBookTest() {
		String isbn = "1234567800";
		assertTrue(bookCheckDAO.returnBook(isbn));
	}

}
