package com.cognixia.jump.library.models;

import java.util.Date;

public class BookCheckout {
	
	protected int checkout_id;
	protected int patron_id;
	protected String isbn;
	protected Date checkedout;
	protected Date due_date;
	protected Date returned;
	
	public BookCheckout(int patron_id, String isbn, Date checkedout, Date due_date, Date returned) {
		super();
		this.patron_id = patron_id;
		this.isbn = isbn;
		this.checkedout = checkedout;
		this.due_date = due_date;
		this.returned = returned;
	}

	public int getCheckout_id() {
		return checkout_id;
	}

	public void setCheckout_id(int checkout_id) {
		this.checkout_id = checkout_id;
	}

	public int getPatron_id() {
		return patron_id;
	}

	public void setPatron_id(int patron_id) {
		this.patron_id = patron_id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getCheckedout() {
		return checkedout;
	}

	public void setCheckedout(Date checkedout) {
		this.checkedout = checkedout;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Date getReturned() {
		return returned;
	}

	public void setReturned(Date returned) {
		this.returned = returned;
	}

	@Override
	public String toString() {
		return "BookCheckout [checkout_id=" + checkout_id + ", patron_id=" + patron_id + ", isbn=" + isbn
				+ ", checkedout=" + checkedout + ", due_date=" + due_date + ", returned=" + returned + "]";
	}
	
	
	
	
	

}
