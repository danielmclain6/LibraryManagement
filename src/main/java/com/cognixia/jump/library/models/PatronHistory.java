package com.cognixia.jump.library.models;

import java.util.ArrayList;

public class PatronHistory extends Patron
{
	private ArrayList<BookCheckoutWithBook> history;
	
	public PatronHistory(int id, String first_name, String last_name, String username, String password, boolean account_frozen)
	{
		super(id, first_name, first_name, first_name, first_name, account_frozen);
		history = new ArrayList<BookCheckoutWithBook>();
	}
	
	public void addHistory(BookCheckoutWithBook bc)
	{
		history.add(bc);
	}
	
	public void editHistory(int i, BookCheckoutWithBook bc)
	{
		history.set(i, bc);
	}
	
	public void deleteHistory(int i)
	{
		history.remove(i);
	}
	
	public ArrayList<BookCheckoutWithBook> getHistory()
	{
		return history;
	}
	
	@Override
	public String toString()
	{
		String ph = new String("Patron History [id=" + getId() + ", first_name=" + getFirst_name() + ", last_name=" + getLast_name() + ", username=" + getUsername()
				+ ", password=" + getPassword() + ", account_frozen=" + isAccount_frozen() + ", history=[\n");
		
		for(BookCheckoutWithBook bc: history)
		{
			ph = ph.concat("\t" + bc.toString() + "\n");
		}
		
		ph = ph.concat("]]");
		
		return ph;
	}
	
}
