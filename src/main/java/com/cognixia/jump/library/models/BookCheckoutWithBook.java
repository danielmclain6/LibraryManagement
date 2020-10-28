package com.cognixia.jump.library.models;

import com.cognixia.jump.library.dao.BookDaoImp;
import java.util.Date;

public class BookCheckoutWithBook extends BookCheckout
{
	private Book b;
	
	public BookCheckoutWithBook(int patron_id, String isbn, Date checkedout, Date due_date, Date returned)
	{
		super(patron_id, isbn, checkedout, due_date, returned);
		BookDaoImp bdi = new BookDaoImp();
		setBook(bdi.getBookByIsbn(isbn));
	}
	
	public Book getBook()
	{
		return b;
	}
	
	public void setBook(Book b)
	{
		this.b = b;
	}
	
	@Override
	public String toString()
	{
		String bc = "BookCheckout [checkout_id=" + getCheckout_id() + ", patron_id=" + getPatron_id() + ", isbn=" + getIsbn()
				+ ", checkedout=" + getCheckedout() + ", due_date=" + getDue_date() + ", returned=" + getReturned() + ", Book=" + b.toString() +"]";
		
		return bc;
	}

}
