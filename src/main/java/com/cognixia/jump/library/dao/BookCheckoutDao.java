package com.cognixia.jump.library.DAO;
import java.util.List;

import com.cognixia.jump.library.models.Book;
import com.cognixia.jump.library.models.BookCheckout;
import com.cognixia.jump.library.models.Patron;

public interface BookCheckoutDao {

		
		public boolean checkoutBook(Patron p, Book b);
		
		public boolean checkoutBookByIsbn(Patron p, String isbn);
		
		public boolean checkoutBookByIsbn(int id, String isbn);
		
		public boolean returnBook(Book b);
		
		public boolean returnBook(String isbn);
		
		public boolean returnBook(Patron p, Book b);
		
		public List<BookCheckout> getBookHistory(int id);
		
		public List<BookCheckout> getBookHistory(Patron p);
		
	}
