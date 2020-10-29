package com.cognixia.jump.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.models.Book;
import com.cognixia.jump.library.models.BookCheckout;
import com.cognixia.jump.library.models.Patron;

public class BookCheckoutDaoImp implements BookCheckoutDao {
	public static final Connection conn = ConnectionManager.getConnection();

	private static final String CHECKOUT_BOOK = "insert into book_checkout(patron_id, isbn, checkedout, due_date) "
			+ "values(?, ?, ?, ?) ";
	private static final String RETURN_BOOK = "update book_checkout set returned = ? where " + "isbn = ?";
	private static final String RETURN_BOOK_FROM_PATRON = "update book_checkout set returned = ? where "
			+ "isbn = ? and patron_id = ?";
	private static final String GET_BOOK_HISTORY = "select * from book_checkout where patron_id = ?";

	// tested and works

	/*
	 * Takes a patron object, plus a book object. Inserts an entry into the
	 * book_checkout table with current date as checkout date Also updates book
	 * table to show book as rented Due date is 30 days from current date
	 * 
	 * 
	 */

	public boolean checkoutBook(Patron p, Book b) {
		String today = java.time.LocalDate.now().toString();
		String dueDate = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		try {
			sdf.parse(today);
			c.setTime(sdf.parse(today));

		} catch (ParseException e) {
			// TODO: handle exception
		}
		c.add(Calendar.DATE, 30);
		dueDate = sdf.format(c.getTime());

		try (PreparedStatement state = conn.prepareStatement(CHECKOUT_BOOK);
				PreparedStatement bookState = conn.prepareStatement("update book set rented = 1 where isbn = ?")) {
			
			state.setInt(1, p.getId());
			state.setString(2, b.getIsbn());
			state.setString(3, today);
			state.setString(4, dueDate);
			bookState.setString(1, b.getIsbn());
			

			if (state.executeUpdate() > 0) {
				bookState.executeUpdate();
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * Takes a patron object, plus a String isbn. Inserts an entry into the
	 * book_checkout table with current date as checkout date Due date is 30 days
	 * from current date Also updates the book in the book table to be shown as
	 * rented
	 * 
	 * 
	 */
	public boolean checkoutBookByIsbn(Patron p, String isbn) {
		String today = java.time.LocalDate.now().toString();
		String returnDate = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		try {
			sdf.parse(today);
			c.setTime(sdf.parse(today));

		} catch (ParseException e) {
			// TODO: handle exception
		}
		c.add(Calendar.DATE, 30);
		returnDate = sdf.format(c.getTime());

		try (PreparedStatement state = conn.prepareStatement(CHECKOUT_BOOK);
				PreparedStatement bookState = conn.prepareStatement("update book set rented = 1 where isbn = ?")) {

			state.setInt(1, p.getId());
			state.setString(2, isbn);
			state.setString(3, today);
			state.setString(4, returnDate);

			bookState.setString(1, isbn);
			bookState.executeUpdate();

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

	public boolean checkoutBookByIsbn(int id, String isbn) {
		String today = java.time.LocalDate.now().toString();
		String returnDate = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		try {
			sdf.parse(today);
			c.setTime(sdf.parse(today));

		} catch (ParseException e) {
			// TODO: handle exception
		}
		c.add(Calendar.DATE, 30);
		returnDate = sdf.format(c.getTime());

		try (PreparedStatement state = conn.prepareStatement(CHECKOUT_BOOK);
				PreparedStatement bookState = conn.prepareStatement("update book set rented = 1 where isbn = ?")) {

			state.setInt(1, id);
			state.setString(2, isbn);
			state.setString(3, today);
			state.setString(4, returnDate);

			bookState.setString(1, isbn);

			if (state.executeUpdate() > 0) {
				bookState.executeUpdate();
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

	/*
	 * Takes a Book parameter Sets rented status on both book and book_checkout
	 * tables as 0 Plus sets the current date as the return date on book_checkout
	 * 
	 * 
	 * 
	 */
	public boolean returnBook(Book b) {
		try (PreparedStatement state = conn.prepareStatement(RETURN_BOOK);
				PreparedStatement bookState = conn.prepareStatement("update book set rented = 0 where isbn = ?");) {

			// This sets the books rented status to 0 in the book table
			bookState.setString(1, b.getIsbn());
			bookState.executeUpdate();

			state.setString(1, java.time.LocalDate.now().toString());
			state.setString(2, b.getIsbn());
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

	/*
	 * Takes isbn as parameter Sets return_date on book_checkout as current date
	 * Sets book rented status as 0 on book table
	 * 
	 */
	public boolean returnBook(String isbn) {
		try (PreparedStatement state = conn.prepareStatement(RETURN_BOOK);
				PreparedStatement bookState = conn.prepareStatement("update book set rented = 0 where isbn = ?");) {

			// This sets the books rented status to 0 in the book table
			bookState.setString(1, isbn);
			bookState.executeUpdate();

			state.setString(1, java.time.LocalDate.now().toString());
			state.setString(2, isbn);
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

	/*
	 * Takes patron and book as parameter Sets rented status to 0 on book table and
	 * sets returnDate on book_checkout as current date using isbn of book, plus the
	 * patron's ID
	 * 
	 */
	public boolean returnBook(Patron p, Book b) {
		try (PreparedStatement state = conn.prepareStatement(RETURN_BOOK_FROM_PATRON);
				PreparedStatement bookState = conn.prepareStatement("update book set rented = 0 where isbn = ?")) {

			// This sets the books rented status to 0 in the book table
			bookState.setString(1, b.getIsbn());
			bookState.executeUpdate();

			state.setString(1, java.time.LocalDate.now().toString());
			state.setString(2, b.getIsbn());
			state.setInt(3, p.getId());
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

	public List<BookCheckout> getBookHistory(int id) {
		List<BookCheckout> bookHistory = new ArrayList<>();

		Date returnDate;
		try (PreparedStatement state = conn.prepareStatement(GET_BOOK_HISTORY)) {

			state.setInt(1, id);
			ResultSet rs = state.executeQuery();

			while (rs.next()) {
				int checkoutId = rs.getInt("checkout_id");
				System.out.println(checkoutId);
				int patronId = rs.getInt("patron_id");
				System.out.println(patronId);

				String isbn = rs.getString("isbn");
				System.out.println(isbn);
				Date checkedOut = rs.getDate("checkedout");
				System.out.println(checkedOut);
				Date dueDate = rs.getDate("due_date");
				System.out.println(dueDate);
				if (rs.getString("returned") != null) {
					returnDate = rs.getDate("returned");
				} else {
					returnDate = null;
				}
				System.out.println(returnDate);
				BookCheckout bc = new BookCheckout(patronId, isbn, checkedOut, dueDate, returnDate);
				System.out.println(bc);
				bookHistory.add(bc);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		return bookHistory;
	}

	@Override
	public List<BookCheckout> getBookHistory(Patron p) {
		List<BookCheckout> bookHistory = new ArrayList<>();

		Date returnDate;
		try (PreparedStatement state = conn.prepareStatement(GET_BOOK_HISTORY)) {

			state.setInt(1, p.getId());
			ResultSet rs = state.executeQuery();

			while (rs.next()) {
				int checkoutId = rs.getInt("checkout_id");
				System.out.println(checkoutId);
				int patronId = rs.getInt("patron_id");
				System.out.println(patronId);

				String isbn = rs.getString("isbn");
				System.out.println(isbn);
				Date checkedOut = rs.getDate("checkedout");
				System.out.println(checkedOut);
				Date dueDate = rs.getDate("due_date");
				System.out.println(dueDate);

				if (rs.getString("returned") != null) {
					returnDate = rs.getDate("returned");
				} else {
					returnDate = null;
				}
				System.out.println(returnDate);
				BookCheckout bc = new BookCheckout(patronId, isbn, checkedOut, dueDate, returnDate);
				System.out.println(bc);
				bookHistory.add(bc);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		return bookHistory;
	}

}
