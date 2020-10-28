
package com.cognixia.jump.library.dao;

import java.util.List;

import com.cognixia.jump.library.models.Book;

public interface BookDao {

	public List<Book> getAllBooks();

	public Book getBookByIsbn(String isbn);

	public boolean addBook(Book book);

	public boolean deleteBook(String isbn);

	public boolean isBookAvailable(Book b);

	public boolean isBookAvailable(String isbn);

	public boolean updateBook(Book book);

	public List<Book> getAllRentedBooks();

	public List<Book> getAllAvailableBooks();

}
