
package com.cognixia.jump.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.models.Book;

public class BookDaoImp implements BookDao {
	public static final Connection conn = ConnectionManager.getConnection();

	private static final String SELECT_ALL_BOOKS = "select * from book";
	private static final String ADD_BOOK = "insert into book(isbn, title, descr, rented, added_to_library) values(?, ?, ?, ?, ?)";
	private static final String GET_BOOK_BY_ISBN = "select * from book where isbn = ?";
	private static final String UPDATE_BOOK = "update book set title = ?, descr = ? where isbn = ?";
	private static final String BOOKS_BY_RENT_STATUS = "select * from book where rented = ?";
	private static final String DELETE_BOOK = "delete from book where isbn = ?";

	// Tested and works
	public List<Book> getAllBooks() {

		List<Book> bookList = new ArrayList<>();

		try (PreparedStatement state = conn.prepareStatement(SELECT_ALL_BOOKS); ResultSet rs = state.executeQuery();) {

			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String description = rs.getString("descr");
				boolean rented;
				if (rs.getInt("rented") == 1) {
					rented = true;
				} else {
					rented = false;
				}
				Date dateAdded = rs.getDate("added_to_library");
				bookList.add(new Book(isbn, title, description, rented, dateAdded));
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		return bookList;
	}

	// tested and works
	public Book getBookByIsbn(String isbn) {

		String isbnNum = "";
		String title = "";
		String description = "";
		boolean rented = false;
		Date dateAdded = new Date();
		
		try (PreparedStatement state = conn.prepareStatement(GET_BOOK_BY_ISBN);

		) {

			state.setString(1, isbn);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				isbnNum = rs.getString("isbn");
				title = rs.getString("title");
				description = rs.getString("descr");

				if (rs.getInt("rented") == 1) {
					rented = true;
				} else {
					rented = false;
				}
				dateAdded = rs.getDate("added_to_library");

			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		Book book = new Book(isbnNum, title, description, rented, dateAdded);
		return book;
	}

	// tested and works. Also uses current date as date added
	public boolean addBook(Book book) {

		try (PreparedStatement state = conn.prepareStatement(ADD_BOOK)) {
			state.setString(1, book.getIsbn());
			state.setString(2, book.getTitle());
			state.setString(3, book.getDescr());
			state.setBoolean(4, false);
			state.setString(5, java.time.LocalDate.now().toString());

			if (state.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// tested and working
	public boolean deleteBook(String isbn) {
		try (PreparedStatement state = conn.prepareStatement(DELETE_BOOK)) {

			state.setString(1, isbn);
			if (state.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}

		return false;
	}

	// tested and works
	public boolean isBookAvailable(Book b) {

		try (PreparedStatement state = conn.prepareStatement(GET_BOOK_BY_ISBN)) {
			state.setString(1, b.getIsbn());
			ResultSet rs = state.executeQuery();

			rs.next();

			if (rs.getInt("rented") == 1) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}

		return false;
	}

	// Wasn't sure if we were going to be passing in a book object or just the ISBN
	// So I overloaded the method to take a string for the isbn
	// tested and works
	public boolean isBookAvailable(String isbn) {

		try (PreparedStatement state = conn.prepareStatement(GET_BOOK_BY_ISBN)) {
			state.setString(1, isbn);
			ResultSet rs = state.executeQuery();
			rs.next();
			if (rs.getInt("rented") == 1) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}

		return false;
	}

	public boolean updateBook(Book book) {
		try (PreparedStatement state = conn.prepareStatement(UPDATE_BOOK)) {
			state.setString(1, book.getTitle());
			state.setString(2, book.getDescr());
			state.setString(3, book.getIsbn());
			if (state.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}

		return false;
	}

	// tested and working
	public List<Book> getAllRentedBooks() {
		List<Book> rentedBooks = new ArrayList<>();
		ResultSet rs = null;
		try (PreparedStatement state = conn.prepareStatement(BOOKS_BY_RENT_STATUS)) {
			state.setString(1, "1");
			rs = state.executeQuery();

			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String description = rs.getString("descr");
				boolean rented = true;
				Date dateAdded = rs.getDate("added_to_library");
				rentedBooks.add(new Book(isbn, title, description, rented, dateAdded));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return rentedBooks;
	}

	// tested and working
	public List<Book> getAllAvailableBooks() {
		List<Book> rentedBooks = new ArrayList<>();
		ResultSet rs = null;
		try (PreparedStatement state = conn.prepareStatement(BOOKS_BY_RENT_STATUS)) {
			state.setString(1, "0");
			rs = state.executeQuery();

			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String description = rs.getString("descr");
				boolean rented = true;
				Date dateAdded = rs.getDate("added_to_library");
				rentedBooks.add(new Book(isbn, title, description, rented, dateAdded));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return rentedBooks;

	}

}
