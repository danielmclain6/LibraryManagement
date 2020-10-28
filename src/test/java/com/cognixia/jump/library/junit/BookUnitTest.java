package com.cognixia.jump.library.junit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.BookDao;
import com.cognixia.jump.library.dao.BookDaoImp;
import com.cognixia.jump.library.models.Book;

public class BookUnitTest {

	private BookDao bookDAO = new BookDaoImp();
	private Connection conn;
	
	@Before
	public void connectDB() {
		conn = ConnectionManager.getConnection();
	}
	
	@Test
	public void getAllBooksTest() {
		List<Book> books = bookDAO.getAllBooks();
		assertTrue(books.size()>0);
	}
	
	@Test
	public void getAllAvailableBooksTest() {
		List<Book> books = bookDAO.getAllAvailableBooks();
		assertTrue(books.size()>0);
	}
	
	@Test
	public void addDeleteBookTest() {
		Book book = new Book("1234123412", 
							"Catcher in the Rye", 
							"A book about a teenage kid",
							false, 
							null);
		
		assertTrue(bookDAO.addBook(book));
		
		assertTrue(bookDAO.deleteBook(book.getIsbn()));
		
		
	}
	
	@Test
	public void getAllRentedBooksTest() {
		List<Book> books = bookDAO.getAllRentedBooks();
		assertTrue(books.size()>0);
	}
	
	@Test
	public void isBookAvailbleTest() {
		String isbn = "1234567893"; //should be unavailable/false
		assertFalse(bookDAO.isBookAvailable(isbn));
	}

}
